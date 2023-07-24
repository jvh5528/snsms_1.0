<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	var isNew = false;
	
	$().ready(function(){
		
		$("#username").blur(function(){
			
			$.ajax({
				
				url : "${contextPath}/member/checkMemberStatus",
				type : "post",
				data : {"username" : $("#username").val() , "userStatus" : $("#userStatus").val()},
				success: function(result){
					 if (result == "new"){
						isNew = true;
					}
					else{
						isNew = false;
					}
				}
			
			});
		});
	});
	
</script>
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
                            <form action="${contextPath }/member/login" method="post">
                                <input type="text" name="username" id="username" placeholder="Username">
                                <input type="password" name="passwd" placeholder="Password">
                                <input type="hidden" id="userStatus" name="userStatus" value="${memberDTO.userStatus }">
                                <div align="center">
                                  <button type="submit" class="site-btn">Login</button> <br><br>
                                </div>
                            </form><br>
                        </div>
                    </div><br>
                    <div style="border:1px solid gray; padding: 10px 10px 10px 10px" align="center">
                       Don't have an account? <a href="${contextPath}/member/register">Sign up</a>
                    </div>
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