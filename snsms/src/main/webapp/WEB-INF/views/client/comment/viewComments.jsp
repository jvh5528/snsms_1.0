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
                    	<tbody id="myFollowing">
                    		<c:choose>
                            	<c:when test="${empty commentList }">
                            		<div align="center">
                            		<img src="${contextPath }/resources/img/chatdefault.png" width="100" height="100"><br>
                            		<strong>No comments yet.</strong><br>
                            		<a href="${contextPath }/profile/myProfile">Return to profile</a>
                            		</div>
                            	</c:when>
	                            <c:otherwise>
	                            	<c:forEach var="commentDTO" items="${commentList }">
	                            		<div align="left">
	                           				<img src="${contextPath }/profile/thumbnails?fileName=${commentDTO.commenterPic}" style="border-radius:50%" width="50" height="50"> 
	                           				<strong>${commentDTO.commenter }</strong>
	                           				${commentDTO.comment } &emsp;&emsp;
	                           				<span style="color:gray; font-size:10px"><fmt:formatDate value="${commentDTO.commentDt }" pattern="dd 'days ago'"/></span>
	                            		</div><br>
	                            	</c:forEach>
			                     	<a href="${contextPath }/profile/myProfile">Return to profile</a>
	                            </c:otherwise>
                            </c:choose>
                    	</tbody>
                    </table>
                    <div class="col-lg-12 text-center">
                       <div class="pagination__option">
                           <c:if test="${startPage > 10}">
				                <a href="${contextPath}/comment/commentList?mediaCd=${postDTO.mediaCd}&currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
				                        class="fa fa-angle-left"></i></a>
				            </c:if>
				            <c:forEach var="i" begin="${startPage}" end="${endPage}">
				                <a href="${contextPath}/comment/commentList?mediaCd=${postDTO.mediaCd}&currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
				            </c:forEach>
				            <c:if test="${endPage != allPageCnt && endPage >= 10}">
				                <a href="${contextPath}/comment/commentList?mediaCd=${postDTO.mediaCd}&currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
				                        class="fa fa-angle-right"></i></a>
				            </c:if>
                       </div>
                   </div>
                </div>
           </div>
       </div>
   </div>
</section>
</body>
</html>