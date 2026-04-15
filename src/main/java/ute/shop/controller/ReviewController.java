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
import ute.shop.entity.OrderStatus;
import ute.shop.entity.Product;
import ute.shop.entity.Review;
import ute.shop.entity.Store;
import ute.shop.entity.User;
import ute.shop.services.IOrderService;
import ute.shop.services.IProductService;
import ute.shop.services.IReviewService;
import ute.shop.services.IStoreService;
import ute.shop.services.implement.OrderServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.ReviewServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/reviews", "/reviews/add" })
public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IReviewService reviewService;
	private final IStoreService storeService = new StoreServiceImpl();
	private final IProductService productService = new ProductServiceImpl();
	private final IOrderService orderService = new OrderServiceImpl();

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
			Integer currentUserId = (Integer) req.getSession().getAttribute("userId");
			User currentUser = (User) req.getSession().getAttribute("account");
			if (currentUserId == null || currentUser == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}

			int orderId = Integer.parseInt(req.getParameter("orderId"));
			int storeId = Integer.parseInt(req.getParameter("storeId"));
			int productId = Integer.parseInt(req.getParameter("productId"));
			String content = req.getParameter("content");
			int rating = Integer.parseInt(req.getParameter("stars"));

			if (content == null || content.isEmpty() || rating < 1 || rating > 5) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=invalid-input");
				return;
			}

			Order order = orderService.findById(orderId);
			if (order == null) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=order-not-found");
				return;
			}

			if (!canAccessOrder(currentUserId, currentUser, order)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not allowed to review this order.");
				return;
			}

			if (order.getStatus() != OrderStatus.DELIVERED) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=invalid-order-status");
				return;
			}

			Product product = productService.findById(productId);
			if (product == null || order.getStore() == null || order.getStore().get_id() != storeId
					|| !doesOrderContainProduct(order, productId)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Order, product and store do not match.");
				return;
			}

			if (hasExistingReview(orderId, storeId, currentUserId, productId)) {
				resp.sendRedirect(req.getContextPath() + "/reviews?error=already-reviewed");
				return;
			}

			Review review = new Review();
			review.setContent(content);
			review.setStars(rating);
			review.setProduct(product);
			review.setOrder(order);
			review.setStore(order.getStore());
			review.setUser(currentUser);

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
		EntityManager em = JPAConfig.getEntityManager();

		try {
			Integer currentUserId = (Integer) req.getSession().getAttribute("userId");
			User currentUser = (User) req.getSession().getAttribute("account");
			if (currentUserId == null || currentUser == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}

			int orderId = Integer.parseInt(req.getParameter("orderId"));
			int storeId = Integer.parseInt(req.getParameter("storeId"));

			Order order = em.find(Order.class, orderId);
			Store store = storeService.findById(storeId);
			if (order == null || store == null || order.getStore() == null || order.getStore().get_id() != storeId
					|| !canAccessOrder(currentUserId, currentUser, order)
					|| order.getStatus() != OrderStatus.DELIVERED
					|| order.getOrderItems() == null
					|| order.getOrderItems().isEmpty()) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not allowed to review this order.");
				return;
			}

			OrderItem orderItem = order.getOrderItems().get(0);
			Product product = orderItem.getProduct();
			if (product == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
				return;
			}

			req.setAttribute("product", product);
			req.setAttribute("productId", product.get_id());
			req.setAttribute("order", order);
			req.setAttribute("store", store);

			req.getRequestDispatcher("/views/addReview.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order or store ID.");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"An error occurred while fetching the review form.");
		} finally {
			em.close();
		}
	}

	private boolean canAccessOrder(Integer currentUserId, User currentUser, Order order) {
		if (order.getUser() != null && order.getUser().get_id() == currentUserId) {
			return true;
		}
		return currentUser.getRole() == User.Role.ADMIN;
	}

	private boolean doesOrderContainProduct(Order order, int productId) {
		for (OrderItem item : order.getOrderItems()) {
			if (item.getProduct() != null && item.getProduct().get_id() == productId) {
				return true;
			}
		}
		return false;
	}

	private boolean hasExistingReview(int orderId, int storeId, int userId, int productId) {
		List<Review> existingReviews = reviewService.getReviewsByOrderAndStore(orderId, storeId);
		for (Review review : existingReviews) {
			if (review.getUser() != null
					&& review.getUser().get_id() == userId
					&& review.getProduct() != null
					&& review.getProduct().get_id() == productId) {
				return true;
			}
		}
		return false;
	}
}
