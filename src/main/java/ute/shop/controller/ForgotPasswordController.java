package ute.shop.controller;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.User;
import ute.shop.services.*;
import ute.shop.services.implement.UserServiceImpl;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Chuyển tiếp đến trang quên mật khẩu
		req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		User user = userService.findByEmail(email);

		if (user != null) {
			// Tạo mật khẩu mới ngẫu nhiên
			String newPassword = generateRandomPassword();

			// Cập nhật mật khẩu mới vào cơ sở dữ liệu
			user.setPassword(newPassword);
			userService.update(user);

			// Gửi email với mật khẩu mới cho người dùng bằng hàm sendEmail
			sendEmail(user);

			// Thông báo người dùng kiểm tra email
			req.setAttribute("message", "Mật khẩu mới đã được gửi vào email của bạn.");
			req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Email không tồn tại.");
			req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
		}
	}

	// Phương thức tạo mật khẩu ngẫu nhiên
	private String generateRandomPassword() {
		return UUID.randomUUID().toString().substring(0, 8); // Mật khẩu ngẫu nhiên có độ dài 8 ký tự
	}

	// Phương thức gửi email với mật khẩu mới
	private void sendEmail(User user) {
		boolean test = false;
		String toEmail = user.getEmail();
		String fromEmail = "22162015@student.hcmute.edu.vn"; // Thay bằng email của bạn
		String password = "11892456Ko4@"; // Thay bằng mật khẩu của bạn

		try {
			// Cấu hình các thuộc tính SMTP
			Properties pr = configEmail(new Properties());

			// Tạo session với thông tin đăng nhập email
			Session session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			// Tạo đối tượng message và thiết lập các thông tin
			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mess.setSubject("Comfirm Password");
			mess.setText("Your new Password is: " + user.getPassword());

			// Gửi email
			Transport.send(mess);
			test = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Cấu hình các thuộc tính SMTP
	public Properties configEmail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.socketFactory.port", "587");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return pr;
	}
}
