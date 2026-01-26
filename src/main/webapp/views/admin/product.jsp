<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-content">
	<div class="container">
		<!-- BEGIN PAGE CONTENT INNER -->
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/updateProduct"
				method="post" class="form-horizontal">
				<div class="col-md-12">
					<!-- BEGIN PORTLET -->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-basket font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Edit
									Product</span>
							</div>
							<div class="actions btn-set">
								<button type="button" class="btn btn-default" onclick="window.location.href='${pageContext.request.contextPath}/admin/manager'">
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
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_general" data-toggle="tab">General</a></li>
								<li><a href="#tab_images" data-toggle="tab">Images</a></li>
								<li><a href="#tab_reviews" data-toggle="tab">Reviews</a></li>
							</ul>
							<div class="tab-content">
								<!-- General Tab -->
								<div class="tab-pane active" id="tab_general">
									<div class="form-body">
										<input type="hidden" name="id" value="${product._id}">
										<div class="form-group">
											<label class="col-md-2 control-label">Name:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="name"
													name="name" value="${product.name}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Description:</label>
											<div class="col-md-10">
												<textarea class="form-control" id="description"
													name="description" rows="5">${product.description}</textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Slug:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="slug"
													name="slug" value="${product.slug}" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Price:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="price"
													name="price" value="${product.price}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Promotional
												Price:</label>
											<div class="col-md-10">
												<input type="number" class="form-control"
													id="promotionalPrice" name="promotionalPrice"
													value="${product.promotionalPrice}" required>
											</div>
										</div>


										<div class="form-group">
											<label class="col-md-2 control-label">Quantity:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="quantity"
													name="quantity" value="${product.quantity}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Sold:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="sold"
													name="sold" value="${product.sold}" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Category:</label>
											<div class="col-md-10">
												<select class="form-control" id="category_id"
													name="category_id">
													<option value="${product.category._id}">${product.category.name}</option>
													<option value="inactive">B</option>
													<option value="inactive">C{product.sold}</option>
													<option value="inactive">D{product.sold}</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">isActive:</label>
											<div class="col-md-10">
												<select class="form-control" id="isActive" name="isActive">
													<option value="true" ${product.isActive ? 'selected' : ''}>Yes</option>
													<option value="false"
														${!product.isActive ? 'selected' : ''}>No</option>
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
													id="rating" name="rating" value="${product.rating}"
													required>
											</div>
										</div>
									</div>
								</div>

								<!-- Images Tab -->
								<div class="tab-pane" id="tab_images">
									<div class="form-group">
										<label class="col-md-2 control-label">Upload Images:</label>
										<div class="col-md-10">
											<input type="file" class="form-control"
												name="product[images][]" multiple>
											<p class="help-block">You can upload multiple images.</p>
										</div>
									</div>
								</div>

								<!-- Reviews Tab -->
								<div class="tab-pane" id="tab_reviews">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>#</th>
												<th>Customer</th>
												<th>Content</th>
												<th>Status</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>John Doe</td>
												<td>Great product!</td>
												<td>Approved</td>
												<td>
													<button class="btn btn-sm btn-warning">Edit</button>
													<button class="btn btn-sm btn-danger">Delete</button>
												</td>
											</tr>
											<!-- More rows can be dynamically added here -->
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET -->
				</div>
			</form>
		</div>
	</div>
	<!-- END PAGE CONTENT INNER -->
</div>