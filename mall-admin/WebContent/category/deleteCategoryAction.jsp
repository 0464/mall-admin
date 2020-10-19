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
	
	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	
	System.out.println(categoryId+"<-categoryId");
	
	CategoryDao categoryDao = new CategoryDao();
	categoryDao.deleteCategory(categoryId);
	
	response.sendRedirect("/mall-admin/category/categoryList.jsp");
%>