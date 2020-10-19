<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	if (session.getAttribute("loginAdminId") == null) {
		System.out.println("로그인 필요");
		
		response.sendRedirect("/mall-admin/login.jsp?loginRequired=true");
		
		return;
	}
	
	request.setCharacterEncoding("UTF-8");
	
	Orders orders = new Orders();
	orders.ordersId = Integer.parseInt(request.getParameter("ordersId"));
	orders.ordersState = request.getParameter("ordersState");
	
	OrdersDao ordersDao = new OrdersDao();
	ordersDao.updateOrdersState(orders);
	
	response.sendRedirect("/mall-admin/orders/ordersList.jsp");
%>