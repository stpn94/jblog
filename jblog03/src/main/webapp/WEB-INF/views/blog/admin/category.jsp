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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	
	var url = "${pageContext.request.contextPath}/assets/images/delete.jpg";
	
	var render = function(i , vo, mode){
		var html = "<tr data-no= "+vo.no+">" + 
		"<td>"  + i +  "</td>" + 
		"<td>" + vo.name + "</td>" + 
		"<td>" + vo.count + "</td>" + 
		"<td>" + vo.desc+ "</td>" + 
		"<td>" + "<a href='' data-no=" +vo.no+ "><img src=" +url+ "></a>" + "</td>" + 
		"</tr>";
		
		$(".admin-cat tr:last").after(html);
	}
	var  i = 0;
	var fetch = function(){
	
		$.ajax({
			url: "${pageContext.request.contextPath}/${authUser.id }/api/admin/category",
			dataType: "json",
			type: "get",
			success: function(response){
				// response.data.forEach(function(e){
				//	render(e, true);
				// });
				
				console.log("success 호출!");
				 response.data.forEach(function(e){
					i++;
					render(i , e, true);
				 });
		
			}
		});	
	}
	
	$(document).on("click", ".admin-cat td a", function(event){
			event.preventDefault();
			let no = $(this).data("no");
			console.log(no);
			deleteCategory(no);
		});
	
	deleteCategory = function(no){ 
	$.ajax({
		url: "${pageContext.request.contextPath }/${authUser.id }/api/admin/delete/"+no,
		dataType: "json",
		type: "delete",

		success: function(response){
			if(response.result != "success"){
				// 비밀번호가 틀린경우.
				alert("비밀번호가 틀렸습니다.");
				return;
			}						
			
			$(".admin-cat tr[data-no=" + response.data + "]").remove();
		}
	});	
	}
	
	
	$(function(){
		fetch();
		$("#add-form").submit(function(event){
			event.preventDefault();
			vo = {}
			
			vo.name = $("#input-name").val();
			vo.desc = $("#input-desc").val();
			$.ajax({
				url: "${pageContext.request.contextPath }/${authUser.id }/api/admin/category",
				dataType: "json",
				type: "post",
				contentType: "application/json",   
				data: JSON.stringify(vo),
				success: function(response){
					// response.data.forEach(function(e){
					//	render(e, true);
					// });
					console.log(response.data);
					var html = "<tr data-no= "+vo.no+">" + 
					"<td>"  + (++i) +  "</td>" + 
					"<td>" + vo.name + "</td>" + 
					"<td>" + response.data.count + "</td>" + 
					"<td>" + vo.desc+ "</td>" + 
		    	"<td>" + "<a href='' data-no=" +vo.no+ "><img src=" +url+ "></a>" + "</td>" + 
	        "</tr>";	
	        $(".admin-cat tr:last").after(html);
	      	
					$("#input-name").val("");
					$("#input-desc").val("");
			
				}
			});
		});

	});
	
	</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<jsp:include page="/WEB-INF/views/blog/admin/includes/menu.jsp" />
				<table class="admin-cat" id="list-jblog">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>


				</table>


				<h4 class="n-c">새로운 카테고리 추가</h4>

				<form action="" method="post" id="add-form">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td>
								<input type="text" id="input-name" name="name">
							</td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td>
								<input type="text" id="input-desc" name="desc">
							</td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td>
								<input type="submit" value="카테고리 추가">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
</html>