package ute.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.services.implement.DeliveryServiceImpl;
import ute.shop.entity.Cart;
import ute.shop.entity.CartItem;
import ute.shop.entity.Commission;
import ute.shop.entity.Delivery;
import ute.shop.entity.Order;
import ute.shop.entity.OrderItem;
import ute.shop.entity.OrderStatus;
import ute.shop.entity.Product;
import ute.shop.entity.Store;
import ute.shop.entity.User;
import ute.shop.services.IOrderService;
import ute.shop.services.ICommissonService;
import ute.shop.services.IDeliveryService;
import ute.shop.services.IStoreService;
import ute.shop.services.IProductService;
import ute.shop.services.ICartService;
import ute.shop.services.IUserService;
import ute.shop.services.implement.CartServiceImpl;
import ute.shop.services.implement.CommissionServiceImpl;
import ute.shop.services.implement.OrderServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;
import ute.shop.services.implement.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/orders", "/orders/cancel", "/orders/status" })
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IOrderService orderService = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/orders":
			showOrderHistory(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/orders/cancel":
			cancelOrder(req, resp);
			break;
		case "/orders/status":
			updateOrderStatus(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int orderId = Integer.parseInt(req.getParameter("orderId"));

			boolean isCanceled = orderService.cancelOrder(orderId);

			if (isCanceled) {
				resp.sendRedirect(req.getContextPath() + "/orders?success=cancel");
			} else {
				resp.sendRedirect(req.getContextPath() + "/orders?error=cancel");
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect(req.getContextPath() + "/orders?error=invalid");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/orders?error=cancel");
		}
	}

	private void updateOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			String status = req.getParameter("status");

			if (status == null || status.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/orders?error=invalid-status");
				return;
			}

			boolean isUpdated = orderService.updateOrderStatus(orderId, OrderStatus.valueOf(status.toUpperCase()));

			if (isUpdated) {
				resp.sendRedirect(req.getContextPath() + "/orders?success=update");
			} else {
				resp.sendRedirect(req.getContextPath() + "/orders?error=update");
			}
		} catch (IllegalArgumentException e) {
			resp.sendRedirect(req.getContextPath() + "/orders?error=invalid");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/orders?error=update");
		}
	}

	private void showOrderHistory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer userId = (Integer) req.getSession().getAttribute("userId");

		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}

		try {
			List<Order> orders = orderService.getAllOrdersByUser(userId);
			req.setAttribute("orders", orders);
			req.getRequestDispatcher("/views/order-history.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch order history.");
		}
	}
}
