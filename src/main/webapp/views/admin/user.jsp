<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="page-user"></div>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>
					User Account <small>user account page sample</small>
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
			<div class="row margin-top-10">
				<div class="col-md-12">
					<!-- BEGIN PROFILE SIDEBAR -->
					<div class="profile-sidebar" style="width: 250px;">
						<!-- PORTLET MAIN -->
						<div class="portlet light profile-sidebar-portlet">
							<!-- SIDEBAR USERPIC -->
							<div class="profile-userpic">
								<img
									src="${URL}assets/admin/pages/media/profile/profile_user.jpg"
									class="img-responsive" alt="">
							</div>
							<!-- END SIDEBAR USERPIC -->
							<!-- SIDEBAR USER TITLE -->
							<div class="profile-usertitle">
								<div class="profile-usertitle-name">${user.firstname}
									${user.lastname}</div>
								<div class="profile-usertitle-job">${user.role}</div>
							</div>
							<!-- END SIDEBAR USER TITLE -->
							<!-- SIDEBAR BUTTONS -->
							<div class="profile-userbuttons">
								<button type="button" class="btn btn-circle green-haze btn-sm">Follow</button>
								<button type="button" class="btn btn-circle btn-danger btn-sm">Message</button>
							</div>
							<!-- END SIDEBAR BUTTONS -->
							<!-- SIDEBAR MENU -->
							<div class="profile-usermenu">
								<ul class="nav">
									<li><a href=""> <i class="icon-home"></i>
											Overview
									</a></li>
									<li class="active"><a href="userDetails?id=${user._id}">
											<i class="icon-settings"></i> Account Settings
									</a></li>
									<li><a href="" target="_blank"> <i
											class="icon-check"></i> Tasks
									</a></li>
									<li><a href=""> <i
											class="icon-info"></i> Help
									</a></li>
								</ul>
							</div>
							<!-- END MENU -->
						</div>
						<!-- END PORTLET MAIN -->
						<!-- PORTLET MAIN -->
						<div class="portlet light">
							<!-- STAT -->
							<div class="row list-separated profile-stat">
								<div class="col-md-4 col-sm-4 col-xs-6">
									<div class="uppercase profile-stat-title">37</div>
									<div class="uppercase profile-stat-text">Projects</div>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-6">
									<div class="uppercase profile-stat-title">51</div>
									<div class="uppercase profile-stat-text">Tasks</div>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-6">
									<div class="uppercase profile-stat-title">61</div>
									<div class="uppercase profile-stat-text">Uploads</div>
								</div>
							</div>
							<!-- END STAT -->
							<div>
								<h4 class="profile-desc-title">About ${user.firstname}
									${user.lastname}</h4>
								<span class="profile-desc-text"> Lorem ipsum dolor sit
									amet diam nonummy nibh dolore. </span>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-globe"></i> <a href="http://www.keenthemes.com">www.keenthemes.com</a>
								</div>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-twitter"></i> <a
										href="http://www.twitter.com/keenthemes/">@keenthemes</a>
								</div>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-facebook"></i> <a
										href="http://www.facebook.com/keenthemes/">keenthemes</a>
								</div>
							</div>
						</div>
						<!-- END PORTLET MAIN -->
					</div>
					<!-- END BEGIN PROFILE SIDEBAR -->
					<!-- BEGIN PROFILE CONTENT -->
					<div class="profile-content">
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light">
									<div class="portlet-title tabbable-line">
										<div class="caption caption-md">
											<i class="icon-globe theme-font hide"></i> <span
												class="caption-subject font-blue-madison bold uppercase">Profile
												Account</span>
										</div>
										<ul class="nav nav-tabs">
											<li class="active"><a href="#tab_1_1" data-toggle="tab">Personal
													Info</a></li>
											<li><a href="#tab_1_2" data-toggle="tab">Change
													Avatar</a></li>
											<li><a href="#tab_1_3" data-toggle="tab">Change
													Password</a></li>
										</ul>
									</div>
									<div class="portlet-body">
										<div class="tab-content">
											<!-- PERSONAL INFO TAB -->
											<div class="tab-pane active" id="tab_1_1">
												<form role="form" action="#">
													<div class="form-group">
														<label class="control-label">First Name</label> <input
															type="text" value="${user.firstname}"
															class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Last Name</label> <input
															type="text" value="${user.lastname}" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Phone</label> <input
															type="text" value="${user.phone}" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Email</label> <input
															type="text" value="${user.email}" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Address</label> <input
															type="text" value="${user.address}" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">E-Wallet</label> <input
															type="text" value="${user.e_wallet}" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Created At</label> <input
															type="text" value="${user.createdAt}"
															class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">id_card</label> <input
															type="text" value="${user.id_card}"
															class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">isEmailActive:</label>
														
															<select class="form-control" id="isEmailActive" name="isEmailActive">
																<option value="true" ${user.isEmailActive ? 'selected' : ''}>Yes</option>
																<option value="false" ${!user.isEmailActive ? 'selected' : ''}>No</option>
															</select>
													</div>
													<div class="form-group">
														<label class="control-label">isPhoneActive:</label>
															<select class="form-control" id="is_card" name="is_card">
																<option value="true" ${user.isPhoneActive ? 'selected' : ''}>Yes</option>
																<option value="false" ${!user.isPhoneActive ? 'selected' : ''}>No</option>
															</select>
													</div>
													<div class="margiv-top-10">
														<a href="#" class="btn green-haze"> Save Changes </a> <a
															href="#" class="btn default"> Cancel </a>
													</div>
												</form>
											</div>
											<!-- END PERSONAL INFO TAB -->
											<!-- CHANGE AVATAR TAB -->
											<div class="tab-pane" id="tab_1_2">
												<form action="#" role="form">
													<div class="form-group">
														<div class="fileinput fileinput-new"
															data-provides="fileinput">
															<div class="fileinput-new thumbnail"
																style="width: 200px; height: 150px;">
																<img
																	src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
																	alt="" />
															</div>
															<div class="fileinput-preview fileinput-exists thumbnail"
																style="max-width: 200px; max-height: 150px;"></div>
															<div>
																<span class="btn default btn-file"> <span
																	class="fileinput-new"> Select image </span> <span
																	class="fileinput-exists"> Change </span> <input
																	type="file" name="...">
																</span> <a href="#" class="btn default fileinput-exists"
																	data-dismiss="fileinput"> Remove </a>
															</div>
														</div>
													</div>
													<div class="margin-top-10">
														<a href="#" class="btn green-haze"> Submit </a> <a
															href="#" class="btn default"> Cancel </a>
													</div>
												</form>
											</div>
											<!-- END CHANGE AVATAR TAB -->
											<!-- CHANGE PASSWORD TAB -->
											<div class="tab-pane" id="tab_1_3">
												<form action="#">
													<div class="form-group">
														<label class="control-label">Current Password</label> <input
															type="password" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">New Password</label> <input
															type="password" class="form-control" />
													</div>
													<div class="form-group">
														<label class="control-label">Re-type New Password</label>
														<input type="password" class="form-control" />
													</div>
													<div class="margin-top-10">
														<a href="#" class="btn green-haze"> Change Password </a> <a
															href="#" class="btn default"> Cancel </a>
													</div>
												</form>
											</div>
											<!-- END CHANGE PASSWORD TAB -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PROFILE CONTENT -->
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->