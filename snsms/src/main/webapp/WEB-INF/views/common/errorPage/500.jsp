<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Unexpected Error</title>
    <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Semi+Condensed:100,200,300,400" rel="stylesheet">
    <link href="${contextPath }/resources/errorpage/500css/style.css" rel="stylesheet" type="text/css" />
	<link href="/assets/org/css/selecthealth.css" rel="stylesheet" type="text/css" media="screen, projection" />

</head>

<body class="loading">
<div class="error-502__container">
	<div class="error-502">
		<div class="error-502__text">
			<h1>Unexpected Error <b>:(</b></h1>
			<h2><p>You can go back to your <a href="${contextPath }/profile/myProfile">profile</a>.</p></h2>
		</div>
	</div>
</div>
<div class="gears">
    <div class="gear one">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
    <div class="gear two">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
    <div class="gear three">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/main.js" type="text/javascript"></script>
<script>
    $(function() {
        setTimeout(function(){
            $('body').removeClass('loading');
        }, 1000);
    });
</script>
</body>
</html>
