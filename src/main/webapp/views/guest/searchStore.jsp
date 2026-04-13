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
	<p class="search-results">Kết quả cho từ khóa: "<c:out value="${keywords}" />"</p>

	<div class="store-list-container">
		<c:forEach var="store" items="${stores}">
			<div class="store-card-item">
				<img src="${pageContext.request.contextPath}/images/store/${store.avatar}" alt="<c:out value="${store.name}"/>"
					class="store-avatar-image">
				<h2 class="store-name"><c:out value="${store.name}"/></h2>
				<p class="store-bio"><c:out value="${store.bio}"/></p>
				<a
					href="${pageContext.request.contextPath}/home/storeDetail?id=${store._id}"
					class="store-detail-link">Xem chi tiết</a>
			</div>
		</c:forEach>
	</div>

	<div class="pagination-container">
		<c:if test="${currentPage > 1}">
			<c:url var="storePrevUrl" value="/home/searchStore">
				<c:param name="keywords" value="${keywords}"/>
				<c:param name="page" value="${currentPage - 1}"/>
			</c:url>
			<a href="${storePrevUrl}"
				class="pagination-link">Previous</a>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="page">
			<c:url var="storePageUrl" value="/home/searchStore">
				<c:param name="keywords" value="${keywords}"/>
				<c:param name="page" value="${page}"/>
			</c:url>
			<a href="${storePageUrl}"
				class="pagination-number">${page}</a>
		</c:forEach>
		<c:if test="${currentPage < totalPages}">
			<c:url var="storeNextUrl" value="/home/searchStore">
				<c:param name="keywords" value="${keywords}"/>
				<c:param name="page" value="${currentPage + 1}"/>
			</c:url>
			<a href="${storeNextUrl}"
				class="pagination-link">Next</a>
		</c:if>
	</div>

</body>
</html>