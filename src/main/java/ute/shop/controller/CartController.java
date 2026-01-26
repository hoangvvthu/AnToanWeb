package ute.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Cart;
import ute.shop.entity.CartItem;
import ute.shop.entity.Order;
import ute.shop.entity.OrderItem;
import ute.shop.entity.User;
import ute.shop.services.ICartService;
import ute.shop.services.IProductService;
import ute.shop.services.implement.CartServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;
import ute.shop.services.IStoreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = { "/cart", "/cart/add", "/cart/update", "/cart/remove", "/cart/view" })
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ICartService cartService = new CartServiceImpl();
	private final IProductService productService = new ProductServiceImpl();
	private final IStoreService storeService = new StoreServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/cart":
		case "/cart/view":
			viewCart(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/home");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/cart/add":
			addToCart(req, resp);
			break;
		case "/cart/update":
			updateCart(req, resp);
			break;
		case "/cart/remove":
			removeFromCart(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/home");
			break;
		}
	}

	private void viewCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    User currentUser = (User) req.getSession().getAttribute("account");
	    if (currentUser == null) {
	        resp.sendRedirect(req.getContextPath() + "/login");
	        return;
	    }

	    Cart cart = cartService.findCartByUserId(currentUser.get_id()); // không throw

	    if (cart == null) {
	        cart = new Cart();
	        cart.setCartItems(new java.util.ArrayList<>()); // giỏ trống để JSP render
	    }

	    req.setAttribute("cart", cart);
	    req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
	}


	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User currentUser = (User) req.getSession().getAttribute("account");

		// Kiểm tra người dùng đã đăng nhập chưa
		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		try {
			// Lấy thông tin từ request
			int productId = Integer.parseInt(req.getParameter("productId"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));

			// Kiểm tra số lượng hợp lệ
			if (quantity <= 0) {
				throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
			}

			// Thêm sản phẩm vào giỏ hàng thông qua service
			Cart updatedCart = cartService.addOrUpdateCartItem(currentUser.get_id(), productId, quantity);

			// Cập nhật giỏ hàng trong session
			req.getSession().setAttribute("cart", updatedCart);

			// Chuyển hướng về trang giỏ hàng
			resp.sendRedirect(req.getContextPath() + "/cart/view?success=product-added");
		} catch (NumberFormatException e) {
			// Xử lý lỗi nếu productId hoặc quantity không phải là số
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=Invalid input format");
		} catch (IllegalArgumentException e) {
			// Xử lý lỗi nếu input không hợp lệ
			resp.sendRedirect(req.getContextPath() + "/error?message=" + e.getMessage());
		} catch (Exception e) {
			// Xử lý các lỗi khác
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=Unexpected error occurred");
		}
	}

	private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User currentUser = (User) req.getSession().getAttribute("account");

		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		try {
			int productId = Integer.parseInt(req.getParameter("productId"));
			int newCount = Integer.parseInt(req.getParameter("count"));

			if (newCount <= 0) {
				throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
			}

			Cart updatedCart = cartService.addOrUpdateCartItem(currentUser.get_id(), productId, newCount);
			req.getSession().setAttribute("cart", updatedCart);
			resp.sendRedirect(req.getContextPath() + "/cart/view");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=InvalidInput");
		} catch (IllegalArgumentException e) {
			resp.sendRedirect(req.getContextPath() + "/error?message=" + e.getMessage());
		}
	}

	private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User currentUser = (User) req.getSession().getAttribute("account");

		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		try {
			// Lấy productId từ request
			int productId = Integer.parseInt(req.getParameter("productId"));
			System.out.println("Attempting to remove product ID: " + productId);

			// Xóa sản phẩm khỏi giỏ hàng bằng service
			cartService.removeCartItem(currentUser.get_id(), productId);

			// Lấy lại giỏ hàng đã cập nhật
			Cart updatedCart = cartService.getCartByUser(currentUser);
			System.out.println("Updated cart size: "
					+ (updatedCart.getCartItems() != null ? updatedCart.getCartItems().size() : 0));

			// Cập nhật lại session
			req.getSession().setAttribute("cart", updatedCart);

			// Chuyển hướng về trang giỏ hàng
			resp.sendRedirect(req.getContextPath() + "/cart/view");
		} catch (NumberFormatException e) {
			// Xử lý lỗi nếu `productId` không phải là số
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=Invalid product ID format");
		} catch (IllegalArgumentException e) {
			// Xử lý lỗi nếu `productId` không hợp lệ
			resp.sendRedirect(req.getContextPath() + "/error?message=" + e.getMessage());
		} catch (Exception e) {
			// Xử lý các lỗi khác
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=Unexpected error occurred");
		}
	}

	private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User currentUser = (User) req.getSession().getAttribute("account");

		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		try {
			cartService.clearCart(currentUser.get_id());
			req.getSession().removeAttribute("cart"); // Xóa giỏ hàng khỏi session
			resp.sendRedirect(req.getContextPath() + "/cart/view?success=cart-cleared");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/error?message=Unable to clear cart");
		}
	}
}
