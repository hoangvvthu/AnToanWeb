<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/store.css">
<title>${store.name}</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

h1, h2 {
	color: #333;
	text-align: center;
}

.store-avatar {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	border: 3px solid #4CAF50;
	margin: 20px auto;
	display: block;
}

p {
	text-align: center;
	color: #666;
	line-height: 1.6;
}

.store-list {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: center;
}

.store-card {
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 20px;
	width: 300px;
	text-align: center;
	background-color: white;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s;
}

.store-card:hover {
	transform: scale(1.05);
}

.slider {
	display: flex;
	overflow-x: auto;
	padding: 10px;
	justify-content: center;
}

.slider img {
	width: 300px;
	height: auto;
	border-radius: 8px;
	margin: 0 10px;
	transition: transform 0.2s;
}

.slider img:hover {
	transform: scale(1.05);
}

.review {
	border: 1px solid #ddd;
	border-radius: 8px;
	margin: 10px 0;
	padding: 15px;
	background-color: #fff;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.review p {
	margin: 5px 0;
	color: #444;
}

ul {
	list-style-type: none;
	padding: 0;
	text-align: center;
}

li {
	background-color: #e7f3fe;
	margin: 5px 0;
	padding: 10px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<h1 class="store-title">${store.name}</h1>
	<img src="${pageContext.request.contextPath}/images/store/${store.avatar}" alt="${store.name}" class="store-avatar">
	<p class="store-bio">${store.bio}</p>

	<h2 class="staff-title">Nhân viên</h2>
	<ul class="staff-list">
		<c:forEach var="staff" items="${store.staffs}">
			<li class="staff-item">${staff.firstname}${staff.lastname}</li>
		</c:forEach>
	</ul>

	<h2 class="slider-title">Slider</h2>
	<div class="image-slider">
		<c:forEach var="image" items="${store.featuredImages}">
			<img src="${image}" alt="${store.name}" class="slider-image" style="width: 84px; height: 84px;">
		</c:forEach>
	</div>

	<h2 class="review-title">Đánh giá</h2>
	<div class="review-container">
		<c:forEach var="review" items="${store.reviews}">
			<div class="review-item">
				<p class="review-content">${review.content}</p>
				<p class="review-user">Người đánh giá: ${review.user.firstname}
					${review.user.lastname}</p>
			</div>
		</c:forEach>
	</div>

	<h2 class="product-title">Sản phẩm</h2>
	<ul class="product-list">
		<c:forEach var="product" items="${store.products}">
			<li class="product-item"><a
				href="${pageContext.request.contextPath}/home/productDetail?id=${product._id}"
				class="product-link">${product.name} - ${product.price}$</a></li>
		</c:forEach>
	</ul>
</body>
</html>