<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div id="page-home"></div>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>
					eCommerce <small>dashboard & statistics</small>
				</h1>
			</div>
			<!-- END PAGE TITLE -->

		</div>
	</div>
	<!-- END PAGE HEAD -->
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content">
		<div class="container">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->



			<!-- 3 ô thông báo ở đầu -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 margin-bottom-10">
					<a class="dashboard-stat dashboard-stat-light blue-madison"
						href="javascript:;">
						<div class="visual">
							<i class="fa fa-briefcase fa-icon-medium"></i>
						</div>
						<div class="details">
							<div class="number">$${totalSale}</div>
							<div class="desc">Lifetime Sales</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<a class="dashboard-stat dashboard-stat-light red-intense"
						href="javascript:;">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div>
						<div class="details">
							<div class="number">$${totalOrder}</div>
							<div class="desc">Total Orders</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<a class="dashboard-stat dashboard-stat-light green-haze"
						href="javascript:;">
						<div class="visual">
							<i class="fa fa-group fa-icon-medium"></i>
						</div>
						<div class="details">
							<div class="number">
								$
								<fmt:formatNumber value="${totalSale/totalOrder}" type="number"
									maxFractionDigits="2" />
							</div>
							<div class="desc">Average Orders</div>
						</div>
					</a>
				</div>
			</div>
			<!-- 3 ô thông báo ở đầu -->






			<div class="row">
				<div class="col-md-6">
					<!-- Begin: life time stats -->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-bar-chart font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Overview</span>
								<span class="caption-helper">weekly stats...</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a> <a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>


						<div class="portlet-body">
							<div class="tabbable-line">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#overview_1" data-toggle="tab">
											Top Selling </a></li>
									<li><a href="#overview_2" data-toggle="tab"> Most
											Viewed </a></li>
									<li><a href="#overview_3" data-toggle="tab"> Customers
									</a></li>
									<li><a href="#overview_4" data-toggle="tab"> Orders </a></li>
								</ul>

								<!-- Nội dung bên trái -->
								<div class="tab-content">
									<div class="tab-pane active" id="overview_1">
										<div class="table-responsive">
											<table class="table table-hover table-light">
												<thead>
													<tr class="uppercase">
														<th>Product Name</th>
														<th>Price</th>
														<th>Sold</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<!-- Lặp qua danh sách proList -->
													<c:forEach var="product" items="${proList}">
														<tr>
															<td><a href="javascript:;">${product.name}</a> <!-- Hiển thị tên sản phẩm -->
															</td>
															<td>$${product.price} <!-- Hiển thị giá sản phẩm, giả sử có thuộc tính price -->
															</td>
															<td>${product.sold}<!-- Hiển thị số lượng đã bán, giả sử có thuộc tính sold -->
															</td>
															<td><a href="javascript:;"
																class="btn default btn-xs green-stripe">View</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>







									<div class="tab-pane" id="overview_2">
										<div class="table-responsive">
											<table class="table table-hover table-light">
												<thead>
													<tr class="uppercase">
														<th>Product Name</th>
														<th>Price</th>
														<th>Views</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><a href="javascript:;"> Metronic - Responsive
																Admin + Frontend Theme </a></td>
														<td>$20.00</td>
														<td>11190</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Regatta Luca 3 in 1
																Jacket </a></td>
														<td>$25.50</td>
														<td>1245</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Apple iPhone 4s -
																16GB - Black </a></td>
														<td>$625.50</td>
														<td>809</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Samsung Galaxy S III
																SGH-I747 - 16GB </a></td>
														<td>$915.50</td>
														<td>6709</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Motorola Droid 4
																XT894 - 16GB - Black </a></td>
														<td>$878.50</td>
														<td>784</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Samsung Galaxy Note 3
														</a></td>
														<td>$925.50</td>
														<td>21245</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
													<tr>
														<td><a href="javascript:;"> Inoval Digital Pen </a></td>
														<td>$125.50</td>
														<td>1245</td>
														<td><a href="javascript:;"
															class="btn default btn-xs green-stripe"> View </a></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>



									<div class="tab-pane" id="overview_3">
										<div class="table-responsive">
											<table class="table table-hover table-light">
												<thead>
													<tr>
														<th>Customer Name</th>
														<th>Total Orders</th>
														<th>Total Amount</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<!-- Lặp qua danh sách topUsers và hiển thị thông tin -->
													<c:forEach var="user" items="${topUsers}">
														<tr>
															<td><a href="javascript:;">${user[0]}</a> <!-- Hiển thị tên sản phẩm -->
															</td>
															<td>${user[1]}<!-- Hiển thị giá sản phẩm, giả sử có thuộc tính price -->
															</td>
															<td>${user[2]}<!-- Hiển thị số lượng đã bán, giả sử có thuộc tính sold -->
															</td>
															<td><a href="javascript:;"
																class="btn default btn-xs green-stripe">View</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="tab-pane" id="overview_4">
										<div class="table-responsive">
											<table class="table table-hover table-light">
												<thead>
													<tr class="uppercase">
														<th>Customer Name</th>
														<th>Date</th>
														<th>Amount</th>
														<th>Status</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<!-- Lặp qua danh sách orders và hiển thị thông tin -->
													<c:forEach var="order" items="${last7order}">
														<tr>
															<!-- Hiển thị tên khách hàng -->
															<td><a href="javascript:;">${order.user.firstname}
																	${order.user.lastname}</a></td>
															<!-- Hiển thị ngày -->
															<td><fmt:formatDate value="${order.createdAt}"
																	pattern="dd MMM, yyyy" /></td>
															<!-- Hiển thị số tiền -->
															<td>${order.amountFromUser}</td>
															<!-- Hiển thị trạng thái đơn hàng -->
															<td><c:choose>
																	<c:when test="${order.status == 'NOT_PROCESSED'}">
																		<span class="label label-sm label-warning">NOT_PROCESSED</span>
																	</c:when>
																	<c:when test="${order.status == 'PROCESSED'}">
																		<span class="label label-sm label-success">PROCESSED</span>
																	</c:when>
																	<c:when test="${order.status == 'SHIPPED'}">
																		<span class="label label-sm label-danger">SHIPPED</span>
																	</c:when>
																	<c:when test="${order.status == 'DELIVERED'}">
																		<span class="label label-sm label-info">DELIVERED</span>
																	</c:when>
																	<c:when test="${order.status == 'CANCELLED'}">
																		<span class="label label-sm label-info">CANCELLED</span>
																	</c:when>
																</c:choose></td>
															<!-- Liên kết xem chi tiết đơn hàng -->
															<td><a href="javascript:;"
																class="btn default btn-xs green-stripe">View</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>

								<!-- Nội dung bên trái -->
							</div>
						</div>
					</div>
					<!-- End: life time stats -->
				</div>
				<div class="col-md-6">
					<!-- Begin: life time stats -->
					<div class="portlet light">
						<div class="portlet-title tabbable-line">
							<div class="caption">
								<i class="icon-share font-red-sunglo"></i> <span
									class="caption-subject font-red-sunglo bold uppercase">Revenue</span>
								<span class="caption-helper">weekly stats...</span>
							</div>
							<ul class="nav nav-tabs">
								<li><a href="#portlet_tab2" data-toggle="tab"
									id="statistics_amounts_tab"> Amounts </a></li>
								<li class="active"><a href="#portlet_tab1"
									data-toggle="tab"> Orders </a></li>
							</ul>
						</div>
						<div class="portlet-body">
							<div class="tab-content">
								<div class="tab-pane active" id="portlet_tab1">
									<div id="statistics_1" class="chart"></div>
								</div>
								<div class="tab-pane" id="portlet_tab2">
									<div id="statistics_2" class="chart"></div>
								</div>
							</div>
							<div class="margin-top-20 no-margin no-border">
								<div class="row">
									<div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-success uppercase"> Revenue: </span>
										<h3>$99999.999999</h3>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-info uppercase"> Tax: </span>
										<h3>$134,90.10</h3>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-danger uppercase"> Shipment: </span>
										<h3>$1,134,90.10</h3>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-6 text-stat">
										<span class="label label-warning uppercase"> Orders: </span>
										<h3>235090</h3>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End: life time stats -->
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->