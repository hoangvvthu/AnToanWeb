<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<div id="page-manager"></div>

<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>
					Datatables <small> datatables</small>
				</h1>
			</div>
			<!-- END PAGE TITLE -->
		</div>
	</div>
	<!-- END PAGE HEAD -->
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content">
		<div class="container">

			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">


					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Products
									tables</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a> <a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover"
								id="sample_2">
								<thead>
									<tr>
										<th colspan="5" style="text-align: left;"><a
											href="${pageContext.request.contextPath}/admin/addProduct" class="btn btn-primary">Add Product</a></th>
									</tr>
									<tr>
										<th>ID Store</th>
										<th>Name</th>
										<th>Price</th>
										<th>Description</th>
										<th>isActive</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="product" items="${proList}">
										<tr>
											<td>${product.store._id}</td>

											<td><a href="productDetails?id=${product._id}">${product.name}</a>
											</td>

											<td>${product.price}</td>

											<td>${product.description}</td>

											<td><a href="deleteProduct?id=${product._id}">${product.isActive ? 'Yes' : 'No'}</a></td>

										</tr>


									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->




					<!-- BEGIN EXAMPLE TABLE PORTLET 3-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Stores
									table</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a> <a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover"
								id="sample_3">
								<thead>
								<tr>
										<th colspan="5" style="text-align: left;"><a
											href="${pageContext.request.contextPath}/admin/addStore" class="btn btn-primary">Add Store</a></th>
									</tr>
									<tr>
										<th>ID</th>
										<th>Bio</th>
										<th>Name</th>
										<th>Slug</th>
										<th>isActive</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="store" items="${stoList}">
										<tr>
											<td><a href="storeDetails?id=${store._id}">${store._id}</a>
											</td>
											<td>${store.bio}</td>
											<td>${store.name}</td>
											<td>${store.slug}</td>
											<td>${store.isActive ? 'Yes' : 'No'}</td>
										</tr>
									</c:forEach>



								</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->



					<!-- BEGIN EXAMPLE TABLE PORTLET 4-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Users
									Tables</span>
							</div>
							<div class="actions btn-set">
								<div class="btn-group">
									<a class="btn green-haze btn-circle" href="javascript:;"
										data-toggle="dropdown"> <!--<i class="fa fa-check-circle"></i>-->
										Columns <i class="fa fa-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right"
										id="sample_4_column_toggler">
										<li><label><input type="checkbox" checked
												data-column="0">+</label></li>
										<li><label><input type="checkbox" checked
												data-column="1">ID</label></li>
										<li><label><input type="checkbox" checked
												data-column="2">Email</label></li>
										<li><label><input type="checkbox" checked
												data-column="3">Password</label></li>
										<li><label><input type="checkbox" checked
												data-column="4">FirtName</label></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover"
								id="sample_4">
								<thead>
								<tr>
										<th colspan="5" style="text-align: left;"><a
											href="${pageContext.request.contextPath}/admin/addUser" class="btn btn-primary">Add User</a></th>
									</tr>
									<tr>
										<th>ID</th>
										<th>Email</th>
										<th class="hidden-xs">First Name</th>
										<th class="hidden-xs">Password</th>
										<th class="hidden-xs">is emailActive</th>
									</tr>
								</thead>
								<tbody>
									<!-- Duyệt qua tất cả các user trong userList -->
									<c:forEach var="user" items="${userList}">
										<tr>
											<!-- Hiển thị thông tin của từng user -->
											<td><a href="userDetails?id=${user._id}">${user._id}</a>
											</td>
											<!-- ID của người dùng -->
											<td>${user.email}</td>
											<!-- Email của người dùng -->
											<td class="hidden-xs">${user.firstname}</td>
											<!-- First Name của người dùng -->
											<td class="hidden-xs">${user.password}</td>
											<!-- Password của người dùng -->
											<td class="hidden-xs">${user.isEmailActive ? 'Yes' : 'No'}</td>
											<!-- Trạng thái kích hoạt email -->
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

