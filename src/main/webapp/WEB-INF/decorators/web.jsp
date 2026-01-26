<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | E-Shopper</title>
<link
	href="${pageContext.request.contextPath}/Eshopper/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Eshopper/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Eshopper/css/prettyPhoto.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Eshopper/css/price-range.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/Eshopper/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/Eshopper/css/main.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Eshopper/css/responsive.css"
	rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/Eshopper/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${pageContext.request.contextPath}/Eshopper/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${pageContext.request.contextPath}/Eshopper/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${pageContext.request.contextPath}/Eshopper/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="${URL}Eshopper/images/ico/apple-touch-icon-57-precomposed.png">
	
	<%@ include file="/common/css-link.jsp"%>
	
</head>
<!--/head-->

<body>

	<%@include file="/common/web/header.jsp"%>

	<section>
		<div class="container">
			<sitemesh:write property="body" />
		</div>
	</section>

	<%@include file="/common/web/footer.jsp"%>



<<<<<<< HEAD
	<script src="${URL}Eshopper/js/jquery.js"></script>
	<script src="${URL}Eshopper/js/bootstrap.min.js"></script>
	<script src="${URL}Eshopper/js/jquery.scrollUp.min.js"></script>
	<script src="${URL}Eshopper/js/price-range.js"></script>
	<script src="${URL}Eshopper/js/jquery.prettyPhoto.js"></script>
	<script src="${URL}Eshopper/js/main.js"></script>
	<script>
    function previewImage(event) {
        const file = event.target.files[0];
        const previewImage = document.getElementById('preview-image');
        
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                previewImage.src = e.target.result;
                previewImage.style.display = 'block';
            };
            reader.readAsDataURL(file);
        } else {
            previewImage.src = '';
            previewImage.style.display = 'none';
        }
    }
    
    $(function() {
		$("#slider-range").slider({
			range : true,
			min : 0,
			max : 200,
			values : [ 0, 0 ],
			slide : function(event, ui) {
				$("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
				$("#amount_start").val(ui.values[0]);
				$("#amount_end").val(ui.values[1]);
			}
		});
		/* $("#amount").val(
				"$" + $("#slider-range").slider("values", 0) + " - $"
						+ $("#slider-range").slider("values", 1)); */
	});

	function readURL(input) {
		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function(e) {
				$('.image-upload-wrap').hide();

				$('.file-upload-image').attr('src', e.target.result);
				$('.file-upload-content').show();

				$('.image-title').html(input.files[0].name);
			};

			reader.readAsDataURL(input.files[0]);

		} else {
			removeUpload();
		}
	}

	function removeUpload() {
		$('.file-upload-input').replaceWith($('.file-upload-input').clone());
		$('.file-upload-content').hide();
		$('.image-upload-wrap').show();
	}
	$('.image-upload-wrap').bind('dragover', function() {
		$('.image-upload-wrap').addClass('image-dropping');
	});
	$('.image-upload-wrap').bind('dragleave', function() {
		$('.image-upload-wrap').removeClass('image-dropping');
	});
	

</script>


</body>
</html>