<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>
    <h2 class="header-title">Kết quả tìm kiếm người dùng : "${keywords}"</h2>

    <!-- Bộ lọc -->
    <form action="${pageContext.request.contextPath}/home/searchUser" method="get" class="filter-form">
        <input type="hidden" name="keywords" value="${param.keywords}">
        <label for="onlyVerified" class="filter-label">
            <input type="checkbox" name="onlyVerified" value="true" class="filter-checkbox" ${param.onlyVerified ? 'checked' : ''}> Only Verified Users
        </label>
        <button type="submit" class="filter-button">Apply Filter</button>
    </form>

    <!-- Hiển thị danh sách người dùng -->
    <c:if test="${not empty users}">
        <ul class="user-list">
            <c:forEach var="user" items="${users}">
                <li>
                    <a href="${pageContext.request.contextPath}/home/userDetail?id=${user._id}">
                        ${user.firstname} ${user.lastname}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <!-- Phân trang -->
    <c:if test="${totalPages > 1}">
        <nav class="pagination">
            <ul>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/home/searchUser?keywords=${param.keywords}&page=${i}&onlyVerified=${param.onlyVerified}">
                            ${i}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>

    <!-- Thông báo nếu không tìm thấy người dùng -->
    <c:if test="${empty users}">
        <p class="no-users-message">No users found.</p>
    </c:if>
</body>
</html>