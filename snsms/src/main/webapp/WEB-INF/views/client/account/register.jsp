<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath }/resources/jquery/jquery-3.6.1.min.js"></script>
<script>
	
	var isValidId = false;
	var isValidEmail = false;
	
	function checkformData(){
		
		if (!isValidId){
			alert("Please choose a different username.");
			$("#username").focus();
			return false;
		}
		
		if ($("#username").val() == ""){
			alert("Please enter a username.");
			$("#username").focus();
			isValidId = false;
			return false;
		}
		
		if ($("#name").val() == ""){
			alert("Please provide your name.");
			$("#username").focus();
			return false;
		}
		
	}
	
	$().ready(function(){
		
		$("#username").blur(function(){
			
			$.ajax({
				
				url : "${contextPath}/member/checkDuplicateId",
				type : "post",
				data : {"username" : $("#username").val()},
				success : function(isDuple){
					if (isDuple == "N"){
						$("#idDupleCheckMsg").html("<span style='color:green; font-size: 10px;'>Username is available.</span>");
						isValidId = true;
					}
					else{
						$("#idDupleCheckMsg").html("<span style='color:red; font-size: 10px;'>Username already exists.</span>");
						isValidId = false;
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
        <div class="container">
            <div class="row">
                <div>
                    <div class="contact__content" style="border:1px solid gray; padding: 10px 10px 10px 10px">
                        <div class="contact__form">
                            <div align="center">
                            	<img alt="logo" src="${contextPath }/resources/img/logo.png" width="100" height="100">
                            	<h6 style="color:gray;"><strong>Sign up to share and sell your photos</strong></h6><br>
                            </div>
                            <form action="${contextPath }/member/register" method="post" onsubmit="return checkformData();">
                                <input type="text" id="email" name="email" placeholder="Email" required><br><span id="emailMsg"></span>
                                <input type="text" id="name" name="name" placeholder="Full Name" required>
                                <input type="text" id="username" name="username" placeholder="Username" required><span id="idDupleCheckMsg"></span>
                                <input type="password" id="passwd" name="passwd" placeholder="Password" required><br>
                                <p style="color:gray; font-size:10px;" align="center">People who use our service may have uploaded your contact information to MyFeed.</p>
                                <div align="center">
	                                <button type="submit" class="site-btn">Sign up</button><br><br>
                                </div>
                            </form>
                        </div>
                    </div><br>
                    <div style="border:1px solid gray; padding: 10px 10px 10px 10px" align="center">
                      <span style="font-size:14px;">Have an account?</span> <a href="${contextPath}/member/login">Log in</a>
                    </div>
                </div>
        </div>
    </div>
</section>
<!-- Contact Section End -->
</body>
</html>