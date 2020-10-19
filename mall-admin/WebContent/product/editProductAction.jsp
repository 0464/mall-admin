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
	
	Product product = new Product();
	product.productId = Integer.parseInt(request.getParameter("productId"));
	product.categoryId = Integer.parseInt(request.getParameter("categoryId"));
	product.productName = request.getParameter("productName");
	product.productPrice = Integer.parseInt(request.getParameter("productPrice"));
	product.productContent = request.getParameter("productContent");
	ProductDao productDao = new ProductDao();
	productDao.updateProduct(product);

	response.sendRedirect("/mall-admin/product/productList.jsp");
%>