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
                    			<c:when test="${empty followersList }">
                    				<tr align="center">
                    					<td>
                    						<strong>People You Follow</strong><br>
                    						<img src="${contextPath }/resources/img/addFollowers.png" width="50" height="50"><br>
                    						<span style="color:gray;">Once you follow people, you'll seem them here.</span>
                    					</td>
                    				</tr>
                    			</c:when>
                    			<c:otherwise>
                    				<c:forEach var="followersDTO" items="${followersList}">
                    					<tr>
                    						<c:choose>
                   								<c:when test="${followersDTO.followerUsername eq sessionScope.username}">
                   									<td>
                    									<a href="${contextPath }/profile/myProfile">
		                    							<img src="${contextPath }/profile/thumbnails?fileName=${followersDTO.followerPic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                    							</a>
		                    						</td>
		                    						<td><strong>${followersDTO.followerUsername}</strong> &emsp;&emsp;•&emsp;&emsp;  ${followersDTO.followerName }</td>
                   								</c:when>
                   								<c:otherwise>
		                							<td>
		                   								<a href="${contextPath }/profile/memberDetail?username=${followersDTO.followerUsername}">
		                   								<img src="${contextPath }/profile/thumbnails?fileName=${followersDTO.followerPic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                   								</a>
		                   							</td>
		                   							<td><strong>${followersDTO.followerUsername}</strong> &emsp;&emsp;•&emsp;&emsp;  ${followersDTO.followerName }</td>
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