<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="sessionId" value="${sessionScope.username}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Blog Section Begin -->
<section class="blog spad">
    <div class="container">
        <div class="row">
            <c:forEach var="postDTO" items="${mediaList}">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic large__item set-bg"
                            data-setbg="${contextPath}/profile/thumbnails?fileName=${postDTO.photo}"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-lg-12 text-center">
        <div class="pagination__option">
            <c:if test="${startPage > 10}">
                <a href="${contextPath}/post/getHomeMediaList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt}"><i
                        class="fa fa-angle-left"></i></a>
            </c:if>
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                <a href="${contextPath}/post/getHomeMediaList?currentPageNumber=${i}&onePageViewCnt=${onePageViewCnt}">${i}</a>
            </c:forEach>
            <c:if test="${endPage != allPageCnt && endPage >= 10}">
                <a href="${contextPath}/post/getHomeMediaList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt}"><i
                        class="fa fa-angle-right"></i></a>
            </c:if>
        </div>
    </div>
</section>
<!-- Blog Section End -->
</body>
</html>
