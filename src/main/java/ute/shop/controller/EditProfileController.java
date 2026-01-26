package ute.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ute.shop.entity.User;
import ute.shop.services.*;
import ute.shop.services.implement.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/edit-profile")
public class EditProfileController extends HttpServlet {
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy thông tin người dùng từ session
		HttpSession session = req.getSession();
		User account = (User) session.getAttribute("account");

		if (account != null) {
			req.setAttribute("account", account);
			req.getRequestDispatcher("/views/edit-profile.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Xử lý lưu thông tin chỉnh sửa
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");

		HttpSession session = req.getSession();
		User account = (User) session.getAttribute("account");

		if (account != null) {
			account.setEmail(email);
			account.setPhone(phone);
			account.setAddress(address);

			userService.update(account); // Cập nhật thông tin người dùng trong DB

			// Cập nhật lại thông tin trong session
			session.setAttribute("account", account);
			resp.sendRedirect(req.getContextPath() + "/account"); // Điều hướng về trang thông tin tài khoản
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
