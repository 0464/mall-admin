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
<title>editProduct</title>
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
	System.out.println(productId+"<-productId"); // 테스트용 코드
	ProductDao productDao = new ProductDao();
	Product product = productDao.selectProductOne(productId);
	CategoryDao categoryDao = new CategoryDao();
	ArrayList<Category> categoryList = categoryDao.selectCategoryList();
%>
<div>
<div><br>
	<h1>상품 수정</h1>
</div>
<div>
	<form method="post" action="/mall-admin/product/editProductAction.jsp">
		<input type="hidden" name="productId" value="<%=product.productId%>">
		<div class="form-group">
			<label for="categoryId">category_id:</label>
			<select id="categoryId" class="form-control" name="categoryId">
				<option value="">카테고리 선택</option>
				<%
					for (Category c : categoryList) {
						if (product.categoryId == c.categoryId) {
				%>
							<option value="<%=c.categoryId %>" selected="selected"><%=c.categoryName %></option>	
				<%
						} else {
				%>
							<option value="<%=c.categoryId %>"><%=c.categoryName %></option>	
				<%
						}
					}
				%>
			</select>
		</div>
		<div class="form-group">
			<label for="productName">product_name:</label>
			<input id="productName" class="form-control" type="text" name="productName" value="<%=product.productName%>">
		</div>
		<div class="form-group">
			<label for="productPrice">product_price:</label>
			<input id="productPrice" class="form-control" type="text" name="productPrice" value="<%=product.productPrice%>">
		</div>
		<div class="form-group">
			<label for="productContent">product_content:</label>
			<textarea id="productContent" class="form-control" rows="5" cols="80" name="productContent"><%=product.productContent %></textarea>
		</div>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-primary" type="submit">상품 수정</button>
			<button class="btn btn-outline-dark" type="reset" onclick="location.href='/mall-admin/product/productList.jsp'">취소</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>