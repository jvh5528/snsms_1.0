<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${sessionScope.username eq null}">
		<script>
			alert("Please log in to continue.");
			location.href = "${contextPath}/member/login";
		</script>
	</c:if>

	<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/profile/myProfile?username=${sessionScope.username}"><i class="fa fa-home"></i> ${sessionScope.username }</a>
                        <span>My Orders</span>
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
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Price</th>
                                    <th>Date ordered</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:choose>
                            		<c:when test="${empty myOrderList}">
                            			<tr align="center">
                            				<td colspan="5"><h5>No order history.</h5></td>
                            			</tr>
                            		</c:when>
                            		<c:otherwise>
		                            	<c:forEach var="myOrder" items="${myOrderList }">
		                            		 <tr>
                                    			<td class="cart__product__item">
			                                       <a href="${contextPath }/profile/myOrderDetail?orderCd=${myOrder.orderCd}&username=${myOrder.username}" style="color:gray; font-size:10px;"><img src="${contextPath }/profile/thumbnails?fileName=${myOrder.photo }" width="50" height="50"> / quantity:&nbsp;${myOrder.orderMediaQty }</a>
			                                        <div class="cart__product__item__title">
			                                        </div>
			                                    </td>
			                                    <td class="cart__price"><fmt:formatNumber value="${myOrder.price * myOrder.orderMediaQty}"/>KRW</td>
			                                    <td class="cart__total"><fmt:formatDate value="${myOrder.payOrderTime }" pattern="yyyy-MM-dd"/> </td>
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
			                <a href="${contextPath}/profile/myOrderList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
			                        class="fa fa-angle-left"></i></a>
			            </c:if>
			            <c:forEach var="i" begin="${startPage}" end="${endPage}">
			                <a href="${contextPath}/profile/myOrderList?currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
			            </c:forEach>
			            <c:if test="${endPage != allPageCnt && endPage >= 10}">
			                <a href="${contextPath}/profile/myOrderList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
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