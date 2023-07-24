<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function updatePreview(input, target) {
	    let file = input.files[0];
	    let reader = new FileReader();
	
	    reader.readAsDataURL(file);
	    reader.onload = function () {
	        let img = document.getElementById(target);
	        img.src = reader.result;
	    }
	}
	
	
	$().ready(function(){
		
		var productOptionSelected = false;
		
		$("#sellYn").change(function(){
			
			var message = "";
			var productOptions = $("[name='sellYn']:checked").val();
			
			if (productOptions == "Y"){
				message = "<input type='text' id='price' name='price' placeholder='Price' required><span style='color:red' id='pricemsg'>*</span><br><input type='text' id='deliveryPrice' name='deliveryPrice' placeholder='Delivery price' required><span style='color:red' id='deliverypricemsg'>*</span><br><input type='text' id='stock' name='stock' placeholder='Stock quantity' required><span style='color:red' id='qtymsg'>*</span>"
				productOptionSelected = true;	
			}
			else{
				productOptionSelected = false;
			}
			$("#productOptions").html(message);
		});
		
	});
	
	
</script>
</head>
<body>
 <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> ${memberDTO.username }</a>
                        <span>New post</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

<!-- Product Details Section Begin -->
    <section class="product-details spad">
    	<form action="${contextPath }/post/newPost" enctype="multipart/form-data" method="post">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-6">
	                    <div class="product__details__pic">
	                        <div class="product__details__slider__content">
	                            <div class="product__details__pic__slider owl-carousel">
	                                <img data-hash="product-1" class="product__big__img" id="image-preview" src="${contextPath }/resources/img/image-default.webp">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-6">
	                    <div class="product__details__text" align="right">
	                        	<div class="cart__product__item">
	                         	 	<label><a style="color:#00A2FF;">Choose file<input type="file" id="photo" name="photo" onchange="updatePreview(this, 'image-preview')" style="display:none;" required><span class="icon_folder_upload"></span></a></label> 
	                        	</div>
	                        	<div class="contact__form">
	                   				<label><textarea rows="10" cols="50" id="caption" name="caption" placeholder="Write a caption..."></textarea></label><br>
	                   				<script>
								    $("#caption").keyup(function(){
								        el = $(this);
								        if(el.val().length >= 500){
								            el.val( el.val().substr(0, 500) );
								        } else {
								            $("#wordCnt").text(500-el.val().length);
								        }
								    });
								    </script>
                                    <span id="wordCnt" style="color: gray; font-size: 10px;">500 </span><br>
	                        	</div>
	                        	<div class="product__details__widget">
		                    		<div class="stock__checkbox" >
		                               <label for="stockin">
		                                   Is this a product?
		                                   <input type="checkbox" id="sellYn" name="sellYn" value="Y"><br>
		                                   <span class="checkmark" ></span>
		                                   <span id="productOptions"></span>
		                               </label>
		                            </div>
	                    		</div>
	                        <div class="product__details__button">
	                            <div align="right">
	                               <input type="hidden" id="username" name="username" value="${memberDTO.username }">
						           <button type="submit" class="site-btn">Share</button>
	                    		</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
        </form>
    </section>
    <!-- Product Details Section End -->
</body>
</html>