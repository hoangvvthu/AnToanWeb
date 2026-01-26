package ute.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ute.shop.entity.User;
import ute.shop.services.*;
import ute.shop.services.implement.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/upload-avatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10) // 10MB
public class UploadAvatarController extends HttpServlet {
	private static final String UPLOAD_DIR = "images";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

		// Tạo thư mục nếu chưa tồn tại
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// Lấy tệp từ request
			Part part = request.getPart("avatar");
			if (part != null && part.getSize() > 0) {
				String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				String filePath = uploadPath + File.separator + fileName;

				// Lưu tệp vào thư mục upload
				part.write(filePath);

				// Cập nhật thông tin vào cơ sở dữ liệu (tùy vào logic)
				// Giả sử bạn đã có đối tượng user:
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("account");
				if (user != null) {
					user.setAvatar(fileName);
					IUserService userService = new UserServiceImpl();
					userService.update(user);
				}

				response.sendRedirect(request.getContextPath() + "/account");
			} else {
				request.setAttribute("error", "Chưa chọn tệp hoặc tệp không hợp lệ.");
				request.getRequestDispatcher("/views/account.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Lỗi trong quá trình upload ảnh", e);
		}
	}
}
