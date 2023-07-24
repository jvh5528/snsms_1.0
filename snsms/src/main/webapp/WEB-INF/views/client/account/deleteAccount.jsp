<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/profile/myProfile"><i class="fa fa-home"></i> ${sessionScope.username }</a>
                        <span>Delete account</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Edit Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <form action="${contextPath }/member/deleteAccount" method="post" class="checkout__form">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Delete profile</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                            	<p>Are you sure?</p><br>
                            	<p>Your profile and related account information will be permanently deleted from our site.</p>
                                <div class="checkout__form__input">
                                	<br>
                                    <p>Password</p>
                                    <input type="password" id="passwd" name="passwd">
                                    <input type="hidden" id="username" name="username" value="${memberDTO.username }">
	                                <button type="submit" class="site-btn">Submit</button>
	                                <button type="button" class="site-btn" onclick="location.href='${contextPath}/profile/myProfile'">Return to profile</button>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
<!-- Edit Profile Section End -->
</body>
</html>