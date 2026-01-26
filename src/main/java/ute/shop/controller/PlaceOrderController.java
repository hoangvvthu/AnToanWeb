package ute.shop.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Cart;
import ute.shop.entity.Delivery;
import ute.shop.entity.Order;
import ute.shop.entity.Store;
import ute.shop.entity.User;
import ute.shop.services.ICartService;
import ute.shop.services.IDeliveryService;
import ute.shop.services.IOrderService;
import ute.shop.services.IStoreService;
import ute.shop.services.IUserService;
import ute.shop.services.implement.CartServiceImpl;
import ute.shop.services.implement.DeliveryServiceImpl;
import ute.shop.services.implement.OrderServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;
import ute.shop.services.implement.UserServiceImpl;

@WebServlet(urlPatterns = { "/orders/place" })
public class PlaceOrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IOrderService orderService = new OrderServiceImpl();
	private final IDeliveryService deliveryService = new DeliveryServiceImpl();
	private final IStoreService storeService = new StoreServiceImpl();
	private final ICartService cartService = new CartServiceImpl();
	private final IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer userId = (Integer) req.getSession().getAttribute("userId");

			// Kiểm tra userId
			if (userId == null) {
				resp.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			}

			// Lấy thông tin người dùng
			User user = userService.findById(userId);
			if (user == null) {
				throw new RuntimeException("User not found for ID: " + userId);
			}

			// Lấy thông tin giỏ hàng
			Cart cart = cartService.findCartByUserId(userId);
			if (cart == null || cart.getCartItems().isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/cart.jsp");
				return;
			}

			// Lấy thông tin cửa hàng từ giỏ hàng
			Integer storeId = cart.getCartItems().get(0).getProduct().getStore().get_id();
			Store store = storeService.findById(storeId);
			if (store == null) {
				throw new RuntimeException("Store not found for ID: " + storeId);
			}

			// Lấy danh sách phương thức giao hàng
			List<Delivery> deliveryList = deliveryService.getAllDeliveries();
			if (deliveryList == null || deliveryList.isEmpty()) {
				throw new RuntimeException("No delivery methods available.");
			}

			// Gửi thông tin đến JSP
			req.setAttribute("user", user); // Truyền thông tin người dùng
			req.setAttribute("cart", cart);
			req.setAttribute("store", store);
			req.setAttribute("deliveryList", deliveryList);

			req.getRequestDispatcher("/views/placeOrder.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load the order form. Please try again.");
			req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Xử lý logic đặt hàng
			Integer userId = (Integer) req.getSession().getAttribute("userId");
			if (userId == null) {
				resp.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			}

			String address = req.getParameter("address");
			String phone = req.getParameter("phone");
			int deliveryId = Integer.parseInt(req.getParameter("deliveryId"));
			int storeId = Integer.parseInt(req.getParameter("storeId"));

			// Log kiểm tra các tham số đầu vào
			System.out.println("Placing order with details:");
			System.out.println("User ID: " + userId);
			System.out.println("Address: " + address);
			System.out.println("Phone: " + phone);
			System.out.println("Delivery ID: " + deliveryId);
			System.out.println("Store ID: " + storeId);

			Order order = orderService.placeOrder(userId, storeId, deliveryId, address, phone);
			// Xóa giỏ hàng sau khi đặt hàng thành công
			cartService.clearCart(userId);
			resp.sendRedirect(req.getContextPath() + "/home?success=order-placed&orderId=" + order.get_id());
		} catch (Exception e) {
			e.printStackTrace();
			// Log lỗi chi tiết
			System.err.println("Error while placing order: " + e.getMessage());
			req.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
			req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
		}
	}

}