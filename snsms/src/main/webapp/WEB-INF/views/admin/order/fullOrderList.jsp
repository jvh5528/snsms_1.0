<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<html>
<head>
    <meta charset="utf-8">
    <script>
        function generateOrderExcelExport() {
            location.href = "${contextPath}/admin/order/fullOrderExcelExport";
        }
        
        function toggleAllOrdersSection() {
            var allOrdersSection = document.getElementById("allOrdersSection");
            var mostOrderedSection = document.getElementById("mostOrderedSection");
            var orderAmountSection = document.getElementById("orderAmountSection");

            allOrdersSection.style.display = "block";
            mostOrderedSection.style.display = "none";
            orderAmountSection.style.display = "none";

            document.getElementById("allOrdersPagination").style.display = "block";
            document.getElementById("mostOrderedPagination").style.display = "none";
            document.getElementById("orderAmountPagination").style.display = "none";
        }
        
        function toggleMostOrderedSection() {
            var allOrdersSection = document.getElementById("allOrdersSection");
            var mostOrderedSection = document.getElementById("mostOrderedSection");
            var orderAmountSection = document.getElementById("orderAmountSection");

            allOrdersSection.style.display = "none";
            mostOrderedSection.style.display = "block";
            orderAmountSection.style.display = "none";

            document.getElementById("allOrdersPagination").style.display = "none";
            document.getElementById("mostOrderedPagination").style.display = "block";
            document.getElementById("orderAmountPagination").style.display = "none";
        }
        
        function toggleOrderAmountSection() {
            var allOrdersSection = document.getElementById("allOrdersSection");
            var mostOrderedSection = document.getElementById("mostOrderedSection");
            var orderAmountSection = document.getElementById("orderAmountSection");

            allOrdersSection.style.display = "none";
            mostOrderedSection.style.display = "none";
            orderAmountSection.style.display = "block";

            document.getElementById("allOrdersPagination").style.display = "none";
            document.getElementById("mostOrderedPagination").style.display = "none";
            document.getElementById("orderAmountPagination").style.display = "block";
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
                        <a href="${contextPath}/admin/adminHome"><i class="fa fa-home"></i> Admin</a>
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
                <div class="shop__sidebar">
                    <div class="sidebar__categories">
                        <div class="cart__btn update__btn" align="right">
                            <a href="javascript:generateOrderExcelExport();"><span class="icon_folder_download"></span>Excel</a>
                        </div>
                        <div class="categories__accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card">
                                    <div class="card-heading active">
                                        <a data-toggle="collapse" data-target="#collapseOne">Order by</a>
                                    </div>
                                    <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <ul>
                                                <li><a href="javascript:toggleAllOrdersSection();">All Orders</a></li>
                                                <li><a href="javascript:toggleMostOrderedSection();">Most ordered</a></li>
                                                <li><a href="javascript:toggleOrderAmountSection();">Order amount</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="shop__cart__table">
                            <div id="allOrdersSection" style="display: none;">
                                <br><h2 style="font-size:10px"><strong>All orders</strong></h2>
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
                                                <c:forEach var="order" items="${orderList}">
                                                    <tr>
                                                        <td class="cart__product__item">
                                                            <img src="${contextPath}/profile/thumbnails?fileName=${order.photo}" width="70" height="70">
                                                            <div class="cart__product__item__title">
                                                                <h6>
                                                                    <a href="${contextPath}/profile/myOrderDetail?orderCd=${order.orderCd}&username=${order.username}">
                                                                        Price : <fmt:formatNumber value="${order.price}"/> KRW / Quantity : ${order.orderMediaQty}
                                                                    </a>
                                                                </h6>
                                                            </div>
                                                        </td>
                                                        <td><h6><strong>${order.username}</strong></h6></td>
                                                        <td><h6><fmt:formatDate value="${order.payOrderTime}" pattern="yyyy-MM-dd"/></h6></td>
                                                        <td><h6>${order.deliveryStatus}</h6></td>
                                                    </tr>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        <div class="shop__cart__table">
                            <div id="mostOrderedSection" style="display: none;">
                                <h2 style="font-size:10px"><strong>Most ordered</strong></h2>
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
                                        <c:forEach var="mostOrder" items="${mostOrdered}">
                                            <tr>
                                                <td class="cart__product__item">
                                                    <img src="${contextPath}/profile/thumbnails?fileName=${mostOrder.photo}" width="70" height="70">
                                                    <a href="${contextPath}/profile/myOrderDetail?orderCd=${mostOrder.orderCd}&username=${mostOrder.username}">
                                                        Price : <fmt:formatNumber value="${mostOrder.price}"/> KRW / Quantity : ${mostOrder.orderMediaQty}
                                                    </a>
                                                </td>
                                                <td><h6><strong>${mostOrder.username}</strong></h6></td>
                                                <td><h6><fmt:formatDate value="${mostOrder.payOrderTime}" pattern="yyyy-MM-dd"/></h6></td>
                                                <td><h6>${mostOrder.deliveryStatus}</h6></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        <div class="shop__cart__table">
                        <div id="orderAmountSection" style="display: none;">
                            <h2 style="font-size:10px"><strong>Order amount</strong></h2>
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
                                    <c:forEach var="orderAmount" items="${orderAmount}">
                                        <tr>
                                            <td class="cart__product__item">
                                                <img src="${contextPath}/profile/thumbnails?fileName=${orderAmount.photo}" width="70" height="70">
                                                <a href="${contextPath}/profile/myOrderDetail?orderCd=${orderAmount.orderCd}&username=${orderAmount.username}">
                                                    Price : <fmt:formatNumber value="${orderAmount.price}"/> KRW / Quantity : ${orderAmount.orderMediaQty}
                                                </a>
                                            </td>
                                            <td><h6><strong>${orderAmount.username}</strong></h6></td>
                                            <td><h6><fmt:formatDate value="${orderAmount.payOrderTime}" pattern="yyyy-MM-dd"/></h6></td>
                                            <td><h6>${orderAmount.deliveryStatus}</h6></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
</body>
</html>
