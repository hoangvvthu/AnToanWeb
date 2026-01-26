package ute.shop.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Product;
import ute.shop.entity.User;
import ute.shop.entity.UserFollowProduct;
import ute.shop.services.IUserFollowProductService;
import ute.shop.services.implement.UserFollowProductServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/followedProducts", "/user/follow", "/user/unfollow" })
public class UserFollowProductController extends HttpServlet {

	private IUserFollowProductService userFollowProductService = new UserFollowProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		User currentUser = (User) request.getSession().getAttribute("account");

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		try {
			switch (action) {
			case "/user/followedProducts":
				showFollowedProducts(request, response, currentUser);
				break;
			case "/user/unfollow":
				unfollowProduct(request, response, currentUser);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error occurred");
		}
	}

	private void showFollowedProducts(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		int userId = currentUser.get_id(); // Get user ID from session
		List<UserFollowProduct> followedProducts = userFollowProductService.getFollowedProductsByUserId(userId);

		request.setAttribute("followedProducts", followedProducts);

		// Forward to the followedProducts view
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/followedProducts.jsp");
		dispatcher.forward(request, response);
	}

	private void unfollowProduct(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws IOException {
		try {
			// Lấy `productId` từ request
			int productId = Integer.parseInt(request.getParameter("productId"));

			// Kiểm tra xem sản phẩm có được người dùng follow hay không
			if (!userFollowProductService.isProductFollowedByUser(currentUser.get_id(), productId)) {
				response.sendRedirect(request.getContextPath() + "/user/followedProducts?error=not-followed");
				return;
			}

			// Xóa sản phẩm khỏi danh sách yêu thích
			UserFollowProduct userFollowProduct = new UserFollowProduct();
			userFollowProduct.setUser(currentUser);
			userFollowProduct.setProduct(new Product(productId));

			userFollowProductService.unfollowProduct(userFollowProduct);

			// Chuyển hướng về trang danh sách sản phẩm yêu thích
			response.sendRedirect(request.getContextPath() + "/user/followedProducts?success=unfollowed");
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid productId");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/user/followedProducts?error=delete-failed");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fetch the logged-in user from the session
		User currentUser = (User) request.getSession().getAttribute("account");

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		String action = request.getParameter("action");
		if ("unfollow".equals(action)) {
			unfollowProduct(request, response, currentUser);
		} else {
			try {
				int userId = currentUser.get_id(); // Get user ID from session
				int productId = Integer.parseInt(request.getParameter("productId"));

				if (userFollowProductService.isProductFollowedByUser(userId, productId)) {
					response.sendRedirect(request.getContextPath() + "/user/followedProducts?error=already-followed");
					return;
				}

				UserFollowProduct userFollowProduct = new UserFollowProduct();
				userFollowProduct.setUser(currentUser);
				userFollowProduct.setProduct(new Product(productId));

				userFollowProductService.followProduct(userFollowProduct);
				response.sendRedirect(request.getContextPath() + "/user/followedProducts?success=followed");
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid productId");
			}
		}
	}

}
