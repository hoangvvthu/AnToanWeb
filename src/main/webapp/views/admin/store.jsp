<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<div class="page-content">
	<div class="container">
		<!-- BEGIN PAGE CONTENT INNER -->
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/updateStore"
				method="post" class="form-horizontal">
				<div class="col-md-12">
					<!-- BEGIN PORTLET -->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-basket font-green-sharp"></i> <span
									class="caption-subject font-green-sharp bold uppercase">Edit
									Store</span>
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
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_general" data-toggle="tab">General</a></li>
								<li><a href="#tab_images" data-toggle="tab">Images</a></li>
								<li><a href="#tab_reviews" data-toggle="tab">Reviews</a></li>
							</ul>
							<div class="tab-content">
								<!-- General Tab -->
								<div class="tab-pane active" id="tab_general">
									<div class="form-body">
										<input type="hidden" name="id" value="${store._id}">
										<div class="form-group">
											<label class="col-md-2 control-label">Name:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="name"
													name="name" value="${store.name}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Bio:</label>
											<div class="col-md-10">
												<textarea class="form-control" id="bio" name="bio" rows="5">${store.bio}</textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Slug:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="slug"
													name="slug" value="${store.slug}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Point:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="point"
													name="point" value="${store.point}" required>
											</div>
										</div>



										<div class="form-group">
											<label class="col-md-2 control-label">CommissionSold:</label>
											<div class="col-md-10">
												<input type="number" class="form-control"
													id="commissionSold" name="commissionSold"
													value="${store.commissionSold}" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Rating:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="rating"
													name="rating" value="${store.rating}" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">isOpen:</label>
											<div class="col-md-10">
												<select class="form-control" id="isOpen" name="isOpen">
													<option value="true" ${store.isOpen ? 'selected' : ''}>Yes</option>
													<option value="false" ${!store.isOpen ? 'selected' : ''}>No</option>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-2 control-label">isActive:</label>
											<div class="col-md-10">
												<select class="form-control" id="isActive" name="isActive">
													<option value="true" ${store.isActive ? 'selected' : ''}>Yes</option>
													<option value="false" ${!store.isActive ? 'selected' : ''}>No</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Commission:</label>
											<div class="col-md-10">
												<select class="form-control" id="commission"
													name="commission">
													<c:forEach var="com" items="${comList}">
														<option value="${com._id}">${com.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Owner:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="owner_id"
													name="owner_id"
													value="${store.owner.firstname} ${store.owner.lastname}"
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