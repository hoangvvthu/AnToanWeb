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
    <h2 class="header-title">Kết quả tìm kiếm người dùng : "<c:out value="${keywords}"/>"</h2>

    <!-- Bộ lọc -->
    <form action="${pageContext.request.contextPath}/home/searchUser" method="get" class="filter-form">
        <input type="hidden" name="keywords" value="<c:out value="${param.keywords}"/>">
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
                        <c:out value="${user.firstname}"/> <c:out value="${user.lastname}"/>
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
                        <c:url var="searchUserPageUrl" value="/home/searchUser">
                            <c:param name="keywords" value="${param.keywords}"/>
                            <c:param name="page" value="${i}"/>
                            <c:param name="onlyVerified" value="${param.onlyVerified}"/>
                        </c:url>
                        <a href="${searchUserPageUrl}">
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