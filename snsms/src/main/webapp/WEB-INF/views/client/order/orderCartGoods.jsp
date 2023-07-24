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

	$().ready(function() {
		
		var orderMediaQtyList = "${orderMediaQtyList}".split(",");
		
		var viewPaymentAmt = 0;
		var paymentAmtList = 0;
		var totalDeliveryPrice = 0;
		for (var i = 0; i < orderMediaQtyList.length - 1; i++) {
			
			$("#qty"+i).text(orderMediaQtyList[i]);
			$("#orderMediaQty"+i).val(orderMediaQtyList[i] );
			
			var price = Number($("#price"+i).val());
			var orderMediaQty = Number($("#orderMediaQty"+i).val());
			var deliveryPrice =  Number($("#deliveryPrice"+i).val());
			
			viewPaymentAmt += price * orderMediaQty + deliveryPrice;
			
			paymentAmtList += price * orderMediaQty + deliveryPrice;
			paymentAmtList += ",";
		
			totalDeliveryPrice += deliveryPrice;
			
		}
		
		$("#totalDeliveryPrice").html(totalDeliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + " KRW");
		$("#viewPaymentAmt").html(viewPaymentAmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + " KRW");
		$("[name='paymentAmtList']").val(paymentAmtList);
		
		
	});
	
	function setPayMethod(){
		
		var method = $("[name='payMethod']").val();
		if (method == 'card') {
			$("#cardPayMonth,#cardCompanyNm").show();
			$("#payOrdererHp").hide();
		}
		else {
			$("#cardPayMonth,#cardCompanyNm").hide();
			$("#payOrdererHp").show();
		}
		
	}
	
</script>
</head>
<body>
<!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <form action="${contextPath }/order/orderCartGoods" method="post" class="checkout__form">
                <div class="row">
               	  <div class="col-lg-12">
                        <div class="checkout__order">
                            <h5>Your order</h5>
                            <div class="checkout__order__product">
                                <ul>
                                    <li>
                                        <span class="top__text">Product</span>
                                        <span class="top__text__right">Qty</span>
                                        <span class="top__text__right">Price&emsp;</span>
                                    </li>
                                    <c:forEach var="mediaDTO" items="${mediaList }" varStatus="i">
                                     <li>
                                     	${i.index + 1}.&nbsp; <img src="${contextPath }/profile/thumbnails?fileName=${mediaDTO.photo }" width="50" height="50"> 
                                      <span id="qty${i.index }"></span>
                                      <span><fmt:formatNumber value="${mediaDTO.price}"/>KRW &emsp;</span>
                                      <input type="hidden" id="price${i.index }" value="${mediaDTO.price}" />
                                      <input type="hidden" id="orderMediaQty${i.index }"/>
                                      <input type="hidden" id="deliveryPrice${i.index }" value="${mediaDTO.deliveryPrice}" />
                                     </li>
                                    </c:forEach>
                                    <br>
		                            <li>DeliveryPrice <span id="totalDeliveryPrice"></span></li>
                                </ul>
                                <input type="hidden" name="mediaCdList" value="${mediaCdList }">
              					<input type="hidden" name="orderMediaQtyList" value="${orderMediaQtyList}">		
              					<input type="hidden" name="cartCdList" value="${cartCdList}">		
              					<input type="hidden" name="username" value="${orderer.username}">
              					<input type="hidden" name="paymentAmtList">
                            </div>
                            <div class="checkout__order__total">
                                <ul>
                                    <li>Total <span id="viewPaymentAmt"></span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                	</div>
                	<br><br><br>
                    <div class="col-lg-12">
                        <br><br><br>
                        <h5>Checkout</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Name <span>*</span></p>
                                    <input type="text" name="ordererNm" value="${orderer.name }">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Phone <span>*</span></p>
                                    <input type="text" name="ordererHp" placeholder="Phone number">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Recipient <span>*</span></p>
                                    <input type="text" name="receiverNm" value="${orderer.name }">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Recipient contact information <span>*</span></p>
                                    <input type="text" name="receiverHp" placeholder="Recipient phone number">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__order__widget">
                                    <p>Is this a gift? <span>*</span></p>
										<input type="radio" id="giftWrapping" name="giftWrapping" value="y"> Yes &emsp;
										<input type="radio" id="giftWrapping" name="giftWrapping" checked value="n"> No
									<p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__order__widget">
                                     <p>Shipping method <span>*</span></p>
                                     <input type="radio" name="deliveryMethod" value="standard" checked> Standard  &emsp; 
									 <input type="radio" name="deliveryMethod" value="express"> Express  &emsp;
									 <input type="radio" name="deliveryMethod" value="international"> International shipping &emsp;
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__order__widget">
                                    <p>Method of payment <span>*</span></p>
                                    <select name="payMethod" onchange="setPayMethod()">
                                    	<option value="card">Credit card</option>
                                    	<option value="phone">Mobile payment</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12">
                            </div>
                            <div id="cardCompanyNm" class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__order__widget">
                                    <p>Credit card issuer <span>*</span></p>
	                                <select name="cardCompanyNm">
										<option value="삼성">삼성</option>
										<option value="하나SK">하나SK</option>
										<option value="현대">현대</option>
										<option value="KB">KB</option>
										<option value="신한">신한</option>
										<option value="롯데">롯데</option>
										<option value="BC">BC</option>
										<option value="시티">시티</option>
										<option value="NH농협">NH농협</option>
								   </select>
                                </div>
                            </div>
                            <div id="cardPayMonth" class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__order__widget">
                                    <p>Installment plan <span>*</span></p>
                                    <select name="cardPayMonth">
										<option value="0">Lump sum</option>                                    
										<option value="1">1 month</option>                                    
										<option value="2">2 months</option>                                    
										<option value="3">3 months</option>                                    
										<option value="4">4 months</option>                                    
										<option value="5">5 months</option>                                    
										<option value="6">6 months</option>                                    
                                    </select>
                                </div>
                            </div>
                            <div id="payOrdererHp" class="col-lg-6 col-md-6 col-sm-6" style="display: none">
                                <div class="checkout__form__input">
                                    <p>Phone number <span>*</span></p>
                                    <input type="text" name="payOrdererHp" placeholder="Phone number">
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12">
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Delivery notes <span>*</span></p>
                                    <input type="text" name="deliveryMessage" placeholder="Delivery notes">
                                </div>
                            </div>
                            <br><br>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Postal code <span>*</span></p>
                                    <input type="text" id="zipcode" name="zipcode" style="width: 20%;" placeholder="Postal code">
                                    <input type="button" value="Lookup address" onclick="execDaumPostcode();" style="width: 15%; padding-left: 0">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Address <span>*</span></p>
                                    <input type="text" id="roadAddress" name="roadAddress" placeholder="Street address">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Address line 2 <span>*</span></p>
                                    <input type="text" id="jibunAddress" name="jibunAddress" placeholder="Address line 2">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Apartment, suite, etc.<span>*</span></p>
                                    <input type="text" id="namujiAddress" name="namujiAddress" placeholder="Apt, suite, etc.">
                                </div>
                            </div>
                        </div>
	                     <div align="right">
	                        <button type="submit" class="site-btn">Place order</button>
	                    </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->
</body>
</html>