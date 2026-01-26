package ute.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.User;
import ute.shop.services.*;
import ute.shop.services.implement.UserServiceImpl;

@WebServlet(urlPatterns = "/change-password")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");

		User account = (User) req.getSession().getAttribute("account");

		if (account != null) {
			if (!account.getPassword().equals(oldPassword)) {
				req.setAttribute("message", "Mật khẩu cũ không chính xác.");
				req.getRequestDispatcher("/views/account.jsp").forward(req, resp);
				return;
			}

			if (!newPassword.equals(confirmPassword)) {
				req.setAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
				req.getRequestDispatcher("/views/account.jsp").forward(req, resp);
				return;
			}

			// Cập nhật mật khẩu mới
			account.setPassword(newPassword);
			userService.update(account);

			// Xóa thông tin người dùng trong session
			req.getSession().removeAttribute("account");

			// Thêm thông báo thành công
			req.setAttribute("message", "Mật khẩu đã được thay đổi thành công. Vui lòng đăng nhập lại.");

			// Chuyển hướng đến trang đăng nhập
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "Vui lòng đăng nhập trước khi thay đổi mật khẩu.");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}
}