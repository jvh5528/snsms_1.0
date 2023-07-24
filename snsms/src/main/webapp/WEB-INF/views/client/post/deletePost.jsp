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
<!-- Product Details Section Begin -->
    <section class="product-details spad">
    	<form action="${contextPath }/post/deletePost?mediaCd=${postDTO.mediaCd}" enctype="multipart/form-data" method="post" onsubmit="return confirm('Post cannot be restored once deleted. Are you sure you want to delete this post?')">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-6">
	                    <div class="product__details__pic">
	                        <div class="product__details__slider__content">
	                            <div class="product__details__pic__slider owl-carousel">
	                                <img data-hash="product-1" class="product__big__img" id="image-preview" src="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-6">
	                    <div class="product__details__text" align="right">
	                        	<div class="contact__form">
	                   				<label><textarea rows="10" cols="50" id="caption" name="caption" readonly>${postDTO.caption }</textarea></label>
	                        	</div>
	                        	<div class="product__details__widget">
		                    		<div class="stock__checkbox" >
		                    		   <span style="align:center; font-family:Montserrat;"><strong>${totalLikesCnt } Likes </strong></span><br><br>
		                    		   <span style="align:center; font-family:Montserrat;"><strong>${totalCommentCnt } Comments </strong></span><br><br>
		                            </div>
	                    		</div><br><br>
	                        <div class="product__details__button">
	                            <div align="right">
						           <button type="button" class="site-btn" onclick="location.href='${contextPath}/profile/myProfile'">Return to profile</button>
						           <button type="submit" class="site-btn">Delete</button>
	                    		</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
        </form>
    </section>
<!-- Product Details Section End -->
</body>
</html>