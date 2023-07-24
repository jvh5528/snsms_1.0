<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<c:set var="sessionId" value="${sessionScope.username}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${memberDTO.username }</title>
<script>
	function processToDetail(mediaCd){
		
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
			location.href = "${contextPath }/post/editPost?mediaCd="+mediaCd+"";		
		}
	}
	
	function processToDelete(mediaCd){
		
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
			location.href = "${contextPath }/post/deletePost?mediaCd="+mediaCd+"";		
		}
	}
</script>
</head>
<body>
<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <i class="fa fa-home"></i> ${memberDTO.username }
                    </div>
                </div>
            </div>
        </div>
    </div><br>
    <!-- Breadcrumb End -->

	<div align="center">
		<span>${allPostCnt } posts</span> &emsp;&emsp;
		<a href="${contextPath }/follow/followersList?followUsername=${memberDTO.username}"><span>${myFollowersCnt} followers</span></a>&emsp;&emsp;
		<a href="${contextPath }/follow/followingList?username=${memberDTO.username}"><span>${myFollowingCnt } following</span></a> &emsp;&emsp;
	</div><br><br>
	<div align="center">
		<button class="site-btn" onclick="location.href='${contextPath}/post/newPost'">+ Upload</button>
	</div>
    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="">
                            	<img src="${contextPath }/profile/thumbnails?fileName=${profileDTO.profilePic}" width="100" height="100" style="border-radius:50%;" alt="profile picture"><br><br>
                                <h4>${memberDTO.username }</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                    <p>${profileDTO.name } </p><br>
                                    <p>${profileDTO.bio }</p><br>
                                    <button class="site-btn" onclick="location.href='${contextPath}/profile/editProfile'">Edit profile</button><br><br>
                                    <button class="site-btn" onclick="location.href='${contextPath}/profile/myOrderList'">My orders</button><br><br>
                                    <button class="site-btn" onclick="location.href='${contextPath}/profile/myIncomingOrderList'">Incoming orders</button>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar__filter">
                        
                        </div>
                        <div class="sidebar__sizes">
                        </div>
                        <div class="sidebar__color">
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="row">
                    	<c:choose>
                    		<c:when test="${empty postList}">
                    			<div class="col-lg-4 col-md-6">
		                        </div>
		                        <div class="col-lg-4 col-md-6" align="center">
		                        	<strong>Photos</strong><br>
		                         	<span style="color:gray">When you share a photo, they'll appear here.</span><br><br>
		                        </div>
		                        <div class="col-lg-4 col-md-6">
		                        </div>
                    		</c:when>
                    		<c:otherwise>
		                    	<c:forEach var="postDTO" items="${postList }">
			                        <div class="col-lg-4 col-md-6">
			                            <div class="product__item">
			                                <div class="product__item__pic set-bg" data-setbg="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}" id="openPhotoDetail">
			                                    <ul class="product__hover">
			                                       <li><a href="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}" class="image-popup"><span class="arrow_expand"></span></a></li>
			                                       <li><a href="javascript:processToDetail(${postDTO.mediaCd})"><span class="icon_pencil"></span></a></li>
			                                       <li><a href="javascript:processToDelete(${postDTO.mediaCd})"><span class="icon_trash"></span></a></li>
			                                    </ul>
			                                </div>
			                            </div>
			                        </div>
		                    	</c:forEach>
                    		</c:otherwise>
                    	</c:choose>
                    	<div class="col-lg-12 text-center">
                            <div class="pagination__option">
                                  <c:if test="${startPage > 10}">
					                <a href="${contextPath}/profile/myProfile?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
					                        class="fa fa-angle-left"></i></a>
					            </c:if>
					            <c:forEach var="i" begin="${startPage}" end="${endPage}">
					                <a href="${contextPath}/profile/myProfile?currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
					            </c:forEach>
					            <c:if test="${endPage != allPageCnt && endPage >= 10}">
					                <a href="${contextPath}/profile/myProfile?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
					                        class="fa fa-angle-right"></i></a>
					            </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Section End -->
</body>
</html>