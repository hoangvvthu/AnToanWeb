<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<div class="page-content">
	<div class="container">
		<!-- BEGIN PAGE CONTENT INNER -->
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/addStore"
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
							<div class="tab-content">
								<!-- General Tab -->
								<div class="tab-pane active" id="tab_general">
									<div class="form-body">
										<input type="hidden" name="id" value="${store._id}">
										<div class="form-group">
											<label class="col-md-2 control-label">Name:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="name"
													name="name"  placeholder="Enter the store's name" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Bio:</label>
											<div class="col-md-10">
												<textarea class="form-control" id="bio" name="bio" rows="5"placeholder="Enter a short bio for the store"></textarea>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Slug:</label>
											<div class="col-md-10">
												<input type="text" class="form-control" id="slug"
													name="slug" placeholder="Enter the slug for the store" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Point:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="point"
													name="point" placeholder="Enter the store's points" required>
											</div>
										</div>



										<div class="form-group">
											<label class="col-md-2 control-label">CommissionSold:</label>
											<div class="col-md-10">
												<input type="number" class="form-control"
													id="commissionSold" name="commissionSold"
													placeholder="Enter the commission sold value" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">Rating:</label>
											<div class="col-md-10">
												<input type="number" class="form-control" id="rating"
													name="rating" placeholder="Enter the store's rating" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">isOpen:</label>
											<div class="col-md-10">
												<select class="form-control" id="isOpen" name="isOpen">
													<option value="" disabled selected>Is the store active?</option>
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
													<option value="" disabled selected>Select Commission (ID of the commission)</option>
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
													placeholder="Enter Owner's ID (Firstname and Lastname will be auto-filled)"
													required>
											</div>
										</div>



									</div>
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