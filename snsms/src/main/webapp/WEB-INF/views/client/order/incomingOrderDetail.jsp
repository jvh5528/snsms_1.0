<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	$().ready(function(){
		
		$("[name='cardCompanyNm']").val("${incomingOrder.cardCompanyNm}");
		$("[name='payMethod']").val("${incomingOrder.payMethod}");
		$("[name='cardPayMonth']").val("${incomingOrder.cardPayMonth}");
		$("[name='deliveryStatus']").val("${incomingOrder.deliveryStatus}");
		
		$("[name='giftWrapping']").each(function(){
			if ($(this).val() == "${incomingOrder.giftWrapping}"){
				$(this).prop("checked", true);
			}
		})
		$("[name='deliveryMethod']").each(function(){
			if ($(this).val() == "${incomingOrder.deliveryMethod}"){
				$(this).prop("checked", true);
			}
		})
	});

</script>
</head>
<body>
<!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
             <form action="#" method="post" class="checkout__form">
                <div class="row">
                	<div class="col-lg-12">
                        <div class="checkout__order">
                            <h5>Incoming order for</h5>
                            <div class="checkout__order__product">
                                <ul>
                                    <li>
                                        <span class="top__text">Product</span>
                                        <span class="top__text__right">Price</span>
                                    </li>
                                    <li>01. <img src="${contextPath }/profile/thumbnails?fileName=${incomingOrder.photo }" width="50" height="50"> / quantity: ${incomingOrder.orderMediaQty } <span><fmt:formatNumber value="${incomingOrder.price + incomingOrder.deliveryPrice}"/> </span></li>
                                    <br>
                                    <li>Price <span><fmt:formatNumber value="${incomingOrder.price}"/>KRW</span></li>
                                    <li>DeliveryPrice <span><fmt:formatNumber value="${incomingOrder.deliveryPrice}"/>KRW</span></li>
                                </ul>
                            </div>
                            <div class="checkout__order__total">
                                <ul>
                                    <li>Total <span><fmt:formatNumber value="${incomingOrder.price + incomingOrder.deliveryPrice}"/>KRW</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
	                	<br><br><br>
                        <h5>Order details</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Customer </p>
                                    <input type="text" name="ordererNm" value="${incomingOrder.ordererNm }" disabled>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Contact </p>
                                    <input type="text" name="myOrderHp" value="${incomingOrder.ordererHp }" disabled>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Recipient </p>
                                    <input type="text" name="receiverNm" value="${incomingOrder.receiverNm }" disabled>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Recipient contact </p>
                                    <input type="text" name="receiverHp" value="${incomingOrder.receiverHp }" disabled>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                               <div class="checkout__form__input">
                                    <p>Gift wrapping </p>
                                    <c:choose>
                                    	<c:when test="${incomingOrder.giftWrapping == 'y'}">
		                                    <input type="text" name="giftWrapping" value="yes" disabled>
                                    	</c:when>
                                    	<c:otherwise>
		                                    <input type="text" name="giftWrapping" value="no" disabled>
                                    	</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Method of delivery </p>
                                    <input type="text" name="deliveryMethod" value="${incomingOrder.deliveryMethod }" disabled>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Payment </p>
                                    <input type="text" name="payMethod" value="${incomingOrder.payMethod }" disabled>
                                </div>
                            </div>
                            <c:choose>
                            	<c:when test="${incomingOrder.payMethod eq 'card'}">
		                            <div class="col-lg-6 col-md-6 col-sm-6">
		                                <div class="checkout__form__input">
		                                    <p>Credit card issuer </p>
		                                    <input type="text" name="cardCompanyNm" value="${incomingOrder.cardCompanyNm }" disabled>
		                                </div>
		                            </div>
		                            <div class="col-lg-6 col-md-6 col-sm-6">
		                            	<div class="checkout__form__input">
		                                    <p>Installment plan </p>
		                                    <c:choose>
		                                    	<c:when test="${incomingOrder.cardPayMonth == 0}">
		                                    		<input type="text" name="cardPayMonth" value="lump" disabled>
		                                    	</c:when>
		                                    	<c:otherwise>
				                                    <input type="text" name="cardPayMonth" value="${incomingOrder.cardPayMonth }month" disabled>
		                                    	</c:otherwise>
		                                    </c:choose>
		                                </div>
		                            </div>
                            	</c:when>
                            	<c:otherwise>
									<div class="col-lg-6 col-md-6 col-sm-6">
		                                <div class="checkout__form__input">
		                                    <p>Buyer contact info </p>
		                                    <input type="text" name="paymyOrderHp" value="${incomingOrder.ordererHp }" disabled>
		                                </div>
		                            </div>                            	
                            	</c:otherwise>
                            </c:choose>
                             <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                	<div class="checkout__order__input">
	                                    <p>Delivery status </p>
	                                    <input type="text" name="deliveryStatus" value="${incomingOrder.deliveryStatus }" disabled>
	                                </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Delivery notes </p>
                                    <input type="text" name="deliveryMessage" disabled>
                                </div>
                            </div>
                            <br><br><br>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Postal code </p>
                                    <input type="text" id="zipcode" name="zipcode" value="${incomingOrder.zipcode }" style="width: 30%;" disabled>
                                    <input type="button" value="Lookup address" onclick="execDaumPostcode();" style="width: 20%; padding-left: 0" disabled>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Street address </p>
                                    <input type="text" id="roadAddress" name="roadAddress" value="${incomingOrder.roadAddress }" disabled>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Address line 2 </p>
                                    <input type="text" id="jibunAddress" name="jibunAddress" value="${incomingOrder.jibunAddress }" disabled>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Apt, suite, etc. </p>
                                    <input type="text" id="namujiAddress" name="namujiAddress" value="${incomingOrder.namujiAddress }" disabled>
                                </div>
                            </div>                         
                        </div>
                        </div>
                    </div>
                </form>
                <div align="right">
	        		<button class="site-btn" onclick="location.href='${contextPath}/profile/myProfile?username=${sessionScope.username }'">Return to profile</button>
	        	</div><br>
            </div>
        </section>
        <!-- Checkout Section End -->
</body>
</html>