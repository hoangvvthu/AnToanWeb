<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Detail</title>
</head>
<body>
    <h2 class="user-details-title">Thông tin chi tiết người dùng:</h2>

    <c:if test="${not empty user}">
        <div class="user-details">
            <p class="user-detail-item"><span class="user-detail-label">First Name:</span> ${user.firstname}</p>
            <p class="user-detail-item"><span class="user-detail-label">Last Name:</span> ${user.lastname}</p>
            <p class="user-detail-item"><span class="user-detail-label">Email:</span> ${user.email}</p>
            <p class="user-detail-item"><span class="user-detail-label">Phone:</span> ${user.phone}</p>
            <p class="user-detail-item"><span class="user-detail-label">ID Card:</span> ${user.id_card}</p>
            <p class="user-detail-item"><span class="user-detail-label">Address:</span> ${user.address}</p>
            <p class="user-detail-item"><span class="user-detail-label">Role:</span> ${user.role}</p>
            <p class="user-detail-item"><span class="user-detail-label">Email Verified:</span> ${user.isEmailActive ? "Yes" : "No"}</p>
            <p class="user-detail-item"><span class="user-detail-label">Phone Verified:</span> ${user.isPhoneActive ? "Yes" : "No"}</p>
            <img src="${pageContext.request.contextPath}/images/user/${user.avatar}" alt="Avatar" class="user-avatar">
        </div>
        <a href="${pageContext.request.contextPath}/home/searchUser?keywords=${keywords}" class="back-link">Quay về trang danh sách tìm kiếm</a>
    </c:if>
</body>

<%--         <img src="${pageContext.request.contextPath}/uploads/${user.avatar}" alt="Avatar" width="150">
 --%>  
</html>