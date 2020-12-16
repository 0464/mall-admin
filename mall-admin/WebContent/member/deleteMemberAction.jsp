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
	
	String memberEmail = request.getParameter("memberEmail");
	
	// System.out.println(memberEmail+" <- memberEmail");
	
	MemberDao memberDao = new MemberDao();
	memberDao.deleteMember(memberEmail);
	
	response.sendRedirect("/mall-admin/member/memberList.jsp");
%>