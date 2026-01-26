<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html lang="en">

<body>
	<header id="header">
		<section id="advertisement">
			<div class="container">
				<img src="images/shop/advertisement.jpg" alt="" />
			</div>
		</section>

		<section>
			<div class="container">
				<div class="row">
					<div class="col-sm-3">
						<div class="left-sidebar">
							<h2>Category</h2>
							<form class="product-search-form" method="get"
								action="${pageContext.request.contextPath}/home/searchProduct">
								<input type="hidden" name="keywords" value="${keywords}">

								<label class="form-label" for="category">Danh mục:</label> <select
									class="form-select" name="category">
									<option value="">Tất cả</option>
									<!-- Điền danh mục động -->
									<c:forEach var="category" items="${categories}">
										<option value="${category._id}"
											<c:if test="${param.category == category._id}">selected</c:if>>${category.name}</option>
									</c:forEach>
								</select> <label class="form-label" for="minPrice">Giá tối thiểu:</label>
								<input class="form-input" type="number" name="minPrice"
									value="${param.minPrice}"> <label class="form-label"
									for="maxPrice">Giá tối đa:</label> <input class="form-input"
									type="number" name="maxPrice" value="${param.maxPrice}">

								<button class="form-button" type="submit">Lọc</button>
							</form>


							<!--/category-productsr-->



							<!--/price-range-->

							<div class="shipping text-center">
								<!--shipping-->
								<img
									src="${pageContext.request.contextPath}/images/home/shipping.jpg"
									alt="" />
							</div>
							<!--/shipping-->

						</div>
					</div>

					<div class="col-sm-9 padding-right">
						<div class="features_items">
							<!--features_items-->
							<h2 class="title text-center">Danh sách sản phẩm</h2>
							<c:forEach var="product" items="${products}">
								<div class="col-sm-4">
									<div class="product-image-wrapper">
										<div class="single-products">
											<div class="productinfo text-center">
												<img
													src="${pageContext.request.contextPath}/images/product/${product.firstImage}"
													alt="${product.name}" style="width: 260px; height: 260px" />
												<h2>${product.price}$</h2>
												<p>${product.name}</p>
												<a
													href="${pageContext.request.contextPath}/home/productDetail?id=${product._id}"
													class="btn btn-default add-to-cart"><i
													class="fa fa-shopping-cart"></i>Add to cart</a>
											</div>
										</div>
										<div class="choose">
											<ul class="nav nav-pills nav-justified">
												<li><a
													href="${pageContext.request.contextPath}/home/productDetail?id=${product._id}"><i
														class="fa fa-plus-square"></i>Views Details</a></li>
												<li><a href="/home/cart/add/${item.id}"><i
														class="fa fa-plus-square"></i>Add to cart </a></li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
						<center>
							<%-- <a style="font-size: 20px" class=" btn btn-warning"
								href="/home/product?p=0">First</a> <a style="font-size: 20px"
								class="btn btn-warning" href="/home/product?p=${page.number-1}">Previous</a>
							<a style="font-size: 20px" class="btn btn-warning"
								href="/home/product?p=${page.number+1}">Next</a> <a
								style="font-size: 20px" class="btn btn-warning"
								href="/home/product?p=${page.totalPages-1}">Last</a>
							<!--features_items <ul class="pagination">
<li class="active"><a href="/home/product?p=0">1</a></li>
<li><a href="/home/product?p=1">2</a></li>
<li><a href="/home/product?p=2">3</a></li>
<li><a href="/home/product?p=${page.number+1}">&raquo;</a></li>
</ul>--> --%>
							<!-- Phân trang -->
							<div>
								<c:if test="${currentPage > 1}">
									<a style="font-size: 20px" class=" btn btn-warning"
										href="?keywords=${keywords}&page=${currentPage - 1}">Previous</a>
								</c:if>
								<c:forEach begin="1" end="${totalPages}" var="page">
									<a style="font-size: 20px" class=" btn btn-warning"
										href="?keywords=${keywords}&page=${page}">${page}</a>
								</c:forEach>
								<c:if test="${currentPage < totalPages}">
									<a style="font-size: 20px" class=" btn btn-warning"
										href="?keywords=${keywords}&page=${currentPage + 1}">Next</a>
								</c:if>

							</div>
						</center>
						<br>
					</div>
				</div>
			</div>
		</section>




		<script src="js/jquery.js"></script>
		<script src="js/price-range.js"></script>
		<script src="js/jquery.scrollUp.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.prettyPhoto.js"></script>
		<script src="js/main.js"></script>
</body>
</html>
