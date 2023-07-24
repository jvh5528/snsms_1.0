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
    	<form action="${contextPath }/comment/newComment" method="post">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-6">
	                    <div class="product__details__pic">
	                        <div class="product__details__slider__content">
	                            <div class="product__details__pic__slider owl-carousel">
	                                <img data-hash="product-1" class="product__big__img" id="image-preview" src="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}">
	                            </div><br>
	                            <div align="center"><strong>${postDTO.caption }</strong></div><br>
	                            <div>
		                            <c:choose>
		                            	<c:when test="${empty recentComments }">
		                            		<div align="center">
		                            		<strong>No comments yet.</strong><br>
		                            		<span style="color:gray; font-size:10px;">Start the conversation.</span><br><br>
		                            		</div>
		                            	</c:when>
			                            <c:otherwise>
			                            	<c:forEach var="commentDTO" items="${recentComments }">
			                            		<div align="left">
			                            			<table>
			                            				<tr>
				                            				<td><img src="${contextPath }/profile/thumbnails?fileName=${commentDTO.commenterPic}" style="border-radius:50%" width="50" height="50"></td> 
				                            				<td><strong>${commentDTO.commenter }</strong></td> 
				                            				<td>${commentDTO.comment }</td>
			                            				</tr>
			                            			</table>
			                            		</div><br>
			                            	</c:forEach>
					                     	<a href="${contextPath }/comment/commentList?mediaCd=${postDTO.mediaCd}">View all comments</a>
			                            </c:otherwise>
		                            </c:choose>
	                            </div><br>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-6">
	                    <div class="product__details__text" align="right">
	                        	<div class="contact__form">
	                   				<label><textarea rows="10" cols="50" id="comment" name="comment" placeholder="Add a comment"></textarea></label><br>
	                   				<script>
								    $("#comment").keyup(function(){
								        el = $(this);
								        if(el.val().length >= 500){
								            el.val( el.val().substr(0, 500) );
								        } else {
								            $("#wordCnt").text(500-el.val().length);
								        }
								    });
								    </script>
                                    <span id="wordCnt" style="color: gray; font-size: 10px;">500 </span><br>
	                        	</div><br><br>
	                        <div class="product__details__button">
	                            <div align="right">
	                            	<input type="hidden" id="mediaCd" name="mediaCd" value="${postDTO.mediaCd }"> 
	                            	<input type="hidden" id="commenter" name="commenter" value="${sessionScope.username }">
	                            	<input type="hidden" id="commenterPic" name="commenterPic" value="${profileDTO.profilePic }">
						           <button type="button" class="site-btn" onclick="history.go(-1)">Go back</button>
						           <button type="submit" class="site-btn">Post</button>
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