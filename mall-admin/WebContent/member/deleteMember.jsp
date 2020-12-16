<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

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
<title>deleteMember</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
	request.setCharacterEncoding("UTF-8");
	
	String memberEmail = request.getParameter("memberEmail");
	MemberDao memberDao = new MemberDao();
	Member member = memberDao.memberOne(memberEmail);
%>
<div>
<div>
	<h1>고객 탈퇴</h1>
</div>
<div>
	<form method="post" action="/mall-admin/member/deleteMemberAction.jsp">
		<input type="hidden" name="memberEmail" value="<%=member.getMemberEmail() %>">
		
		<p>정말로 <%=member.getMemberName() %>을(를) 탈퇴시키겠습니까?</p>
		<p>탈퇴된 내용은 복구할 수 없습니다!</p>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-dark btn-block" type="submit">고객 탈퇴</button>
		</div>
	</form>
</div>
</div>
</div>
</body>
</html>