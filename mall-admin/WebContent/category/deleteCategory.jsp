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
<title>deleteCategory</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
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
<div>
	<h1>카테고리 삭제</h1>
</div>
<div>
	<form method="post" action="/mall-admin/category/deleteCategoryAction.jsp">
		<input type="hidden" name="categoryId" value="<%=category.categoryId %>">
		
		<p>정말로 <%=category.categoryName %>을(를) 삭제하시겠습니까?</p>
		<p>삭제된 내용은 복구할 수 없습니다!</p>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-dark btn-block" type="submit">카테고리 삭제</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>