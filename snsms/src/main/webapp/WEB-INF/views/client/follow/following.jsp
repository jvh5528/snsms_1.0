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
<section class="shop-cart spad">
       <div class="container">
           <div class="row">
               <div class="col-lg-12">
                   <div class="shop__cart__table">       
                    <table>
                    	<tbody id="myFollowing">
                    		<c:choose>
                    			<c:when test="${empty followingList }">
                    				<tr align="center">
                    					<td>
                    						<strong>People You Follow</strong><br>
                    						<img src="${contextPath }/resources/img/addFollowers.png" width="50" height="50"><br>
                    						<span style="color:gray;">Once you follow people, you'll seem them here.</span>
                    					</td>
                    				</tr>
                    			</c:when>
                    			<c:otherwise>
                    				<c:forEach var="followingDTO" items="${followingList}">
                    					<tr>
                   							<c:choose>
                   								<c:when test="${followingDTO.followingUsername eq sessionScope.username}">
                   									<td>
                    									<a href="${contextPath }/profile/myProfile">
		                    							<img src="${contextPath }/profile/thumbnails?fileName=${followingDTO.followingPic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                    							</a>
		                    						</td>
		                    						<td><strong>${followingDTO.followingUsername}</strong> &emsp;&emsp;•&emsp;&emsp;  ${followingDTO.followingName }</td>
                   								</c:when>
                   								<c:otherwise>
                   									<td>
		                    							<a href="${contextPath }/profile/memberDetail?username=${followingDTO.followingUsername}">
		                    							<img src="${contextPath }/profile/thumbnails?fileName=${followingDTO.followingPic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                    							</a>
		                    						</td>
		                    						<td><strong>${followingDTO.followingUsername}</strong> &emsp;&emsp;•&emsp;&emsp;  ${followingDTO.followingName }</td>
                   								</c:otherwise>
                   							</c:choose>
                    					</tr>
                    				</c:forEach>
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