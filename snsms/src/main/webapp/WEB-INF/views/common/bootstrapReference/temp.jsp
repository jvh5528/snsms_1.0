<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<html>
<head>
<meta  charset="utf-8">
<script>
	function generateOrderExcelExport() {
		location.href = "${contextPath}/admin/order/fullOrderExcelExport";
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
                        <a href="${contextPath }/admin/adminHome"><i class="fa fa-home"></i> Admin</a>
                        <span>Order List</span>
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
						<a href="javascript:generateOrderExcelExport();"><span class="icon_folder_download"></span>Excel</a>
					</div>
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th width="70%">Order detail</th>
                                    <th width="10%">Order placed by</th>
                                    <th width="10%">Date ordered</th>
                                    <th width="10%">Delivery status</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:choose>
                            		<c:when test="${empty orderList}">
                            			<tr>
                            				<td colspan="4" align="center"><h6>You'll see all orders here.</h6></td>
                            			</tr>
                            		</c:when>
                            		<c:otherwise>
		                            	<c:forEach var="order" items="${orderList }">
		                            		 <tr>
                                    			<td class="cart__product__item">
			                                        <img src="${contextPath }/profile/thumbnails?fileName=${order.photo }" width="70" height="70">
			                                        <div class="cart__product__item__title">
		                                            	<h6>
		                                            		<a href="${contextPath }/profile/myOrderDetail?orderCd=${order.orderCd}&username=${order.username}">
		                                            		Price : <fmt:formatNumber value="${order.price }"/> KRW / Quantity : ${order.orderMediaQty }
		                                        			</a>
		                                        		</h6>
			                                        </div>
			                                    </td>
			                                    <td><h6><strong>${order.username }</strong></h6></td>
			                                    <td><h6><fmt:formatDate value="${order.payOrderTime }" pattern="yyyy-MM-dd"/></h6> </td>
			                                    <td><h6>${order.deliveryStatus }</h6></td>
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
			                <a href="${contextPath}/admin/order/fullOrderList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
			                        class="fa fa-angle-left"></i></a>
			            </c:if>
			            <c:forEach var="i" begin="${startPage}" end="${endPage}">
			                <a href="${contextPath}/admin/order/fullOrderList?currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
			            </c:forEach>
			            <c:if test="${endPage != allPageCnt && endPage >= 10}">
			                <a href="${contextPath}/admin/order/fullOrderList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
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