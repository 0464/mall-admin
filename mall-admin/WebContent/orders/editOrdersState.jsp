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
<title>editOrdersState</title>
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
	Orders orders = ordersDao.selectOrdersOne(ordersId);
%>
<div>
<!-- 제목 시작 -->
<div>
	<h1>주문 상태 수정</h1>
</div>
<div>
	<form method="post" action="/mall-admin/orders/editOrdersStateAction.jsp">
		<input type="hidden" name="ordersId" value="<%=orders.ordersId%>">
		
		<div class="form-group">
			<label for="ordersState">orders_state:</label>
			<select id="ordersState" class="form-control" name="ordersState">
				<%
					ArrayList<String> ordersStateList = new ArrayList<String>();
					ordersStateList.add("결제완료");
					ordersStateList.add("배송준비중");
					ordersStateList.add("배송완료");
					ordersStateList.add("주문취소");
					for (String state : ordersStateList) {
						if (state.equals(orders.ordersState)) {
				%>
							<option value="<%=state%>" selected="selected"><%=state %></option>	
				<%
						} else {
				%>
							<option value="<%=state%>"><%=state %></option>	
				<%
						}
					}
				%>
			</select>
		</div>
		<hr>
		<div class="form-group">
			<button class="page-link btn-block" type="submit">상품 수정</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>