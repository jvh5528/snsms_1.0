<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="shop-cart spad">
       <div class="container">
           <div class="row">
               <div class="col-lg-12">
                   <div class="shop__cart__table">       
                    <table>
                    	<tbody id="likes">
                    		<c:choose>
                    			<c:when test="${empty myLikesList || empty myCommentsList || empty myFollowersList }">
                    				<div align="center">
                    				<img src="${contextPath }/resources/img/likedefault.png" width="100" height="100"><br>
                            		<strong>New activity will show up here.</strong><br>
                            		<a href="${contextPath }/profile/myProfile">Return to profile</a>
                            		</div>
                    			</c:when>
                    			<c:otherwise>
		                           	<c:forEach var="myLikesDTO" items="${myLikesList }">
		                           		<div align="left">
		                      				<img src="${contextPath }/profile/thumbnails?fileName=${myLikesDTO.likedbyProfilepic}" style="border-radius:50%" width="50" height="50">
		                      				<strong>${myLikesDTO.likedbyUsername }</strong>&nbsp; liked your post.&emsp;&emsp;<span style="color:gray; font-size:10px">
		                      				<fmt:formatDate value="${myLikesDTO.likeDt }" pattern="yyy-MM-dd"/>
		                      				
		                      				</span>
		                           		</div><br>
		                           	</c:forEach>
		                           	<c:forEach var="myCommentsDTO" items="${myCommentsList }">
		                           		<div align="left">
		                      				<img src="${contextPath }/profile/thumbnails?fileName=${myCommentsDTO.commenterPic}" style="border-radius:50%" width="50" height="50">
		                      				<strong>${myCommentsDTO.commenter }</strong>&nbsp; commented: ${myCommentsDTO.comment} &emsp;&emsp;<span style="color:gray; font-size:10px">
		                      				<fmt:formatDate value="${myCommentsDTO.commentDt }" pattern="yyyy-MM-dd"/>
		                      				</span>
		                           		</div><br>
		                           	</c:forEach>
		                           	<c:forEach var="myFollowersDTO" items="${myFollowersList }">
		                           		<div align="left">
		                      				<img src="${contextPath }/profile/thumbnails?fileName=${myFollowersDTO.followerPic}" style="border-radius:50%" width="50" height="50">
		                      				<strong>${myFollowersDTO.followerUsername }</strong>&nbsp; started following you. &emsp;&emsp;<span style="color:gray; font-size:10px">
		                      				<fmt:formatDate value="${myFollowersDTO.followDt }" pattern="dd 'days ago'"/>
		                      				</span>
		                           		</div><br>
		                           	</c:forEach>
				                  	<a href="${contextPath }/profile/myProfile">Return to profile</a>
                    			</c:otherwise>
                    		</c:choose>
                    	</tbody>
                    </table>
                </div>
           </div>
       </div>
   </div>
</section>
</body>
</html>