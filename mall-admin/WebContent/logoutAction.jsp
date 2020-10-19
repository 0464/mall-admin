<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션 정보를 모두 지우고 초기화시킴
	session.invalidate();
	
	// 로그아웃 후 로그인 페이지로 이동시킴
	response.sendRedirect("/mall-admin/login.jsp");
%>