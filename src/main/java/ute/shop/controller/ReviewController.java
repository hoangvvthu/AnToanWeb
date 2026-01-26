package ute.shop.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.config.JPAConfig;
import ute.shop.entity.Order;
import ute.shop.entity.OrderItem;
import ute.shop.entity.Product;
import ute.shop.entity.Review;
import ute.shop.entity.Store;
import ute.shop.entity.User;
import ute.shop.services.*;
import ute.shop.services.implement.OrderServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.ReviewServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = { "/reviews", "/reviews/add" })
public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IReviewService reviewService;
	private final IStoreService storeService = new StoreServiceImpl();
	private final IProductService productService = new ProductServiceImpl();
	private final IOrderService orderService = new OrderServiceImpl(); // Khởi tạo OrderService

	public ReviewController() {
		this.reviewService = new ReviewServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/reviews":
			showProductReviews(req, resp);
			break;
		case "/reviews/add":
			showAddReviewForm(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/reviews/add":
			addReview(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void showProductReviews(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(req.getParameter("productId"));
			List<Review> reviews = reviewService.getReviewsByProduct(productId);
			req.setAttribute("reviews", reviews);
			req.getRequestDispatcher("/views/reviews.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch reviews.");
		}
	}

	private void addReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			// Lấy userId từ session
			Integer userId = (Integer) req.getSession().getAttribute("userId");
			if (userId == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}

			// Lấy orderId, storeId, productId, nội dung và đánh giá từ form
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			int storeId = Integer.parseInt(req.getParameter("storeId"));
			int productId = Integer.parseInt(req.getParameter("productId"));
			String content = req.getParameter("content");
			int rating = Integer.parseInt(req.getParameter("stars"));

			// Kiểm tra dữ liệu đầu vào
			if (content == null || content.isEmpty() || rating < 1 || rating > 5) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=invalid-input");
				return;
			}

			// Tìm Order từ database
			Order order = orderService.findById(orderId);
			if (order == null) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=order-not-found");
				return;
			}

			// Lấy Product từ OrderItem (Order có nhiều OrderItem)
			Product product = productService.findById(productId);

			// Tạo đối tượng Review và thiết lập các thuộc tính
			Review review = new Review();
			review.setContent(content);
			review.setStars(rating);
			review.setProduct(product); // Gắn sản phẩm vào review
			review.setOrder(order);

			Store store = new Store();
			store.set_id(storeId); // Gắn storeId vào review
			review.setStore(store);

			User user = new User();
			user.set_id(userId); // Gắn userId vào review
			review.setUser(user);

			// Thêm review vào cơ sở dữ liệu
			boolean isAdded = reviewService.addReview(review);

			if (isAdded) {
				resp.sendRedirect(req.getContextPath() + "/home/productDetail?id=" + productId);
			} else {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=add-failed");
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect(req.getContextPath() + "/reviews?error=invalid-input");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/reviews?error=add-failed");
		}
	}

	private void showAddReviewForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EntityManager em = JPAConfig.getEntityManager(); // Mở lại session

		try {
			// Lấy orderId và storeId từ tham số request
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			int storeId = Integer.parseInt(req.getParameter("storeId"));

			// Lấy đối tượng Order và Store từ dịch vụ
			Order order = em.find(Order.class, orderId); // Sử dụng EntityManager để tìm Order
			Store store = storeService.findById(storeId); // Assuming you have a Store service to fetch store by ID

			// Lấy OrderItem đầu tiên từ Order
			OrderItem orderItem = order.getOrderItems().get(0); // Assuming there's at least one item

			// Lấy Product từ OrderItem
			Product product = orderItem.getProduct();

			// Đặt đối tượng product vào request attribute
			req.setAttribute("product", product);

			// Lấy productId để hiển thị trên form
			int productId = product.get_id();
			req.setAttribute("productId", productId); // Thêm productId vào request

			if (order != null && store != null) {
				// Đặt đối tượng order và store vào request để render trên JSP
				req.setAttribute("order", order);
				req.setAttribute("store", store);
				req.setAttribute("productId", productId);

				// Chuyển hướng tới trang thêm review
				req.getRequestDispatcher("/views/addReview.jsp").forward(req, resp);
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order or Store not found or Order has no items.");
			}

		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order or store ID.");
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception to the server logs
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"An error occurred while fetching the review form.");
		} finally {
			em.close(); // Đảm bảo đóng session sau khi thực hiện xong
		}
	}

}
