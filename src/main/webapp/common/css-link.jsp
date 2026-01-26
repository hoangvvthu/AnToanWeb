
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<base href="${pageContext.servletContext.contextPath}/" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/prettyPhoto.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/price-range.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/responsive.css"
	rel="stylesheet">
	


<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
<link rel="icon" href="images/home/logo.png">

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/price-range.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<style>
    .store-title {
        font-size: 2em;
        text-align: center;
        color: #333;
        margin: 20px 0;
    }

    .store-avatar {
        width: 120px;
        height: 120px;
        border-radius: 50%;
        border: 3px solid #4CAF50;
        display: block;
        margin: 0 auto;
    }

    .store-bio {
        text-align: center;
        color: #666;
        margin: 10px 0;
    }

    .staff-title, .slider-title, .review-title, .product-title {
        text-align: center;
        color: #555;
        margin: 20px 0 10px;
    }

    .staff-list, .product-list {
        list-style-type: none;
        padding: 0;
        text-align: center;
    }

    .staff-item, .product-item {
        background-color: #e7f3fe;
        margin: 5px 0;
        padding: 10px;
        border-radius: 5px;
    }

    .image-slider {
        display: flex;
        overflow-x: auto;
        padding: 10px;
        justify-content: center;
    }

    .slider-image {
        width: 300px;
        height: auto;
        border-radius: 8px;
        margin: 0 10px;
        transition: transform 0.2s;
    }

    .slider-image:hover {
        transform: scale(1.05);
    }

    .review-container {
        text-align: center;
    }

    .review-item {
        border: 1px solid #ddd;
        border-radius: 8px;
        margin: 10px 0;
        padding: 15px;
        background-color: #fff;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }

    .review-content {
        margin: 5px 0;
        color: #444;
    }

    .review-user {
        font-size: 0.9em;
        color: #777;
    }

    .product-link {
        text-decoration: none;
        color: #007BFF;
    }

    .product-link:hover {
        text-decoration: underline;
    }
    
    .search-title {
        font-size: 2.5em;
        text-align: center;
        color: #333;
        margin: 20px 0;
    }

    .search-results {
        text-align: center;
        color: #666;
        margin-bottom: 30px;
        font-size: 1.2em;
    }

    .store-list-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
        margin: 0 auto;
        max-width: 1200px;
    }

    .store-card-item {
        border: 1px solid #ddd;
        border-radius: 8px;
        width: 280px;
        text-align: center;
        background-color: #fff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        transition: transform 0.2s;
    }

    .store-card-item:hover {
        transform: scale(1.05);
    }

    .store-avatar-image {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin-top: 15px;
        border: 3px solid #4CAF50;
    }

    .store-name {
        font-size: 1.5em;
        color: #333;
        margin: 10px 0;
    }

    .store-bio {
        color: #666;
        margin: 10px 15px;
        font-size: 0.9em;
    }

    .store-detail-link {
        display: inline-block;
        margin: 15px 0;
        padding: 10px 15px;
        background-color: #FE980F;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }


	// Search user
    .store-detail-link:hover {
        background-color: #45a049;
    }

    .pagination-container {
        text-align: center;
        margin: 20px 0;
    }

    .pagination-link, .pagination-number {
        margin: 0 5px;
        padding: 10px;
        background-color: #f1f1f1;
        color: #333;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .pagination-link:hover, .pagination-number:hover {
        background-color: #ddd;
    }

    .pagination-number.active {
        background-color: #4CAF50;
        color: white;
    }
    
    .header-title {
        color: #333;
        margin-bottom: 20px;
    }

    .filter-form {
        margin-bottom: 20px;
        padding: 15px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .filter-label {
        font-weight: bold;
        margin-right: 10px;
    }

    .filter-checkbox {
        margin-right: 5px;
    }

    .filter-button {
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .filter-button:hover {
        background-color: #0056b3;
    }

    .user-list {
        list-style-type: none;
        padding: 0;
    }

    .user-list li {
        margin: 10px 0;
    }

    .pagination {
        margin-top: 20px;
    }

    .pagination ul {
        list-style-type: none;
        padding: 0;
    }

    .pagination li {
        display: inline;
        margin-right: 5px;
    }

    .pagination a {
        text-decoration: none;
        color: #007bff;
        padding: 5px 10px;
        border: 1px solid #007bff;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .pagination a:hover {
        background-color: #007bff;
        color: white;
    }

    .no-users-message {
        color: #ff0000;
        font-weight: bold;
    }
    // user detail
    
    .user-details-title {
        color: #333;
        margin-bottom: 20px;
    }

    .user-details {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }

    .user-detail-item {
        margin-bottom: 10px;
    }

    .user-detail-label {
        font-weight: bold;
        color: #555;
    }

    .user-avatar {
        width: 150px;
        border-radius: 50%;
        margin-top: 15px;
    }

    .back-link {
        display: inline-block;
        margin-top: 15px;
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .back-link:hover {
        background-color: #0056b3;
    }
    
    .sold-info {
    color: black; 
    font-weight: bold; 
}

$yellow:#f5ba1a;
$black:#000000;
$grey:#cccccc;


body {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 14px;
	background: #f2f2f2;
}
.clearfix {
	&:after {
		content: "";
		display: block;
		clear: both;
		visibility: hidden;
		height: 0;
	}
}
.form_wrapper {
	background: #fff;
	width: 400px;
	max-width: 100%;
	box-sizing: border-box;
	padding: 25px;
	margin: 8% auto 0;
	position: relative;
	z-index: 1;
	border-top: 5px solid $yellow;
	-webkit-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
	box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
    -webkit-transform-origin: 50% 0%;
    transform-origin: 50% 0%;
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
    -webkit-transition: none;
    transition: none;
    -webkit-animation: expand 0.8s 0.6s ease-out forwards;
    animation: expand 0.8s 0.6s ease-out forwards;
    opacity: 0;
	h2 {
		font-size: 1.5em;
		line-height: 1.5em;
		margin: 0;
	}
	.title_container {
		text-align: center;
		padding-bottom: 15px;
	}
	h3 {
		font-size: 1.1em;
		font-weight: normal;
		line-height: 1.5em;
		margin: 0;
	}
    label {
        font-size: 12px;
    }
	.row {
		margin: 10px -15px;
		>div {
			padding: 0 15px;
			box-sizing: border-box;
		}
	}
	.col_half {
		width: 50%;
		float: left;
	}
	.input_field {
		position: relative;
		margin-bottom: 20px;
        -webkit-animation: bounce 0.6s ease-out;
  	     animation: bounce 0.6s ease-out;
		>span {
			position: absolute;
			left: 0;
			top: 0;
			color: #333;
			height: 100%;
			border-right: 1px solid $grey;
			text-align: center;
			width: 30px;
			>i {
				padding-top: 10px;
			}
		}
	}
	.textarea_field {
		>span {
			>i {
				padding-top: 10px;
			}
		}
	}
	input {
    &[type="text"], &[type="email"], &[type="password"] {
      width: 100%;
      padding: 8px 10px 9px 35px;
      height: 35px;
      border: 1px solid $grey;
      box-sizing: border-box;
      outline: none;
      -webkit-transition: all 0.30s ease-in-out;
      -moz-transition: all 0.30s ease-in-out;
      -ms-transition: all 0.30s ease-in-out;
      transition: all 0.30s ease-in-out;
    }
    &[type="text"]:hover, &[type="email"]:hover, &[type="password"]:hover {
      background: #fafafa;
    }
    &[type="text"]:focus, &[type="email"]:focus, &[type="password"]:focus {
      -webkit-box-shadow: 0 0 2px 1px rgba(255, 169, 0, 0.5);
      -moz-box-shadow: 0 0 2px 1px rgba(255, 169, 0, 0.5);
      box-shadow: 0 0 2px 1px rgba(255, 169, 0, 0.5);
      border: 1px solid $yellow;
      background: #fafafa;
    }
    &[type="submit"] {
		background: #FE980F;
		height: 35px;
		line-height: 35px;
		width: 100%;
		border: none;
		outline: none;
		cursor: pointer;
		color: #fff;
		font-size: 1.1em;
		margin-bottom: 10px;
		-webkit-transition: all 0.30s ease-in-out;
		-moz-transition: all 0.30s ease-in-out;
		-ms-transition: all 0.30s ease-in-out;
		transition: all 0.30s ease-in-out;
		&:hover {
			background: darken($yellow,7%);
		}
		&:focus {
			background: darken($yellow,7%);
		}
	}    
    &[type="checkbox"], &[type="radio"] {
      border: 0;
      clip: rect(0 0 0 0);
      height: 1px;
      margin: -1px;
      overflow: hidden;
      padding: 0;
      position: absolute;
      width: 1px;
    }
  }
}
.form_container {
	.row {
		.col_half.last {
			border-left: 1px solid $grey;
		}
	}
}
.checkbox_option{
    label{
        margin-right: 1em;
        position: relative;
        &:before {
          content: "";
          display: inline-block;
          width: 0.5em;
          height: 0.5em;
          margin-right: 0.5em;
          vertical-align: -2px;
          border: 2px solid $grey;
          padding: 0.12em;
          background-color: transparent;
          background-clip: content-box;
          transition: all 0.2s ease;
        }
        &:after {
          border-right: 2px solid $black;
          border-top: 2px solid $black;
          content: "";
          height: 20px;
          left: 2px;
          position: absolute;
          top: 7px;
          transform: scaleX(-1) rotate(135deg);
          transform-origin: left top;
          width: 7px;
          display: none;
        }
    }
    input {
    &:hover + label:before {
      border-color: $black;
    }
    &:checked + label {
      &:before {
        border-color: $black;
      }
      &:after {
        -moz-animation: check 0.8s ease 0s running;
        -webkit-animation: check 0.8s ease 0s running;
        animation: check 0.8s ease 0s running;
        display: block;
        width: 7px;
        height: 20px;
        border-color: $black;
      }
    }
  }
}
.radio_option {
  label {
      margin-right: 1em;
    &:before {
      content: "";
      display: inline-block;
      width: 0.5em;
      height: 0.5em;
      margin-right: 0.5em;
      border-radius: 100%;
      vertical-align: -3px;
      border: 2px solid $grey;
      padding: 0.15em;
      background-color: transparent;
      background-clip: content-box;
      transition: all 0.2s ease;
    }
  }
  input {
    &:hover + label:before {
      border-color: $black;
    }
    &:checked + label:before {
      background-color: $black;
      border-color: $black;
    }
  }
}
.select_option {
  position: relative;
  width: 100%;
  select {
    display: inline-block;
    width: 100%;
    height: 35px;
    padding: 0px 15px;
    cursor: pointer;
    color: #7b7b7b;
    border: 1px solid $grey;
    border-radius: 0;
    background: #fff;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    transition: all 0.2s ease;
    &::-ms-expand {
      display: none;
    }
    &:hover, &:focus {
      color: $black;
      background: #fafafa;
      border-color: $black;
      outline: none;
    }
  }
}
.select_arrow {
  position: absolute;
  top: calc(50% - 4px);
  right: 15px;
  width: 0;
  height: 0;
  pointer-events: none;
  border-width: 8px 5px 0 5px;
  border-style: solid;
  border-color: #7b7b7b transparent transparent transparent;
}

.select_option select {
  &:hover + .select_arrow, &:focus + .select_arrow {
    border-top-color: $black;
  }
}
.credit {
	position: relative;
	z-index: 1;
	text-align: center;
	padding: 15px;
	color: $yellow;
	a {
		color: darken($yellow,7%);
	}
}
@-webkit-keyframes check {
  0% { height: 0; width: 0; }
  25% { height: 0; width: 7px; }
  50% { height: 20px; width: 7px; }
}

@keyframes check {
  0% { height: 0; width: 0; }
  25% { height: 0; width: 7px; }
  50% { height: 20px; width: 7px; }
}

@-webkit-keyframes expand { 
	0% { -webkit-transform: scale3d(1,0,1); opacity:0; }
	25% { -webkit-transform: scale3d(1,1.2,1); }
	50% { -webkit-transform: scale3d(1,0.85,1); }
	75% { -webkit-transform: scale3d(1,1.05,1); }
	100% { -webkit-transform: scale3d(1,1,1);  opacity:1; }
}

@keyframes expand { 
	0% { -webkit-transform: scale3d(1,0,1); transform: scale3d(1,0,1);  opacity:0; }
	25% { -webkit-transform: scale3d(1,1.2,1); transform: scale3d(1,1.2,1); }
	50% { -webkit-transform: scale3d(1,0.85,1); transform: scale3d(1,0.85,1); }
	75% { -webkit-transform: scale3d(1,1.05,1); transform: scale3d(1,1.05,1); }
	100% { -webkit-transform: scale3d(1,1,1); transform: scale3d(1,1,1);  opacity:1; }
}


@-webkit-keyframes bounce { 
	0% { -webkit-transform: translate3d(0,-25px,0); opacity:0; }
	25% { -webkit-transform: translate3d(0,10px,0); }
	50% { -webkit-transform: translate3d(0,-6px,0); }
	75% { -webkit-transform: translate3d(0,2px,0); }
	100% { -webkit-transform: translate3d(0,0,0); opacity: 1; }
}

@keyframes bounce { 
	0% { -webkit-transform: translate3d(0,-25px,0); transform: translate3d(0,-25px,0); opacity:0; }
	25% { -webkit-transform: translate3d(0,10px,0); transform: translate3d(0,10px,0); }
	50% { -webkit-transform: translate3d(0,-6px,0); transform: translate3d(0,-6px,0); }
	75% { -webkit-transform: translate3d(0,2px,0); transform: translate3d(0,2px,0); }
	100% { -webkit-transform: translate3d(0,0,0); transform: translate3d(0,0,0); opacity: 1; }
}
@media (max-width: 600px) {
	.form_wrapper {
		.col_half {
			width: 100%;
			float: none;
		}
	}
	.bottom_row {
		.col_half {
			width: 50%;
			float: left;
		}
	}
	.form_container {
		.row {
			.col_half.last {
				border-left: none;
			}
		}
	}
	.remember_me {
		padding-bottom: 20px;
	}
}

/*Lọc  */
.product-search-form {
    background-color: #f9f9f9; /* Màu nền cho form */
    padding: 20px; /* Padding cho form */
    border-radius: 8px; /* Bo tròn góc */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Bóng cho form */
    max-width: 400px; /* Chiều rộng tối đa của form */
    margin: auto; /* Canh giữa form */
}

.form-label {
    font-weight: bold; /* Đậm cho nhãn */
    margin-top: 10px; /* Khoảng cách trên nhãn */
    display: block; /* Hiển thị nhãn trên một dòng mới */
}

.form-select,
.form-input {
    width: 100%; /* Chiều rộng 100% cho select và input */
    padding: 10px; /* Padding cho select và input */
    margin-top: 5px; /* Khoảng cách trên input */
    border: 1px solid #ced4da; /* Đường viền cho input */
    border-radius: 4px; /* Bo tròn góc */
    transition: border-color 0.3s; /* Hiệu ứng chuyển màu viền */
}

.form-select:focus,
.form-input:focus {
    border-color: #007bff; /* Màu viền khi focus */
    outline: none; /* Xóa outline mặc định */
}

.form-button {
    background-color: #007bff; /* Màu nền cho nút */
    color: white; /* Màu chữ cho nút */
    border: none; /* Không có viền */
    padding: 10px; /* Padding cho nút */
    border-radius: 4px; /* Bo tròn góc */
    cursor: pointer; /* Con trỏ khi hover */
    margin-top: 10px; /* Khoảng cách trên nút */
    width: 100%; /* Chiều rộng 100% cho nút */
    transition: background-color 0.3s; /* Hiệu ứng chuyển màu nền */
}

.form-button:hover {
    background-color: #0056b3; /* Màu nền khi hover */
}


.customer-reviews-container {
    background-color: #f8f9fa; /* Màu nền cho phần đánh giá */
    padding: 20px; /* Padding cho container */
    border-radius: 8px; /* Bo tròn góc */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Bóng cho container */
    margin-top: 20px; /* Khoảng cách phía trên */
}

/* Lọc của trang home */

.price-range {
    background-color: #f9f9f9; /* Màu nền cho phần lọc giá */
    padding: 20px; /* Padding cho phần lọc giá */
    border-radius: 8px; /* Bo tròn góc */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Bóng cho phần lọc giá */
    margin-bottom: 20px; /* Khoảng cách dưới phần lọc giá */
}

.price-range h2 {
    font-size: 20px; /* Kích thước chữ cho tiêu đề */
    margin-bottom: 15px; /* Khoảng cách dưới tiêu đề */
    color: #333; /* Màu chữ cho tiêu đề */
}

.form-control {
    width: 100%; /* Chiều rộng 100% cho input */
    padding: 10px; /* Padding cho input */
    margin-bottom: 10px; /* Khoảng cách dưới input */
    border: 1px solid #ced4da; /* Đường viền cho input */
    border-radius: 4px; /* Bo tròn góc cho input */
    transition: border-color 0.3s; /* Hiệu ứng chuyển màu viền */
}

.form-control:focus {
    border-color: #007bff; /* Màu viền khi focus */
    outline: none; /* Xóa outline mặc định */
}

.btn-primary {
    background-color: #007bff; /* Màu nền cho nút lọc */
    color: white; /* Màu chữ cho nút lọc */
    border: none; /* Không có viền */
    padding: 10px 15px; /* Padding cho nút */
    border-radius: 4px; /* Bo tròn góc cho nút */
    cursor: pointer; /* Con trỏ khi hover */
    transition: background-color 0.3s; /* Hiệu ứng chuyển màu nền */
}

.btn-primary:hover {
    background-color: #0056b3; /* Màu nền khi hover */
}

.sorting {
    background-color: #f9f9f9; /* Màu nền cho phần sắp xếp giá */
    padding: 20px; /* Padding cho phần sắp xếp giá */
    border-radius: 8px; /* Bo tròn góc */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Bóng cho phần sắp xếp giá */
}

.sorting h2 {
    font-size: 20px; /* Kích thước chữ cho tiêu đề */
    margin-bottom: 15px; /* Khoảng cách dưới tiêu đề */
    color: #333; /* Màu chữ cho tiêu đề */
}

.btn-secondary {
    background-color: #FE980F; /* Màu nền cho nút sắp xếp */
    color: white; /* Màu chữ cho nút sắp xếp */
    border: none; /* Không có viền */
    padding: 10px 15px; /* Padding cho nút */
    border-radius: 4px; /* Bo tròn góc cho nút */
    margin-bottom: 5px; /* Khoảng cách dưới nút */
    cursor: pointer; /* Con trỏ khi hover */
    transition: background-color 0.3s; /* Hiệu ứng chuyển màu nền */
    width: 100%; /* Chiều rộng 100% cho nút */
}

.btn-secondary:hover {
    background-color: #5a6268; /* Màu nền khi hover */
}





</style>