<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/store.css">
<title>Search Stores</title>

</head>
<body>
	<h1 class="search-title">Tìm kiếm cửa hàng</h1>
	<p class="search-results">Kết quả cho từ khóa: "${keywords}"</p>

	<div class="store-list-container">
		<c:forEach var="store" items="${stores}">
			<div class="store-card-item">
				<img src="${pageContext.request.contextPath}/images/store/${store.avatar}" alt="${store.name}"
					class="store-avatar-image">
				<h2 class="store-name">${store.name}</h2>
				<p class="store-bio">${store.bio}</p>
				<a
					href="${pageContext.request.contextPath}/home/storeDetail?id=${store._id}"
					class="store-detail-link">Xem chi tiết</a>
			</div>
		</c:forEach>
	</div>

	<div class="pagination-container">
		<c:if test="${currentPage > 1}">
			<a href="?keywords=${keywords}&page=${currentPage - 1}"
				class="pagination-link">Previous</a>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="page">
			<a href="?keywords=${keywords}&page=${page}"
				class="pagination-number">${page}</a>
		</c:forEach>
		<c:if test="${currentPage < totalPages}">
			<a href="?keywords=${keywords}&page=${currentPage + 1}"
				class="pagination-link">Next</a>
		</c:if>
	</div>

</body>
</html>