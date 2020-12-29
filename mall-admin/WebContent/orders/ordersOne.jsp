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
<title>ordersOne</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
	request.setCharacterEncoding("UTF-8");
	
	int ordersId = Integer.parseInt(request.getParameter("ordersId"));
	OrdersDao ordersDao = new OrdersDao();
	OrdersAndProduct oap = ordersDao.selectOrdersAndProductOne(ordersId);
%>
<div>
<div><br>
	<h1>상품 상세보기</h1>
</div>
<div>
	<table class="table table-bordered table-hover">
		<tr>
			<th style="width:200px">orders_id</th>
			<td><%=oap.orders.ordersId %></td>
		</tr>
		<tr>
			<th>orders_date</th>
			<td><%=oap.orders.ordersDate %></td>
		</tr>
		<tr>
			<th>product_name</th>
			<td><%=oap.product.productName %></td>
		</tr>
		<tr>
			<th>orders_amount</th>
			<td><%=oap.orders.ordersAmount %></td>
		</tr>
		<tr>
			<th>orders_price</th>
			<td><%=oap.orders.ordersPrice %></td>
		</tr>
		<tr>
			<th>member_email</th>
			<td><%=oap.orders.memberEmail %></td>
		</tr>
		<tr>
			<th>orders_addr</th>
			<td><%=oap.orders.ordersAddr %></td>
		</tr>
		<tr>
			<th>orders_state</th>
			<td><%=oap.orders.ordersState %></td>
		</tr>
	</table>
	<hr>
	<div class="row">
		<div class="col-3">
			<a class="btn btn-outline-dark btn-block" href="/mall-admin/orders/editOrdersState.jsp?ordersId=<%=oap.orders.ordersId%>">order_state 수정</a>
		</div>
		<div class="col-3">
			<button class="btn btn-outline-dark btn-block" type="reset" onclick="location.href='/mall-admin/product/productList.jsp'">취소</button>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>