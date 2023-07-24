<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<c:set var="sessionId" value="${sessionScope.username }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
	function toHome(){
		
		if("${sessionId == null}" == "true"){
			alert("Log into myFeed to continue.");
			location.href = "${contextPath}/member/login";
		}
		else{
			location.href = "${contextPath}/post/getHomeMediaList";
		}
		
	}
	function toMyProfile(){
		
		if("${sessionId == null}" == "true"){
			alert("Log into myFeed to continue.");
			location.href = "${contextPath}/member/login";
		}
		else{
			location.href = "${contextPath}/profile/myProfile?username=${sessionId}";
		}
		
	}
	
	function toSearch(){
		
		if("${sessionId == null}" == "true"){
			alert("Log into myFeed to continue.");
			location.href = "${contextPath}/member/login";
		}
	}
	
	function toNotifications(){
		if ("${sessionId == null}" == "true"){
			alert("Log into myFeed to continue.");
			location.href = "${contextPath}/member/login";
		}
		else{
			location.href = "${contextPath}/like/notifications";
		}
	}
	
	function toCart(){
		if ("${sessionId == null}" == "true"){
			alert("Log into myFeed to continue.");
			location.href = "${contextPath}/member/login";
		}
		else{
			location.href = "${contextPath}/profile/myCartList";
		}
	}
</script>
</head>
<body>
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="#"><img src="${contextPath }/resources/img/logo.png" style="margin-left:20px;" width="50" height="50" alt="logo"></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                    	<ul>
                    	<c:choose>
                    		<c:when test="${sessionScope.role eq 'admin' }">
	                            <li><a href="${contextPath }/admin/posts/adminPostsList">Content Management</a></li>
	                            <li><a href="${contextPath }/admin/adminMemberList">User Management</a></li>
	                            <li><a href="${contextPath }/admin/order/fullOrderList">Order Management</a></li>
                    		</c:when>
                    		<c:otherwise>
	                            <li><a href="javascript:toHome()">Home</a></li>
	                            <li><a href="javascript:toMyProfile()">My Profile</a></li>
	                            <li><a href="${contextPath }/post/getExploreMediaList">Explore</a></li>
                    		</c:otherwise>
                    	</c:choose>
                       </ul> 
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <c:choose>
                        	<c:when test="${sessionScope.username eq null }">
                        		<div class="header__right__auth">
		                        	<a href="${contextPath }/member/login">Login</a>
		                            <a href="${contextPath }/member/register">Register</a>
		                        </div>
                        	</c:when>
                        	<c:otherwise>
                        		<div class="header__right__auth">
		                            <a href="${contextPath }/member/logout">Logout</a>
		                        </div>
                        	</c:otherwise>
                        </c:choose>
                            
                        <ul class="header__right__widget">
                            <li><a href="javascript:toSearch()"><span class="icon_search search-switch"></span></a></li>
                            <li><a href="javascript:toNotifications()"><span class="icon_heart_alt"></span>
                            </a></li>
                            <li><a href="javascript:toCart()"><span class="icon_cart_alt"></span>
                            	<c:choose>
                                	<c:when test="${sessionScope.role == 'client'}">
		                                <div class="tip">${sessionScope.myCartCnt }</div>
                                	</c:when>
                                	<c:otherwise>
                                		<div class="tip">0</div>
                                	</c:otherwise>
                                </c:choose>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
</body>
</html>