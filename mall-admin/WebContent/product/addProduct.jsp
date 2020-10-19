<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>
<%@ page import="dao.*"%>
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
<title>addProduct</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		<%
			CategoryDao categoryDao = new CategoryDao();
		ArrayList<Category> categoryList = categoryDao.selectCategoryList();
		%>
		<div>
			<div>
				<h1>상품 추가</h1>
			</div>
			<div>
				<form method="post"
					action="/mall-admin/product/addProductAction.jsp">
					<div class="form-group">
						<label for="categoryId">category_id:</label> <select
							id="categoryId" class="form-control" name="categoryId">
							<option value="">카테고리 선택</option>
							<%
								for (Category c : categoryList) {
							%>
							<option value="<%=c.categoryId%>"><%=c.categoryName%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<label for="productName">product_name:</label> <input
							id="productName" class="form-control" type="text"
							name="productName">
					</div>
					<div class="form-group">
						<label for="productPrice">product_price:</label> <input
							id="productPrice" class="form-control" type="text"
							name="productPrice">
					</div>
					<div class="form-group">
						<label for="productContent">product_content:</label>
						<textarea id="productContent" class="form-control" rows="5"
							cols="80" name="productContent"></textarea>
					</div>
					<div class="form-group">
						<div>
							<label>product_soldout:</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="productSoldout" value="N"
								checked="checked">판매중
							</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="productSoldout" value="Y">품절
							</label>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<button class="btn btn-outline-primary btn-block" type="submit">상품
							추가</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>