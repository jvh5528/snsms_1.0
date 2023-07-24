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
                    	<tbody id="likes">
                    		<c:choose>
                    			<c:when test="${empty likesList }">
                    				<div align="center">
                    				<img src="${contextPath }/resources/img/likedefault.png" width="100" height="100"><br>
                            		<strong>You'll see who liked your post here.</strong><br>
                            		<a href="javascript:void(0);" onclick="history.go(-1);">Return to post</a>
                            		</div>
                    			</c:when>
                    			<c:otherwise>
		                           	<c:forEach var="likeDTO" items="${likesList }">
		                           		<div align="left">
		                      				<a href="${contextPath }/profile/memberDetail?username=${likeDTO.likedbyUsername}"><img src="${contextPath }/profile/thumbnails?fileName=${likeDTO.likedbyProfilepic}" style="border-radius:50%" width="50" height="50"></a>
		                      				<strong>${likeDTO.likedbyUsername }</strong>
		                           		</div><br>
		                           	</c:forEach>
				                  	<a href="javascript:void(0);" onclick="history.go(-1);">Return to post</a>
                    			</c:otherwise>
                    		</c:choose>
                    	</tbody>
                    </table>
                </div>
           </div>
           <div class="col-lg-12 text-center">
             <div class="pagination__option">
               <c:if test="${startPage > 10}">
			      <a href="${contextPath}/like/likesList?mediaCd=${param.mediaCd}&currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
			              class="fa fa-angle-left"></i></a>
			  </c:if>
			  <c:forEach var="i" begin="${startPage}" end="${endPage}">
			      <a href="${contextPath}/like/likesList?mediaCd=${param.mediaCd}&currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
			  </c:forEach>
			  <c:if test="${endPage != allPageCnt && endPage >= 10}">
			      <a href="${contextPath}/like/likesList?mediaCd=${param.mediaCd}&currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
			              class="fa fa-angle-right"></i></a>
			  </c:if>
             </div>
         </div>
       </div>
   </div>
</section>
</body>
</html>