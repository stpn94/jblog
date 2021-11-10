<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<div id="container">
	<jsp:include page="/WEB-INF/views/blog/admin/includes/header.jsp" />
	<div id="wrapper">
		<div id="content" class="full-screen">
			<jsp:include page="/WEB-INF/views/blog/admin/includes/menu.jsp" />

			<form:form modelAttribute="postVo" class="write-form" id="write-form" method="post" action="${pageContext.request.contextPath}/${authUser.id}/admin/write">

				<div class="admin-cat-write">


					<div class="t">
						<form:label path="title" />
						<form:input path="title" />
					</div>

					<div>
						<form:errors path="title" />
					</div>

					<div>
						<select name="category">
							<c:forEach var="vo" items="${categoryList}" begin="0">
								<option value="${vo.no}">${vo.name}</option>
							</c:forEach>
						</select>
					</div>

					<div>
						<form:label path="contents" />
						<form:textarea path="contents" cols="50" rows="5" />
					</div>
					
					<div>
						<form:errors path="contents" />
					</div>

					<div>
						<div>&nbsp;</div>
						<div class="s">
							<input type="submit" value="포스트하기">
						</div>
					</div>
				</div>
			</form:form>

		</div>
	</div>
	<jsp:include page="/WEB-INF/views/blog/admin/includes/footer.jsp" />
</div>
</body>

</html>