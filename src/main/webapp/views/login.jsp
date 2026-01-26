<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login or Signup</title>
<link rel="stylesheet" href="path/to/your/styles.css">
<!-- Link file CSS -->
</head>

<body>
	<div class="form_wrapper">
		<div class="form_container">
			<div class="title_container">
				<h2>Welcome Back</h2>
				<p>Login to your account</p>
			</div>
			<c:if test="${alert != null}">
				<div class="message">
					<p style="color: red;">${alert}</p>
				</div>
			</c:if>
			<div class="row clearfix">
				<form action="${pageContext.request.contextPath}/login" method="post">
					<div class="input_field">
						<span><i aria-hidden="true" class="fa fa-envelope"></i></span> <input
							type="email" name="email" placeholder="Email Address" required />
					</div>
					<div class="input_field">
						<span><i aria-hidden="true" class="fa fa-lock"></i></span> <input
							type="password" name="password" placeholder="Password" required />
					</div>
					<div class="checkbox_field">
						<input type="checkbox" name="keepSignedIn"> <label>Keep
							me signed in</label>
					</div>
					<input class="button" type="submit" value="Login" />
				</form>
			</div>
			<div style="text-align: center; margin-top: 10px;">
				<a href="${pageContext.request.contextPath}/forgot-password"
					style="color: #007bff; text-decoration: none;">Forgot Password?</a>
			</div>
		</div>
		<p class="credit">
			Don't have an account? <a
				href="${pageContext.request.contextPath}/register">Signup</a>
		</p>
	</div>
</body>
</html>
