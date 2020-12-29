<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션 변수로 로그인했는지 확인한 후, 비로그인한 사용자에 대하여 해당 페이지의 접근 권한이 없으니 로그인 페이지로 이동시킴
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
<title>index</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<div>
<div><br>
	<h1>관리자 메인 페이지</h1>
</div>
<div>
<p>
	<img src="/mall-admin/image/sel.png">
</p>
<p>
	쇼핑몰 관리용 메인 페이지
</p>
</div>
</div>
</div>
</body>
</html>