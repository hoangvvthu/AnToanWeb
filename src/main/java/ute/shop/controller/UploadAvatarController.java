package ute.shop.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ute.shop.entity.User;
import ute.shop.services.IUserService;
import ute.shop.services.implement.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/upload-avatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class UploadAvatarController extends HttpServlet {
	private static final String UPLOAD_DIR = "images";
	private static final String AVATAR_SUB_DIR = "user";
	private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");
	private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("image/jpeg", "image/png", "image/gif",
			"image/webp");

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = session != null ? (User) session.getAttribute("account") : null;
		if (user == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login before uploading an avatar.");
			return;
		}

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR + File.separator
				+ AVATAR_SUB_DIR;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		try {
			Part part = request.getPart("avatar");
			if (part == null || part.getSize() <= 0) {
				request.setAttribute("error", "Chua chon tep hoac tep khong hop le.");
				request.getRequestDispatcher("/views/account.jsp").forward(request, response);
				return;
			}

			String submittedFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			String extension = extractExtension(submittedFileName);
			String contentType = part.getContentType();

			if (!ALLOWED_EXTENSIONS.contains(extension) || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
						"Only JPG, PNG, GIF or WEBP images are allowed.");
				return;
			}

			try (InputStream input = part.getInputStream()) {
				if (!matchesExpectedSignature(input, extension)) {
					response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
							"Uploaded file content does not match its image type.");
					return;
				}
			}

			String safeFileName = UUID.randomUUID() + "." + extension;
			Path targetPath = Path.of(uploadPath, safeFileName);
			try (InputStream input = part.getInputStream()) {
				Files.copy(input, targetPath, StandardCopyOption.REPLACE_EXISTING);
			}

			user.setAvatar(AVATAR_SUB_DIR + "/" + safeFileName);
			IUserService userService = new UserServiceImpl();
			userService.update(user);
			session.setAttribute("account", user);

			response.sendRedirect(request.getContextPath() + "/account");
		} catch (Exception e) {
			throw new ServletException("Loi trong qua trinh upload anh", e);
		}
	}

	private String extractExtension(String fileName) {
		int lastDot = fileName.lastIndexOf('.');
		if (lastDot < 0 || lastDot == fileName.length() - 1) {
			return "";
		}
		return fileName.substring(lastDot + 1).toLowerCase(Locale.ROOT);
	}

	private boolean matchesExpectedSignature(InputStream input, String extension) throws IOException {
		byte[] header = input.readNBytes(12);
		return switch (extension) {
			case "jpg", "jpeg" -> header.length >= 3
					&& (header[0] & 0xFF) == 0xFF
					&& (header[1] & 0xFF) == 0xD8
					&& (header[2] & 0xFF) == 0xFF;
			case "png" -> header.length >= 8
					&& (header[0] & 0xFF) == 0x89
					&& header[1] == 0x50
					&& header[2] == 0x4E
					&& header[3] == 0x47
					&& header[4] == 0x0D
					&& header[5] == 0x0A
					&& header[6] == 0x1A
					&& header[7] == 0x0A;
			case "gif" -> header.length >= 6
					&& header[0] == 0x47
					&& header[1] == 0x49
					&& header[2] == 0x46
					&& header[3] == 0x38
					&& (header[4] == 0x37 || header[4] == 0x39)
					&& header[5] == 0x61;
			case "webp" -> header.length >= 12
					&& header[0] == 0x52
					&& header[1] == 0x49
					&& header[2] == 0x46
					&& header[3] == 0x46
					&& header[8] == 0x57
					&& header[9] == 0x45
					&& header[10] == 0x42
					&& header[11] == 0x50;
			default -> false;
		};
	}
}