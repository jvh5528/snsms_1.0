<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script>
	
	$().ready(function(){
	
		getTotalPrice();
		
		$("[name='cartCd']").change(function(){
			getTotalPrice();
		});
		
	});
	
	
	function getTotalPrice () {
		var totalPrice = 0;
		$("[name='cartCd']:checked").each(function(){
			var tempCartCd = $(this).val();
			totalPrice += Number($("#price" + tempCartCd).val()) * Number($("#cartGoodsQty" + tempCartCd).val());
		});
		totalPrice = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + " KRW";
		$("#totalPrice").html(totalPrice);
	}
	
	
	function removeCart() {
			
		var cartCdList = "";
		if (confirm("Are you sure you want to delete this item?")) {
			
			$("input[name='cartCd']:checked").each(function(){
				cartCdList += $(this).val() + ",";
			});
			location.href = "${contextPath}/profile/removeCart?cartCdList=" + cartCdList;
		}
		
	}
	
	
	function modifyCartGoodsQty(cartCd){
		$.ajax({
			type : "get",
			url : "${contextPath}/profile/modifyCartGoodsQty",
			data : {
				"cartCd"   : cartCd,
				"cartGoodsQty" : $("#cartGoodsQty" + cartCd).val()
			},
			success:function(){
				getTotalPrice();
			}
		});
		
	}
	
	
	function processOrderCart() {
	
		var mediaCdList = "";
		var cartGoodsQtyList = "";
		var cartCdList = ""
		
		$("[name='cartCd']:checked").each(function(){
			
			var cartCd = $(this).val();
			var mediaCd =  $("#mediaCd" + cartCd).val();
			var cartGoodsQty = $("#cartGoodsQty" + cartCd).val();
			
			mediaCdList += mediaCd + ",";
			cartGoodsQtyList += cartGoodsQty +",";
			cartCdList += cartCd + ",";
			
		});
		
		if (mediaCdList == "") {
			alert("Failed to find related order.");
			return false;
		}
		
		var url = "${contextPath}/order/orderCartGoods";
		    url += "?mediaCdList=" + mediaCdList;
		    url += "&cartGoodsQtyList=" + cartGoodsQtyList;
		    url += "&cartCdList=" + cartCdList;
		
		location.href = url;
		
		
	}
	
	
	function selectAllCart() {
		if ($("#changeAllChoice").prop("checked")) {
			$("[name='cartCd']").prop("checked" , true);
		}
		else {
			$("[name='cartCd']").prop("checked" , false);
		}
		getTotalPrice();
	}	
	

</script>
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
                        <a href="${contextPath }/profile/myProfile?username=${myCart.username}"><i class="fa fa-home"></i> ${sessionScope.username }</a>
                        <span>Your cart</span>
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
                    	<h6><input type="checkbox" id="changeAllChoice" onchange="selectAllCart()"> &nbsp;Select all</h6>
                    	<br>
                        <table>
                            <thead>
                                <tr>
                                    <th width="3%"></th>
                                    <th></th>
                                    <th width="13%">Price</th>
                                    <th width="13%">Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:choose>
                            		<c:when test="${empty myCartList}">
                            			<tr align="center">
                            				<td colspan="5"><h5>Your cart is empty.</h5></td>
                            			</tr>
                            		</c:when>
                            		<c:otherwise>
		                            	<c:forEach var="myCart" items="${myCartList }">
		                            		 <tr>
		                            		 	<td><input type="checkbox" name="cartCd" value="${myCart.cartCd }" checked></td>
                                    			<td class="cart__product__item">
			                                        <div class="cart__product__item__title">
			                                        	<a href="${contextPath }/post/postDetailView?mediaCd=${myCart.mediaCd}"><img src="${contextPath }/profile/thumbnails?fileName=${myCart.photo }" width="50" height="50"></a>
			                                        	<input type="hidden" id="mediaCd${myCart.cartCd }" value="${myCart.mediaCd }"/>
			                                        </div>
			                                    </td>
			                                    <td class="cart__price">
			                                    	<div class="product__price" >
				                                    	<fmt:formatNumber value="${myCart.price -  myCart.price * (myCart.discountRate / 100)}"/>
														<input type="hidden" id="price${myCart.cartCd }" value="${myCart.price -  myCart.price * (myCart.discountRate / 100)}">	 
																                                    
			                                    	</div>
			                                    </td>
			                                    <td class="cart__quantity">
			                                        <div class="pro-qty" onmouseleave="modifyCartGoodsQty(${myCart.cartCd })">
			                                            <input type="text" id="cartGoodsQty${myCart.cartCd }" value="${myCart.cartGoodsQty }" />
			                                        </div>
			                                    </td>
			                                </tr>
		                            	</c:forEach>
                            		</c:otherwise>
                            	</c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="cart__btn update__btn" align="right">
                        <a href="javascript:removeCart();"><span class="icon_trash"></span>Remove</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <ul>
                            <li>Total <span id="totalPrice"></span></li>
                        </ul>
                        <a href="javascript:processOrderCart()" class="primary-btn"> Checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
</body>
</html>