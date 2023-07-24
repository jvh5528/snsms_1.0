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
<!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container" >
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="contact__content" style="border:1px solid gray; padding: 10px 10px 10px 10px">
                        <div class="contact__form">
                        	<div align="center">
	                            <img alt="logo" src="${contextPath }/resources/img/logo.png" width="100" height="100">
                        	</div>
                            <form action="${contextPath }/admin/adminLogin" method="post">
                                <input type="text" name="adminId" id="adminId" placeholder="Username">
                                <input type="password" name="passwd" placeholder="Password">
                                <div align="center">
                                  <button type="submit" class="site-btn">Login</button> <br><br>
                                </div>
                            </form><br>
                        </div>
                    </div><br>
                </div>
	           <div class="col-lg-6 col-md-6">
	           	<div class="contact__map">
	             <img src="${contextPath }/resources/img/iphonemockup.png" height="450" style="border:0" allowfullscreen="">
	           	</div>
	           </div>
        </div>
    </div>
</section>
<!-- Contact Section End -->
</body>
</html>