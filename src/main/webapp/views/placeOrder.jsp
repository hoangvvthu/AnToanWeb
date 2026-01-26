<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Place Order</title>
</head>
<body>
	<h1>Place Your Order</h1>

	<!-- Form đặt hàng -->
	<form action="${pageContext.request.contextPath}/orders/place"
		method="post">
		<!-- Hiển thị địa chỉ -->
		<label for="address">Address:</label> <input type="text" id="address"
			name="address" value="${not empty user.address ? user.address : ''}"
			required placeholder="Enter your address" /> <br>
		<!-- Hiển thị số điện thoại -->
		<label for="phone">Phone:</label> <input type="text" id="phone"
			name="phone" value="${not empty user.phone ? user.phone : ''}"
			required placeholder="Enter your phone number" /> <br>
		<!-- Phương thức giao hàng -->
		<label for="deliveryId">Delivery Method:</label> <select
			id="deliveryId" name="deliveryId" required>
			<c:forEach var="delivery" items="${deliveryList}">
				<option value="${delivery._id}">${delivery.name}-
					${delivery.price} USD</option>
			</c:forEach>
		</select> <input type="hidden" name="storeId" value="${store._id}" /> <br>
		<button type="submit">Place Order</button>
	</form>
</body>
</html>
