<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<body style="font-family: Arial, sans-serif; margin: 0; padding: 0;">
	<div class="container"
		style="max-width: 600px; margin: 100px auto; background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); text-align: center;">
		<h2 class="my-4" style="color: #333;">Khôi phục mật khẩu</h2>

		<!-- Hiển thị thông báo nếu có lỗi -->
		<c:if test="${alert != null}">
			<div class="alert alert-danger" role="alert"
				style="background-color: #f8d7da; color: #721c24; padding: 15px; border-radius: 5px; border: 1px solid #f5c6cb; margin-bottom: 20px;">
				${alert}</div>
		</c:if>

		<!-- Form nhập email để lấy mật khẩu mới -->
		<form action="${pageContext.request.contextPath}/forgot-password"
			method="post" style="display: inline-block; text-align: left;">
			<div class="form-group" style="margin-bottom: 20px;">
				<label for="email" style="font-size: 16px; color: #333;">Nhập
					email của bạn để nhận mật khẩu mới:</label> <input type="email" id="email"
					name="email" class="form-control"
					style="padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px;"
					required>
			</div>
			<button type="submit" class="btn btn-primary"
				style="padding: 12px 20px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;">
				Gửi liên kết tạo mật khẩu mới</button>
		</form>

		<!-- Liên kết quay lại trang đăng nhập nếu người dùng đã có tài khoản -->
		<div class="mt-3" style="margin-top: 20px; text-align: center;">
			<a href="${pageContext.request.contextPath}/login"
				style="color: #007bff; text-decoration: none;">Quay lại trang
				đăng nhập</a>
		</div>
	</div>
</body>
