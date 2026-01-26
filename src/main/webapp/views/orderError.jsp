<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Order Error</title>
</head>
<body>
	<h1>Error Placing Your Order</h1>
	<p>There was an issue with placing your order. Please try again
		later.</p>
	<a href="${pageContext.request.contextPath}/cart">Go back to Cart</a>
</body>
</html>
