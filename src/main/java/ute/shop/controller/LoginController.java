package ute.shop.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ute.shop.entity.User;
import ute.shop.services.IUserService;
import ute.shop.services.implement.UserServiceImpl;
import ute.shop.utils.BCryptUtils;
import ute.shop.utils.md5;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login") // Ensure the URL matches the form action
public class LoginController extends HttpServlet {

	private static final String COOKIE_REMEMBER = "email";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String hashedPassword = md5.md5Hex(password);
		boolean isRememberMe = "on".equals(req.getParameter("remember"));

		String alertMsg = "";

		// Kiểm tra input
		if (email.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			return;
		}

		// Gọi UserService để kiểm tra thông tin đăng nhập
		IUserService service = new UserServiceImpl();
		User user = service.login(email, hashedPassword);
		
		if (user != null ) {
			// Lưu thông tin người dùng vào session
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			session.setAttribute("userId", user.get_id());

			// Nếu người dùng chọn "Remember Me"
			if (isRememberMe) {
				saveRemeberMe(resp, email);
			}

			// Phân quyền dựa trên role
			if ("ADMIN".equalsIgnoreCase(user.getRole().toString())) {
				resp.sendRedirect(req.getContextPath() + "/admin/home"); // Chuyển hướng đến trang admin
			} else {
				resp.sendRedirect(req.getContextPath() + "/home"); // Chuyển hướng đến trang người dùng
			}
		} else {
			// Xử lý khi đăng nhập thất bại
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String email) {
		Cookie cookie = new Cookie(COOKIE_REMEMBER, email);
		cookie.setMaxAge(30 * 60); // Cookie tồn tại trong 30 phút
		cookie.setPath("/"); // Cookie có hiệu lực trên toàn bộ website
		response.addCookie(cookie);
	}

}
