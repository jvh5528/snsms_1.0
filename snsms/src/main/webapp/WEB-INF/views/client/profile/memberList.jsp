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
<!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                     <table>
                     	<tbody id="memberList">
                     		<c:choose>
                     			<c:when test="${empty memberList }">
                     				<tr align="center">
                     					<td>No results found.</td>
                     				</tr>
                     			</c:when>
                     			<c:otherwise>
                     				<c:forEach var="profileDTO" items="${memberList}">
                     					
                     					<tr>
                     						<td>
                     							<c:choose>
                     								<c:when test="${profileDTO.username eq sessionScope.username }">
		                     							<a href="${contextPath }/profile/myProfile?username=${profileDTO.username}">
		                     							<img src="${contextPath }/profile/thumbnails?fileName=${profileDTO.profilePic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                     							</a>
                     								</c:when>
                     								<c:otherwise>
                     									<a href="${contextPath }/profile/memberDetail?username=${profileDTO.username}">
		                     							<img src="${contextPath }/profile/thumbnails?fileName=${profileDTO.profilePic}" width="50" height="50" style="border-radius:50%;" alt="profile picture">
		                     							</a>
                     								</c:otherwise>
                     							</c:choose>
                     							
                     						</td>
                     						<td><strong>${profileDTO.username}</strong> &emsp;&emsp;•&emsp;&emsp;  ${profileDTO.name }</td>
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
    <!-- Shop Cart Section End -->
</body>
</html>