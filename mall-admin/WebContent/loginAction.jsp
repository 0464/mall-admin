<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	Admin paramAdmin = new Admin();
	paramAdmin.adminId = request.getParameter("adminId");
	paramAdmin.adminPw = request.getParameter("adminPw");
	AdminDao adminDao = new AdminDao();
	Admin loginAdmin = adminDao.login(paramAdmin);
	
	if (loginAdmin != null) {
		
		session.setAttribute("loginAdminId", loginAdmin.adminId);
		
		response.sendRedirect("/mall-admin/index.jsp");
	} else {
		// ID와 PW를 비교하여 일치하지 않으면 다시 로그인 페이지로 이동
		response.sendRedirect("/mall-admin/login.jsp?loginFailed=true");
	}
%>