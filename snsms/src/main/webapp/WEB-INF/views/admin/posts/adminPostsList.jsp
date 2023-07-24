<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script>
	
	function generatePostsExcelExport() {
		location.href = "${contextPath}/admin/posts/postsExcelExport";
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
                        <a href="${contextPath }/admin/adminHome"><i class="fa fa-home"></i>Admin</a>
                        <span>User Posts List</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">	
                <div class="col-lg-12">
	            	<div class="cart__btn update__btn" align="right">
						<a href="javascript:generatePostsExcelExport();"><span class="icon_folder_download"></span>Excel</a>
					</div>
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th width="10%">Post No.</th>
                                    <th width="75%"></th>
                                    <th width="10%">Date Posted</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:choose>
                            		<c:when test="${empty mediaList}">
                            			<tr>
		                                    <td colspan="4" align="center"><h6>You'll you a list of content posted by users here.</h6></td>
		                                </tr>	
                            		</c:when>
                            		<c:otherwise>
		                            	<c:forEach var="postDTO" items="${mediaList }" varStatus="i">
		                            		 <tr>
		                            		 	<td class="cart__product__item" align="center">
		                            		 		<h6>${postDTO.mediaCd }</h6>
			                                    </td>
                                    			<td class="cart__product__item">
			                                        <img src="${contextPath }/profile/thumbnails?fileName=${postDTO.photo }" width="100" height="100">
			                                        <div class="cart__product__item__title">
			                                            <h6><a href="${contextPath }/post/postDetailView?mediaCd=${postDTO.mediaCd}">${postDTO.username }</a></h6>
			                                        	<p><fmt:formatNumber value="${postDTO.price }"/>KRW</p>
			                                        </div>
			                                    </td>
			                                    <td class="cart__total"><fmt:formatDate value="${postDTO.uploadDt }" pattern="yyyy-MM-dd"/> </td>
			                                </tr>
		                            	</c:forEach>
                            		</c:otherwise>
                            	</c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-12 text-center">
		        <div class="pagination__option">
		            <c:if test="${startPage > 10}">
		                <a href="${contextPath}/admin/posts/adminPostsList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
		                        class="fa fa-angle-left"></i></a>
		            </c:if>
		            <c:forEach var="i" begin="${startPage}" end="${endPage}">
		                <a href="${contextPath}/admin/posts/adminPostsList?currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
		            </c:forEach>
		            <c:if test="${endPage != allPageCnt && endPage >= 10}">
		                <a href="${contextPath}/admin/posts/adminPostsList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
		                        class="fa fa-angle-right"></i></a>
		            </c:if>
		        </div>
    		</div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
</body>
</html>