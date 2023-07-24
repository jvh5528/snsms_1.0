<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$().ready(function(){
		
			
		$("#sellYn").change(function(){
			
			var productOptionSelected = false;
			var message = "";
			var productOptions = $("[name='sellYn']:checked").val();
			
			if (productOptions == "Y"){
				message = "<input type='text' id='price' name='price' placeholder='Price' value='${postDTO.price }' required><span style='color:red' id='pricemsg'>*</span><br><input type='text' id='deliveryPrice' name='deliveryPrice' placeholder='Delivery price' value='${postDTO.deliveryPrice }' required><span style='color:red' id='deliverypricemsg'>*</span><br><input type='text' id='stock' name='stock' placeholder='Stock quantity' value='${postDTO.stock }' required><span style='color:red' id='qtymsg'>*</span>"
				productOptionSelected = true;
			}
			
		 	else{
		 		productOptionSelected = false;
			} 
			$("#productOptions").html(message);
		});
		
	});
	
</script>
</head>
<body>
<!-- Product Details Section Begin -->
    <section class="product-details spad">
    	<form action="${contextPath }/post/editPost" enctype="multipart/form-data" method="post">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-6">
	                    <div class="product__details__pic">
	                        <div class="product__details__slider__content">
	                            <div class="product__details__pic__slider owl-carousel">
	                                <img data-hash="product-1" class="product__big__img" id="image-preview" src="${contextPath }/profile/thumbnails?fileName=${postDTO.photo}">
	                            </div>
	                            <div>
		                            <c:choose>
		                            	<c:when test="${empty commentList }">
		                            		<div align="center">
		                            		<strong>No comments yet.</strong>
		                            		</div>
		                            	</c:when>
			                            <c:otherwise>
			                            	<c:forEach var="commentDTO" items="${commentList }">
			                            		<div align="left">
			                            			<table>
			                            				<tr>
				                            				<td><img src="${contextPath }/profile/thumbnails?fileName=${commentDTO.commenterPic}" style="border-radius:50%" width="50" height="50"></td>
				                            				<td><strong>${commentDTO.commenter }</strong></td>
				                            				<td>${commentDTO.comment } &emsp;&emsp;<span style="color:gray; font-size:10px"><fmt:formatDate value="${commentDTO.commentDt }" pattern="dd 'days ago'"/></span></td>
				                            				
			                            				</tr>
			                            			</table>
			                            		</div><br>
			                            	</c:forEach>
						                    <a href="${contextPath }/comment/newComment?mediaCd=${postDTO.mediaCd}">Leave a comment</a><br>
						                    <a href="${contextPath }/comment/commentList?mediaCd=${postDTO.mediaCd}">View all comments</a>
			                            </c:otherwise>
		                            </c:choose>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-6">
	                    <div class="product__details__text" align="right">
	                        	<div class="contact__form">
	                   				<label><textarea rows="10" cols="50" id="caption" name="caption">${postDTO.caption }</textarea></label><br>
	                   				<script>
								    $("#caption").keyup(function(){
								        el = $(this);
								        if(el.val().length >= 500){
								            el.val( el.val().substr(0, 500) );
								        } else {
								            $("#wordCnt").text(500-el.val().length);
								        }
								    });
								    </script>
                                    <span id="wordCnt" style="color: gray; font-size: 10px;">500 </span><br>
	                        	</div>
	                        	<div class="product__details__widget">
		                    		<div class="stock__checkbox" >
		                    		   <span style="align:center; font-family:Montserrat;"><strong><a href="${contextPath }/like/likesList?mediaCd=${postDTO.mediaCd}">${likesCnt } Likes </a></strong></span><br><br>
		                    		   <span style="align:center; font-family:Montserrat;"><strong><a href="${contextPath }/comment/commentList?mediaCd=${postDTO.mediaCd}">${totalCommentCnt } Comments </a></strong></span><br><br>
		                               <label for="stockin">
		                                   Is this a product?
		                                   <input type="checkbox" id="sellYn" name="sellYn" value="Y" <c:if test="${postDTO.sellYn eq 'Y' }">checked</c:if> /> <br>
		                                   <span class="checkmark" ></span>
		                                   <span id="productOptions"></span>
		                               </label>
		                            </div>
	                    		</div><br><br>
	                        <div class="product__details__button">
	                            <div align="right">
	                            	<input type="hidden" id="mediaCd" name="mediaCd" value="${postDTO.mediaCd }"> 
						           <button type="button" class="site-btn" onclick="location.href='${contextPath}/profile/myProfile'">Return to profile</button>
						           <button type="submit" class="site-btn">Edit</button>
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