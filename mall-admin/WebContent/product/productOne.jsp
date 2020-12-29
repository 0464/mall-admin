<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>productOne</title>
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
		ProductAndCategory pac = productDao.selectProductAndCategoryOne(productId);
	%>
	<div>
		<div><br>
			<h1>상품 상세보기</h1>
		</div>
		<div>
			<table class="table table-bordered table-hover">
				<tr>
					<th style="width:200px">product_id</th>
					<td><%=pac.product.productId %></td>
				</tr>
				<tr>
					<th>category_name</th>
					<td><%=pac.category.categoryName %></td>
				</tr>
				<tr>
					<th>product_name</th>
					<td><%=pac.product.productName %></td>
				</tr>
				<tr>
					<th>product_price</th>
					<td><%=pac.product.productPrice %></td>
				</tr>
				<tr>
					<th>product_content</th>
					<td><%=pac.product.productContent %></td>
				</tr>
				<tr>
					<th>product_soldout</th>
					<%
						String soldoutText = "판매중";
						if (pac.product.productSoldout.equals("Y")) {
							soldoutText = "품절";
						}
					%>
					<td>
						<div class="row">
						<div class="col-6"><%=soldoutText %></div>
						<div class="col-6" style="text-align:right">
						<a class="btn btn-sm btn-outline-primary" href="/mall-admin/product/editProductSoldoutAction.jsp?productId=<%=pac.product.productId%>&productSoldout=<%=pac.product.productSoldout%>">
						product_soldout 상태 변경
						</a>
						</div>
						</div>
					</td>
				</tr>
			</table><hr>
			<div class="row">
				<div class="col-3">
					<a class="btn btn-outline-warning btn-block" href="/mall-admin/product/editProduct.jsp?productId=<%=pac.product.productId%>">수정</a>
				</div>
				<div class="col-3">
					<a class="btn btn-outline-danger btn-block" href="/mall-admin/product/deleteProduct.jsp?productId=<%=pac.product.productId%>">삭제</a>
				</div>
				<div class="col-3">
				</div>
				<div class="col-3">
					<a class="btn btn-outline-dark btn-block" href="/mall-admin/product/productList.jsp">목록</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>