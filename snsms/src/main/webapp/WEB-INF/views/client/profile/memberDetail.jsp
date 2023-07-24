<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<c:set var="sessionId" value="${sessionScope.username}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
.icon_heart{
	color: #FF0000;
}
</style>
<script>


	function processFollow() {
		
		var isNewFollow = false; 
		
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
			var param = {
					"username" 			: $("#username").val() , 
					"followingUsername" : $("#followingUsername").val() , 
					"followingName" 	: $("#followingName").val(),
					"followingPic" 		: $("#followingPic").val(),
					"followUsername" 	: $("#followUsername").val(),
					"followerUsername" 	: $("#followerUsername").val(),
					"followerName" 		: $("#followerName").val(),
					"followerPic" 		: $("#followerPic").val()
			};
			
			$.ajax({
				url : "${contextPath}/follow/addtoFollowingList",
				type : "post",
				data : param,
				success : function(isDuple){
					if (isDuple == "N"){
						location.reload();
					}
					else{
						location.reload();
					} 
				}
			});
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
                        <a href="${contextPath }/profile/myProfile"><i class="fa fa-home"></i> ${memberDTO.username }</a>
                        <a href="${contextPath }/profile/memberDetail?username=${profileDTO.username}" style="color:gray;">${profileDTO.username } </a>
                    </div>
                </div>
            </div>
        </div>
    </div><br>
    <!-- Breadcrumb End -->

	<div align="center">
		<a><span>${allPostCnt } posts</span></a>&emsp;&emsp; 
		<c:choose>
			<c:when test="${empty followersList }">
				<a href="#"><span>${followersCnt } followers</span></a>&emsp;&emsp;
			</c:when>
			<c:otherwise>
				<a href="${contextPath }/follow/followersList?followUsername=${profileDTO.username}"><span>${followersCnt } followers</span></a>&emsp;&emsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty followingList }">
				<a href="#"><span>${followingCnt } following</span></a>
			</c:when>
			<c:otherwise>
				<a href="${contextPath }/follow/followingList?username=${profileDTO.username}"><span>${followingCnt } following</span></a>
			</c:otherwise>
		</c:choose>
	</div><br><br>
	<div align="center">
		
			<input type="hidden" id="username" 			name="username"		 		value="${sessionScope.username }">
			<input type="hidden" id="followingUsername" name="followingUsername" 	value="${profileDTO.username }">
			<input type="hidden" id="followingName" 	name="followingName" 	 	value="${profileDTO.name }">
			<input type="hidden" id="followingPic" 		name="followingPic" 	 	value="${profileDTO.profilePic }">
			
			<input type="hidden" id="followUsername" 	name="followUsername"		value="${profileDTO.username }">
			<input type="hidden" id="followerUsername" 	name="followerUsername" 	value="${myProfileDTO.username }">
			<input type="hidden" id="followerName" 		name="followerName" 		value="${myProfileDTO.name }">
			<input type="hidden" id="followerPic" 		name="followerPic" 	 		value="${myProfileDTO.profilePic }">
			<input type="checkbox" class='chk-btn' name="followbtn" id="followbtn" onchange="processFollow();"/>
			<c:choose>
				<c:when test="${followCheck eq null}">
					<label for="followbtn"><span id="followBtnText">Follow</span></label>
				</c:when>
				<c:otherwise>
					<label for="followbtn"><span id="unfollowBtnText">Unfollow</span></label>
				</c:otherwise>
				
			</c:choose>
		
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
                                <h4>${profileDTO.username }</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                    <p>${profileDTO.name } </p><br>
                                    <p>${profileDTO.bio }</p><br>
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
		                         	<img src="${contextPath }/resources/img/defaultPost.png" width="200px" height="200px"><br>
		                         	<span style="color:gray">No posts yet.</span><br><br>
		                        </div>
                    			<div class="col-lg-4 col-md-6">
		                        </div>
                    		</c:when>
                    		<c:otherwise>
		                    	<c:forEach var="postDTO" items="${postList }">
		                    		<input type="hidden" id="mediaCd" name="mediaCd" value="${postDTO.mediaCd }">
									<input type="hidden" id="likedbyUsername" name="likedbyUsername" value="${sessionScope.username }">
									<input type="hidden" id="likedbyProfilepic" name="likedbyProfilepic" value="${myProfileDTO.profilePic }">
			                        <div class="col-lg-4 col-md-6">
			                            <div class="product__item">
			                                <div class="product__item__pic set-bg" data-setbg="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}" id="openPhotoDetail">
			                                    <ul class="product__hover">
			                                       <li><a href="${contextPath }/post/postDetailView?mediaCd=${postDTO.mediaCd}"><span class="arrow_expand"></span></a></li>
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
					                <a href="${contextPath}/profile/memberDetail?username=${profileDTO.username}&currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
					                        class="fa fa-angle-left"></i></a>
					            </c:if>
					            <c:forEach var="i" begin="${startPage}" end="${endPage}">
					                <a href="${contextPath}/profile/memberDetail?username=${profileDTO.username}&currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
					            </c:forEach>
					            <c:if test="${endPage != allPageCnt && endPage >= 10}">
					                <a href="${contextPath}/profile/memberDetail?username=${profileDTO.username}&currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
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