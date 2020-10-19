<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
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
<title>deleteProduct</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
	request.setCharacterEncoding("UTF-8");
	
	int productId = Integer.parseInt(request.getParameter("productId"));
	
	ProductDao productDao = new ProductDao();
	Product product = productDao.selectProductOne(productId);
%>
<div>
<div>
	<h1>상품 삭제</h1>
</div>
<div>
	<form method="post" action="/mall-admin/product/deleteProductAction.jsp">
		<input type="hidden" name="categoryId" value="<%=product.productId %>">
		
		<p>정말로 <%=product.productName %>을(를) 삭제하시겠습니까?</p>
		<p>삭제된 내용은 복구할 수 없습니다!</p>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-primary btn-block" type="submit">상품 삭제</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>