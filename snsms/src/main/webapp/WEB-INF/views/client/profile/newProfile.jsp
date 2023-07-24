<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>

	function updatePreview(input, target) {
	    let file = input.files[0];
	    let reader = new FileReader();
	
	    reader.readAsDataURL(file);
	    reader.onload = function () {
	        let img = document.getElementById(target);
	        // can also use "this.result"
	        img.src = reader.result;
	    }
	}
	
	$().ready(function(){
		
		$("[name='gender']").change(function(){
			
			var message = "";
			var customGender = $("[name='gender']:checked").val();
			
			if (customGender == "c"){
				message = "<input type='text' size='20' id='customGender' name='customGender' placeholder='custom'>"
			}
			$("#customAnswer").html(message);
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
                        <a href="${contextPath }/profile/myProfile"><i class="fa fa-home"></i> ${memberDTO.username }</a>
                        <span>Create profile</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Edit Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <form action="${contextPath }/profile/newProfile" enctype="multipart/form-data"  method="post" class="checkout__form">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Profile detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                	<img style="width: 100px; height: 100px; border-radius:50%;" id="image-preview" src="${contextPath }/resources/img/defaultphoto.png">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                            	<div class="checkout__form__input">
                            	<br>
                                <label><span><strong>${memberDTO.username }</strong><input type="text" id="username" name="username" value="${memberDTO.username }" style="display:none;"></span></label><br>
                            	 <label><a style="color:#00A2FF;">Change profile photo<input type="file" id="profilePic" name="profilePic" onchange="updatePreview(this, 'image-preview')" style="display:none;"></a></label> 
                            	 
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                	<br>
                                    <p>Name</p>
                                    <input type="text" id="name" name="name" value="${memberDTO.name }">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Bio</p>
                                    <textarea rows="10" cols="50" id="bio" name="bio" style="border:solid 1px #e1e1e1;"></textarea><br>
								    <script>
								    $("#bio").keyup(function(){
								        el = $(this);
								        if(el.val().length >= 200){
								            el.val( el.val().substr(0, 200) );
								        } else {
								            $("#wordCnt").text(200-el.val().length);
								        }
								    });
								    </script>
                                    <span id="wordCnt" style="color: gray; font-size: 10px;">200 </span><br>
                                </div><br>
                                <div class="checkout__form__input">
                                	<p>Gender</p>
                                </div>
                                <div>
                                    Male			  <input type="radio" name="gender" value="m">&emsp;&emsp;
                                    Female 			  <input type="radio" name="gender" value="f">&emsp;&emsp;
                                    Prefer not to say <input type="radio" name="gender" value="x">&emsp;&emsp;
                                    Custom 			  <input type="radio" name="gender" value="c">&emsp;&emsp;<span id="customAnswer"></span><br>
                                    <span style="color: gray; font-size: 10px;">This won't be part of your public profile.</span><br><br>
                                </div>
                                </div>
                                <input type="hidden" id="userStatus" name="userStatus" value="existing">
                                <button type="submit" class="site-btn">Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
<!-- Edit Profile Section End -->
</body>
</html>