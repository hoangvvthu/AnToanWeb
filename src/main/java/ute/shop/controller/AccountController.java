package ute.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ute.shop.entity.User;

@WebServlet(urlPatterns = "/account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User account = (User) session.getAttribute("account");

		// Kiểm tra nếu chưa đăng nhập, điều hướng về trang đăng nhập
		if (account == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Nếu đã đăng nhập, điều hướng tới trang account.jsp
		req.setAttribute("account", account);
		req.getRequestDispatcher("/views/account.jsp").forward(req, resp);
	}
}