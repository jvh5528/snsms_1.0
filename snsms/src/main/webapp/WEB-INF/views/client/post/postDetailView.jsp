<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<c:set var="sessionId" value="${sessionScope.username}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.icon_heart{
	color: #FF0000;
}
</style>
<script>
	function processToComment(mediaCd){
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
			location.href = "${contextPath}/comment/newComment?mediaCd="+mediaCd+"";
		}
	} 
	
	function processToLike(mediaCd){
		
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
			
			var param = {
				"mediaCd" 			: mediaCd,
				"likedbyUsername" 	: $("#likedbyUsername").val(),
				"likedbyProfilepic" : $("#likedbyProfilepic").val()
			}
			
			$.ajax({
				url : "${contextPath}/like/addLike",
				method : "post",
				data : param,
				success : function(result){
					if (result == "Y"){
						location.reload();
					}
					else{
						location.reload();
					}
				}
			}); 
		}
	}
	
	function processToCart(mediaCd){
		
		if ("${sessionId == null}" == "true"){
			alert("Please log in to continue.");
			location.href = "${contextPath }/member/login";
		}
		else{
						
			$.ajax({
				url : "${contextPath}/profile/addCart",
				method : "get",
				data : {"mediaCd" : mediaCd , "cartGoodsQty" : 1},
				success : function(result){
					if (result == "duple"){
						alert("Looks like it's already in your cart!");
					}
					else{
						alert("Added to cart.");
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
                        <a href="${contextPath }/profile/myProfile"><i class="fa fa-home"></i> ${sessionScope.username }</a>
                        <a href="${contextPath }/profile/memberDetail?username=${postDTO.username}" style="color:gray;">${postDTO.username } </a>
                    </div>
                </div>
            </div>
        </div>
    </div><br>
<!-- Breadcrumb End -->
<!-- Blog Details Section Begin -->
     <section class="blog-details spad"> 
        <div class="container">
            <div class="row">
                <div align="center">
                    <div class="blog__details__content">
                        <div class="blog__details__item">
                            <img src="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}" width="300" height="300">
                        </div>
                        <div class="blog__details__desc">
                            <div>
                            	<span>${postDTO.caption } &emsp;
                            		<c:choose>
                            			<c:when test="${sellYn eq 'N' }">
                            				<c:choose>
												<c:when test="${likeCheck eq null}">
													<a href="javascript:processToLike(${postDTO.mediaCd})" id="likeBtn" class="icon_heart_alt"></a>
													<span style="font-size:10px; color:blue;">${totalLikeCnt }</span>&nbsp;
												</c:when>
												<c:otherwise>
													<a href="javascript:processToLike(${postDTO.mediaCd})" id="likeBtn" class="icon_heart"></a>
									                <span style="font-size:10px; color:blue;">${totalLikeCnt }</span>&nbsp;
												</c:otherwise>
											</c:choose>
                            			</c:when>
                            			<c:otherwise>
                            				<c:choose>
												<c:when test="${likeCheck eq null}">
													<a href="javascript:processToLike(${postDTO.mediaCd})" id="likeBtn" class="icon_heart_alt"></a>
													<span style="font-size:10px; color:blue;">${totalLikeCnt }</span>&nbsp;
												</c:when>
												<c:otherwise>
													<a href="javascript:processToLike(${postDTO.mediaCd})" id="likeBtn" class="icon_heart"></a>
									                <a href="${contextPath }/like/likesList?mediaCd=${postDTO.mediaCd}"><span style="font-size:10px; color:blue;">${totalLikeCnt }</span></a>&nbsp;
												</c:otherwise>
											</c:choose>
							                <a href="javascript:processToCart(${postDTO.mediaCd})"><span class="icon_cart_alt" id="addToCartBtn"></span></a> &nbsp; 
                            			</c:otherwise>
                            		</c:choose>
                            		<input type="hidden" id="likedbyUsername" name="likedbyUsername" value="${sessionScope.username }">
									<input type="hidden" id="likedbyProfilepic" name="likedbyProfilepic" value="${myProfile.profilePic }">
                            	</span>
                            </div>
                        </div>
                    </div>
                </div>
                 <div align="left">
                    <c:choose>
                    	<c:when test="${empty commentList }">
                    		<div align="center">
                    			<strong>No comments yet.</strong><br>
                    			<a href="javascript:processToComment(${postDTO.mediaCd})" class="icon_chat_alt"></a>&nbsp;
                    			<span>Leave a comment</span>
                    		</div>
                    	</c:when>
                    	<c:otherwise>
		                    <span>
		                    	${totalCommentCnt } Comment &emsp;&emsp;
			                    <a href="${contextPath }/comment/newComment?mediaCd=${postDTO.mediaCd}" class="icon_chat_alt">
			                    </a>&nbsp;
			                    <span>Leave a comment</span>
		                    </span>
		                    <p></p>
                    		<c:forEach var="commentDTO" items="${commentList }">
                    			<input type="hidden" id="mediaCd" name="mediaCd" value="${postDTO.mediaCd }">
								
                    			<div class="blog__comment__item">
                        			<div class="blog__comment__item__pic">
                        				<span>
	                            			<img src="${contextPath }/profile/thumbnails?fileName=${commentDTO.commenterPic}" alt="" width="30" height=30">
	                            			<strong>${commentDTO.commenter }</strong>&nbsp;
	                            			${commentDTO.comment }&emsp;
	                            			<span style="color:gray; font-size:10px;"> <fmt:formatDate value="${commentDTO.commentDt }" type="date" dateStyle="short"/> </span>
                            			</span>
                        			</div>
                    			</div><br>
                    		</c:forEach>
                    		<a href="${contextPath }/comment/commentList?mediaCd=${postDTO.mediaCd}">View all comments</a>
                    	</c:otherwise>
                    </c:choose>
                 </div> 
             </div>
        </div>
   </section>
    <!-- Blog Details Section End -->
</body>
</html>