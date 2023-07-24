<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("form").submit(function() {
			if ($("#passwd").val() != $("#confirmPasswd").val()) {
				alert("Please ensure that your passwords match.");
				$("#passwd").focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        ${sessionScope.username } 
                        <span style="color:gray"> > Renew password</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Edit Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
           <form action="${contextPath}/profile/passwordRenewal" method="post" class="checkout__form">
			    <div class="row">
			        <div class="col-lg-8">
			            <h5>Password Expired</h5>
			            <div class="row">
			                <div class="col-lg-6 col-md-6 col-sm-6">
			                    <p>This page indicates that your password has expired.</p>
			                    <br>
			                    <p>Please select a new password.</p>
			                    <div class="checkout__form__input">
			                        <br>
			                        Enter new password <input type="password" id="passwd" name="passwd">
			                        Confirm new password <input type="password" id="confirmPasswd" name="confirmPasswd">
			                        <input type="hidden" name="username" value="${username}">
			                        <button type="submit" class="site-btn">Submit</button>
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