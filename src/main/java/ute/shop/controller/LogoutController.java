package ute.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Xóa session của người dùng
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate(); // Xóa toàn bộ session
		}

		// Nếu có cookie "Remember Me", xóa nó
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("email".equals(cookie.getName())) {
					cookie.setMaxAge(0); // Xóa cookie bằng cách set age = 0
					cookie.setPath("/"); // Đảm bảo cookie có thể xóa trên toàn bộ website
					resp.addCookie(cookie);
				}
			}
		}

		// Chuyển hướng về trang login sau khi đăng xuất
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
