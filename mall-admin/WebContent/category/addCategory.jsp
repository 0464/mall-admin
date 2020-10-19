<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	if (session.getAttribute("loginAdminId") == null) {
		System.out.println("로그인 필요");
		
		response.sendRedirect("/mall-admin/login.jsp?loginRequired=true");
		
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCategory</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
			if($("#categoryName").val() == "") { // form validation checking
				$("#categoryNameHelper").text("카테고리 이름을 입력하세요.");
				return;
			}
			$("#addcategoryForm").submit();
		});
	});
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<div>
<div>
	<h1>카테고리 추가</h1>
</div>
<div>
	<form method="post" action="/mall-admin/category/addCategoryAction.jsp" id="addcategoryForm">
		<div class="form-group">
			<label for="categoryName">category_name:</label>
			<input id="categoryName" class="form-control" type="text" name="categoryName" id="categoryName">
			<span id="categoryNameHelper"></span>
		</div>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-dark btn-block" type="button" id="btn">카테고리 추가</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>