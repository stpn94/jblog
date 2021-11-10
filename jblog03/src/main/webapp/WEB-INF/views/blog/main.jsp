<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<c:choose>
					<c:when test="${title == null }">
						<div class="blog-content">
							<h4>비어있음.</h4>
							<p>새로운 포스트를 작성해주세요.</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="blog-content">
							<h4>${title}</h4>
							<p>${contents}</p>
						</div>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${title == null }">
						<ul class="blog-list">
						
						</ul>
					</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${postList}" begin="0">
							<ul class="blog-list">
								<li><a href="${pageContext.request.contextPath}/${vo.blogId}/${vo.categoryNo}/${vo.no}">${vo.title} </a> <span>${vo.regDate}</span></li>
							</ul>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="vo" items="${categoryList}" begin="0">
					<li><a href="${pageContext.request.contextPath}/${vo.id}/${vo.no}">${vo.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<jsp:include page="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
</html>