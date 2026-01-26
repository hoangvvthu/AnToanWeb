<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body style="font-family: Arial, sans-serif; margin: 0; padding: 20px">

	<h1 style="text-align: center; font-size: 2em; color: #333;">Your
		Followed Products</h1>

	<c:if test="${not empty error}">
		<p
			style="color: red; font-weight: bold; text-align: center; margin-top: 10px;">${error}</p>
	</c:if>
	<c:if test="${not empty success}">
		<p
			style="color: green; font-weight: bold; text-align: center; margin-top: 10px;">${success}</p>
	</c:if>

	<div style="max-width: 800px; margin: 20px auto;">
		<c:forEach var="followedProduct" items="${followedProducts}">
			<div
				style="display: flex; background: #fff; border: 1px solid #ddd; padding: 15px; margin: 10px 0; align-items: center; border-radius: 8px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);">

				<div style="flex-grow: 1;">
					<h3 style="margin: 0; font-size: 1.5em; color: #007bff;">${followedProduct.product.name}</h3>
					<p style="margin: 8px 0; font-size: 1em; color: #555;">
						Price: <strong style="color: #28a745;">${followedProduct.product.price}
							USD</strong>
					</p>
					<p style="margin: 8px 0; font-size: 0.9em; color: #777;">Description:
						${followedProduct.product.description}</p>
					<p style="margin: 8px 0; font-size: 0.9em; color: #777;">Sold:
						${followedProduct.product.sold}</p>
				</div>
				<button onclick="removeFollow(${followedProduct.product._id})"
					style="padding: 10px 15px; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 0.9em;">
					Unfollow</button>
			</div>
		</c:forEach>
	</div>

	<script>
    function removeFollow(productId) {
        if (confirm("Are you sure you want to unfollow this product?")) {
            window.location.href = `${pageContext.request.contextPath}/user/unfollow?productId=${productId}`;
        }
    }
</script>

</body>
</html>
