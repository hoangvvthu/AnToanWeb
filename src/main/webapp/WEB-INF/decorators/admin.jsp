<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Admin Dashboard Template | Advanced Datatables</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css">
<link
	href="${URL}assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${URL}assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css">
<link href="${URL}assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${URL}assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->



<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css">
<link
	href="${URL}assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${URL}assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css">
<link href="${URL}assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${URL}assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link rel="stylesheet" type="text/css"
	href="${URL}assets/global/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="${URL}assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css" />
<link rel="stylesheet" type="text/css"
	href="${URL}assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css" />
<link rel="stylesheet" type="text/css"
	href="${URL}assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<link href="${URL}assets/global/plugins/jqvmap/jqvmap/jqvmap.css"
	rel="stylesheet" type="text/css">
<link href="${URL}assets/global/plugins/morris/morris.css"
	rel="stylesheet" type="text/css">
<!-- END PAGE LEVEL PLUGIN STYLES -->

<!-- BEGIN PAGE STYLES -->
<link href="${URL}assets/admin/pages/css/tasks.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->
<link
	href="${URL}assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<link href="${URL}assets/admin/pages/css/profile.css" rel="stylesheet"
	type="text/css" />
<link href="${URL}assets/admin/pages/css/tasks.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="${URL}assets/global/css/components-rounded.css"
	id="style_components" rel="stylesheet" type="text/css">
<link href="${URL}assets/global/css/plugins.css" rel="stylesheet"
	type="text/css">
<link href="${URL}assets/admin/layout3/css/layout.css" rel="stylesheet"
	type="text/css">
<link href="${URL}assets/admin/layout3/css/themes/default.css"
	rel="stylesheet" type="text/css" id="style_color">
<link href="${URL}assets/admin/layout3/css/custom.css" rel="stylesheet"
	type="text/css">
<!-- END THEME STYLES -->

<link rel="shortcut icon" href="favicon.ico" />
</head>

<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-menu-fixed" class to set the mega menu fixed  -->
<!-- DOC: Apply "page-header-top-fixed" class to set the top menu fixed  -->
<body>

	<!-- Header -->

	<%@include file="/common/admin/header.jsp"%>

	<section>
		<div class="container">
			<sitemesh:write property="body" />
		</div>
	</section>

	<%@include file="/common/admin/footer.jsp"%>



	<!-- BEGIN JAVASCRIPTS (Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="${URL}assets/global/plugins/respond.min.js"></script>
<script src="${URL}assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="${URL}assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${URL}assets/global/plugins/select2/select2.min.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="${URL}assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
	<script
		src="${URL}assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"></script>
	<script
		src="${URL}assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"></script>
	<script
		src="${URL}assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script src="${URL}assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/morris/morris.min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/morris/raphael-min.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/jquery.sparkline.min.js"
		type="text/javascript"></script>

	<script src="${URL}assets/global/plugins/flot/jquery.flot.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/flot/jquery.flot.resize.js"
		type="text/javascript"></script>
	<script
		src="${URL}assets/global/plugins/flot/jquery.flot.categories.js"
		type="text/javascript"></script>


	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="${URL}assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
		type="text/javascript"></script>
	<script src="${URL}assets/global/plugins/jquery.sparkline.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->


	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${URL}assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/layout3/scripts/layout.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/layout3/scripts/demo.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/pages/scripts/table-advanced.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/pages/scripts/index3.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/pages/scripts/tasks.js"
		type="text/javascript"></script>
	<script src="${URL}assets/admin/pages/scripts/ecommerce-index.js"></script>
	<script src="${URL}assets/admin/pages/scripts/profile.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {
			if ($('#page-home').length > 0) {
				Metronic.init(); // init metronic core components
				Layout.init(); // init current layout
				Demo.init(); // init demo features
				EcommerceIndex.init();
			}

			if ($('#page-manager').length > 0) {
				Metronic.init(); // Init Metronic core components
				Layout.init(); // Init layout
				Demo.init(); // Init demo features
				TableAdvanced.init(); // Init advanced table
			}
			if ($('#page-user').length > 0) {
				Metronic.init(); // Init Metronic core components
				Layout.init(); // Init layout
				Demo.init(); // Init demo features
				Profile.init(); // Init advanced table
			}
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>
