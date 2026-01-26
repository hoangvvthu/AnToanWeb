<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<div class="page-content">
	<div class="container">
		<!-- BEGIN PAGE CONTENT INNER -->
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/addProduct"
				method="post" class="form-horizontal">
				<div class="col-md-12">
					<!-- BEGIN PORTLET -->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-basket font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Add
									Product</span>
							</div>
							<div class="actions btn-set">
								<button type="button" class="btn btn-default"
									onclick="window.location.href='${pageContext.request.contextPath}/admin/manager'">
									<i class="fa fa-angle-left"></i> Back
								</button>
								<button type="reset" class="btn btn-default">
									<i class="fa fa-reply"></i> Reset
								</button>
								<button type="submit" class="btn green-haze">
									<i class="fa fa-check"></i> Save
								</button>
							</div>
						</div>
						<div class="portlet-body">
							<!-- General Tab -->
							<div class="tab-pane active" id="tab_general">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">Name:</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="name" name="name"
												placeholder="Enter the product name" required>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Description:</label>
										<div class="col-md-10">
											<textarea class="form-control" id="description"
												name="description" rows="5"
												placeholder="Enter a detailed description of the product"></textarea>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Slug:</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="slug" name="slug"
												placeholder="Enter the URL-friendly slug for the product"
												required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Price:</label>
										<div class="col-md-10">
											<input type="number" class="form-control" id="price"
												name="price" placeholder="Enter the price of the product"
												required>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Promotional
											Price:</label>
										<div class="col-md-10">
											<input type="number" class="form-control"
												id="promotionalPrice" name="promotionalPrice"
												placeholder="Enter the promotional price" required>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Quantity:</label>
										<div class="col-md-10">
											<input type="number" class="form-control" id="quantity"
												name="quantity" placeholder="Enter the available quantity"
												required>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Sold:</label>
										<div class="col-md-10">
											<input type="number" class="form-control" id="sold"
												name="sold" placeholder="Number of units sold" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Category:</label>
										<div class="col-md-10">
											<select class="form-control" id="category_id"
												name="category_id">
												<c:forEach var="cate" items="${cateList}">
													<option value="${cate._id}">${cate.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Store ID:</label>
										<div class="col-md-10">
											<input type="number" class="form-control" id="store_id"
												name="store_id" placeholder="Enter the available store Id"
												required>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">isActive:</label>
										<div class="col-md-10">
											<select class="form-control" id="isActive" name="isActive">
												<option value="true" ${product.isActive ? 'selected' : ''}>Yes</option>
												<option value="false" ${!product.isActive ? 'selected' : ''}>No</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">isSelling:</label>
										<div class="col-md-10">
											<select class="form-control" id="isSelling" name="isSelling">
												<option value="true" ${product.isSelling ? 'selected' : ''}>Yes</option>
												<option value="false"
													${!product.isSelling ? 'selected' : ''}>No</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Rating:</label>
										<div class="col-md-10">
											<input type="number" step="0.5" class="form-control"
												id="rating" name="rating"
												placeholder="Enter the product rating (e.g., 4.5)" required>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END PORTLET -->
			</form>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->