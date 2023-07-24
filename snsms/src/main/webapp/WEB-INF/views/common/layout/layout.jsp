<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><tiles:insertAttribute name="title"/></title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/bootstrap/css/style.css" type="text/css">
    <script src="${contextPath }/resources/bootstrap/js/jquery-3.3.1.min.js"></script>
    <!-- <script src="${contextPath }/resources/ckeditor/ckeditor.js"></script> -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
		function execDaumPostcode() {
		    new daum.Postcode({
		        oncomplete: function(data) {
		
		            var fullRoadAddr = data.roadAddress; 
		            var extraRoadAddr = ''; 
		
		            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                extraRoadAddr += data.bname;
		            }
		            if (data.buildingName !== '' && data.apartment === 'Y'){
		               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		            }
		            if (extraRoadAddr !== ''){
		                extraRoadAddr = ' (' + extraRoadAddr + ')';
		            }
		            if (fullRoadAddr !== ''){
		                fullRoadAddr += extraRoadAddr;
		            }
		
		            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
		            document.getElementById('roadAddress').value = fullRoadAddr;
		            document.getElementById('jibunAddress').value = data.jibunAddress;
		          
		        }
		    }).open();
		}
	</script>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="#"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="./index.html"><img src="img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="#">Login</a>
            <a href="#">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
		<tiles:insertAttribute name="header"/>
    <!-- Header Section End -->

	<!-- Content Begin -->
		<tiles:insertAttribute name="body"/>
	<!-- Content End -->
	
    <!-- Footer Section Begin -->
    	<tiles:insertAttribute name="footer"/>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form action="${contextPath }/profile/searchMember" name="search" method="get" class="search-model-form">
                <input type="text" id="search-input" name="word" placeholder="Search">
                <input type="hidden" name="method" value="search">
                <span onmouseenter="searchMember()" class="icon_search"></span>
            </form>
            <script>
            	function searchMember(){
            		if (document.search.word.value != ""){
            			document.search.submit();
            		}
            	}
            </script>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    
    <script src="${contextPath }/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/jquery.magnific-popup.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/jquery-ui.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/mixitup.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/jquery.countdown.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/jquery.slicknav.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/owl.carousel.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/jquery.nicescroll.min.js"></script>
    <script src="${contextPath }/resources/bootstrap/js/main.js"></script>
</body>

</html>