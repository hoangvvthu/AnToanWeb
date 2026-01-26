<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:forEach var="product" items="${products}">
	<div class="col-sm-4">
		<div class="product-image-wrapper">
			<div class="single-products">
				<div class="productinfo text-center">
					<img src="${pageContext.request.contextPath}/images/shop/1001.jpg" 
					     alt="" style="width: 260px; height: 260px" />
					<h2>${product.price}$</h2>
					<p>${product.name}</p>
					<a href="${pageContext.request.contextPath}/home/productDetail?id=${product._id}" 
					   class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
				</div>
			</div>
			<div class="choose">
				<ul class="nav nav-pills nav-justified">
					<li>
						<a href="${pageContext.request.contextPath}/home/productDetail?id=${product._id}">
							<i class="fa fa-plus-square"></i>Views Details
						</a>
					</li>
					<li>
						<a href="/home/cart/add/${product._id}">
							<i class="fa fa-plus-square"></i>Add to cart 
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</c:forEach>
