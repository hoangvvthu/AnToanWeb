<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Order Success</title>
</head>
<body>
	<h1>Your order has been placed successfully!</h1>
	<p>Your order will be processed shortly.</p>
	<a href="${pageContext.request.contextPath}/orders">Go to Order
		History</a>
</body>
</html>
