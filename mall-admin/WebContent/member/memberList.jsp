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
<title>memberList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>
	<h1>고객 목록</h1>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>member_email</th>
				<th>member_name</th>
				<th>member_date</th>
				<th>탈퇴</th>
			</tr>
		</thead>
		<tbody>
			<%
				request.setCharacterEncoding("UTF-8");
				MemberDao memberDao = new MemberDao();
				ArrayList<Member> myList = memberDao.memberList();
	
				for (Member m : myList) {
			%>
			<tr>
				<td><%=m.getMemberEmail()%></td>
				<td><%=m.getMemberName()%></td>
				<td><%=m.getMemberDate()%></td>
				<td><a href="/mall-admin/member/deleteMember.jsp?memberEmail=<%=m.getMemberEmail()%>">탈퇴</a></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>