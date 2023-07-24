<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
	function generateMemberExcelExport() {
		location.href = "${contextPath}/admin/dailyNewMemberExcelExport";
	}
</script>
</head>
<body>
	<body>
	<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/admin/adminHome"><i class="fa fa-home"></i> Admin</a>
                        <span>Member List</span>
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
						<a href="javascript:generateMemberExcelExport();"><span class="icon_folder_download"></span>Excel</a>
					</div>
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th width="10%">No.</th>
                                    <th width="30%">Username</th>
                                    <th width="25%">Name</th>
                                    <th width="5%">Email Address</th>
                                    <th width="15%">Date enrolled</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:choose>
                            		<c:when test="${empty newMember}">
                            			<tr>
                            				<td colspan="6"><h3>No new members.</h3></td>
                            			</tr>
                            		</c:when>
                            		<c:otherwise>
		                            	<c:forEach var="memberDTO" items="${newMember }" varStatus="i">
		                            		 <tr>
		                            		 	<td>
		                            		 		<h6>${i.count }</h6>
			                                    </td>
                                    			<td class="cart__product__item">
			                                        <h6><a href="${contextPath }/member/memberInfo?username=${memberDTO.username}">${memberDTO.username} </a></h6>
			                                    </td>
			                                	<td class="cart__product__item__title"><strong>${memberDTO.name }</strong></td>
			                                    <td>${memberDTO.email }</td>
			                                    <td><fmt:formatDate value="${memberDTO.enrollDt }" pattern="yyyy-MM-dd"/> </td>
			                                </tr>
		                            	</c:forEach>
                            		</c:otherwise>
                            	</c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
</body>
</html>