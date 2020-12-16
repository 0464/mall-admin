<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션 변수로 로그인했는지 체크 후, 로그인했다면 별도 로그인 페이지 필요없이 바로 index.jsp 호출
	if (session.getAttribute("loginAdminId") != null) {
		System.out.println("이미 로그인됨");
		
		response.sendRedirect("/mall-admin/index.jsp");
		
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<p>
<div>
<div>
	<h1>관리자 로그인</h1>
</div>
<div>
	<form method="post" action="/mall-admin/loginAction.jsp">
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="adminId">관리자 ID:</label>
					<input id="adminId" class="form-control" type="text" name="adminId" value="admin">
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="adminPw">관리자 PW:</label>
					<input id="adminPw" class="form-control" type="password" name="adminPw" value="1234">
				</div>
			</div>
		</div>
		<hr>
		<div class="form-group">
			<button class="btn btn-outline-primary" type="submit">로그인</button>
		</div>
	</form>
	<%
		String errorMessage = "";
		
		String loginRequired = request.getParameter("loginRequired");
		if (loginRequired != null && loginRequired.equals("true")) {
			errorMessage = "로그인이 필요합니다";
		}
		String loginFailed = request.getParameter("loginFailed");
		if (loginFailed != null && loginFailed.equals("true")) {
			errorMessage = "로그인에 실패했습니다";
		}
		
		if (!errorMessage.equals("")) {
	%>
			<span class="btn btn-outline-danger btn-block disabled">
				<%=errorMessage %>
			</span>
	<%
		}
	%>
</div>
</div>
</div>
</body>
</html>