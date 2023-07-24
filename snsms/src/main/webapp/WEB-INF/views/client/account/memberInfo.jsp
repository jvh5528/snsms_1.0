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
	        img.src = reader.result;
	    }
	}
	
	$().ready(function(){
		
		$("[name='gender']").change(function(){
			
			var message = "";
			var customGender = $("[name='gender']:checked").val();
			
			if (customGender == "c"){
				message = "<input type='text' size='20' id='customGender' name='customGender' placeholder='' value='${profileDTO.customGender}'>"
			}
			$("#customAnswer").html(message);
			
		});
	});
	
</script>
</head>
<body>
    <!-- Edit Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>${memberDTO.username }'s personal information</h5><br>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                	<c:choose>
                                		<c:when test="${profileDTO.profilePic eq null}">
                                			<img style="width: 100px; border-radius:50%;" id="image-preview" src="${contextPath }/resources/img/defaultphoto.png">
                                		</c:when>
                                		<c:otherwise>
                                			<img style="width: 100px; height:100px; border-radius:50%;" id="image-preview" src="${contextPath }/profile/thumbnails?fileName=${profileDTO.profilePic}">
                                		</c:otherwise>
                                	</c:choose>
                                </div>
                            </div>
                            <div class="col-lg-12">
                            	<div class="checkout__form__input">
                            	<br>
                                <p>Username: ${memberDTO.username }</p>
                                </div>
                                <div class="checkout__form__input">
                                	<br>
                                    <p>Name: ${memberDTO.name }</p>
                                </div>
                                <div class="checkout__form__input">
                                	<p>Gender: ${profileDTO.gender }</p>
                                </div>
                            </div>
                            </div><br><br>
                        </div>
                    </div>
            </div>
        </section>
<!-- Edit Profile Section End -->
</body>
</html>