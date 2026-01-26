<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<title><sitemesh:write property="title" /></title>
<%@ include file="/common/css-link.jsp"%>

<%@ include file="/common/web/header.jsp"%>

</head>

<body class="my-body">
	<sitemesh:write property="body" />
</body>

<footer>
	<%@ include file="/common/web/footer.jsp"%>
</footer>

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
	
	function loadProducts(categoryId) {
		$.ajax({
			url: '${pageContext.request.contextPath}/home/productsByCategory', // URL đến API backend
			type: 'GET',
			data: { categoryId: categoryId },
			success: function(response) {
				// Cập nhật danh sách sản phẩm
				$('#products-container').html(response);
			},
			error: function(err) {
				console.error('Error loading products:', err);
			}
		});
	}
</script>