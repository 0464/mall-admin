<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

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
<title>editCategory</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
			console.log("ready"); // 디버깅
			if($("#categoryName").val() == "") { // form validation checking
				alert("카테고리 확인");
				return;
			}
			$("#editcategoryForm").submit();
		});
	});
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<!-- 메뉴 시작 -->
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
	request.setCharacterEncoding("UTF-8");
	
	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	
	CategoryDao categoryDao = new CategoryDao();
	Category category = categoryDao.selectCategoryOne(categoryId);
%>
<div>
<div><br>
	<h1>카테고리 수정</h1>
</div>
<div style="width:500px">
	<form method="post" action="/mall-admin/category/editCategoryAction.jsp" id="editcategoryForm">
		<input type="hidden" name="categoryId" value="<%=category.categoryId %>">
		
		<div class="form-group">
			<label for="categoryName">category_name</label>
			<input id="categoryName" class="form-control" type="text" name="categoryName" value="<%=category.categoryName%>">
		</div>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-primary" type="button">카테고리 수정</button>
			<button class="btn btn-outline-danger" type="reset" onclick="location.href='/mall-admin/category/categoryList.jsp'">취소</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>