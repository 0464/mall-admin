<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 페이지의 상단 네비게이션으로 끼울 메뉴(페이지의 일부분만 구현한 JSP 파일) -->
<!-- bootstrap class 사용 -->
<nav class="navbar navbar-expand navbar-light bg-light">
	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="navbar-brand" href="/mall-admin/index.jsp">GoodeeShop</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/mall-admin/category/categoryList.jsp">상품 카테고리 관리</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/mall-admin/product/productList.jsp">상품 관리</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/mall-admin/orders/ordersList.jsp">주문 관리</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/mall-admin/member/memberList.jsp">고객 관리</a>
		</li>
		<li class="nav-item">
			<span class="nav-link disabled"><%=session.getAttribute("loginAdminId") %>님 반갑습니다</span>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/mall-admin/logoutAction.jsp">로그아웃</a>
		</li>
	</ul>
</nav>