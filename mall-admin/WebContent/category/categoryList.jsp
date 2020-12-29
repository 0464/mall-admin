<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>
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
<title>categoryList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
	request.setCharacterEncoding("UTF-8");
	
	final String defaultItemClasses = "page-item";
	final String activeItemClasses = "page-item active";
	final String disabledItemClasses = "page-item disabled";
	final String disabledLink = "#";
	
	CategoryDao categoryDao = new CategoryDao();
	
	CategoryPage categoryPage = new CategoryPage();
	if (request.getParameter("currentPage") != null) {
		categoryPage.currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}

	String categoryName = "";
	if (request.getParameter("categoryName") != null) {
		categoryName = request.getParameter("categoryName");
	}
	
	ArrayList<Category> list = null;
	if (categoryName.equals("")) {
		list = categoryDao.selectCategoryListWithPage(categoryPage);
	} else {
		list = categoryDao.selectCategoryListWithPageSearchByCategoryName(categoryPage, categoryName);
	}
%>
<div>
<!-- 제목 시작 -->
<div><br>
	<h1>상품 카테고리</h1>
</div>
<div>
	<form method="post" action="/mall-admin/category/categoryList.jsp">
		<div class="row">
			<div class="col-8">
				<div class="form-group">
					<input class="form-control" type="text" name="categoryName" placeholder="이름으로 카테고리 검색" value="<%=categoryName%>">
				</div>
			</div>
			<div class="col-4">
				<div class="form-group">
					<button class="btn btn-outline-primary btn-block" type="submit">검색</button>
				</div>
			</div>
		</div>
	</form>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>category_id</th>
				<th>category_name</th>
				<th style="width:150px">&nbsp;</th>
				<th style="width:150px">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (list.size() == 0) {
			%>
					<tr class="table-secondary">
						<td colspan="4" style="text-align: center">카테고리 없음</td>
					</tr>
			<%
				}
			
				for (Category c : list) {
			%>
					<tr>
						<td><%=c.categoryId %></td>
						<td><%=c.categoryName %></td>
						<td><a class="btn btn-sm btn-outline-warning" href="/mall-admin/category/editCategory.jsp?categoryId=<%=c.categoryId%>">수정</a></td>
						<td><a class="btn btn-sm btn-outline-danger" href="/mall-admin/category/deleteCategory.jsp?categoryId=<%=c.categoryId%>">삭제</a></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<hr>
	<div class="row">
		<div class="col-8">
			<ul class="pagination">
				<%
					int currentPage = categoryPage.currentPage;
					int naviAmount = categoryPage.naviAmount;
					int naviLastPage = 0;
					if (categoryName.equals("")) {
						naviLastPage = categoryPage.getLastPage();
					} else {
						naviLastPage = categoryPage.getLastPageSearchByCategoryName(categoryName);
					}
					
					int naviStartPage = currentPage - (currentPage%naviAmount) + 1;
					if (currentPage%naviAmount == 0) {
						naviStartPage -= naviAmount;
					}
					int naviEndPage = naviStartPage + naviAmount - 1;
		
					String naviPrevItemClasses = defaultItemClasses;
					String naviFirstLink = "/mall-admin/category/categoryList.jsp";
					String naviPrevLink = "/mall-admin/category/categoryList.jsp?currentPage="+(naviStartPage-1);

					String naviNextItemClasses = defaultItemClasses;
					String naviNextLink = "/mall-admin/category/categoryList.jsp?currentPage="+(naviEndPage+1);
					String naviLastLink = "/mall-admin/category/categoryList.jsp?currentPage="+naviLastPage;

					if (!categoryName.equals("")) {
						naviFirstLink = "/mall-admin/category/categoryList.jsp?categoryName="+categoryName;
						naviPrevLink = "/mall-admin/category/categoryList.jsp?currentPage="+(naviStartPage-1)+"&categoryName="+categoryName;
						naviNextLink = "/mall-admin/category/categoryList.jsp?currentPage="+(naviEndPage+1)+"&categoryName="+categoryName;
						naviLastLink = "/mall-admin/category/categoryList.jsp?currentPage="+naviLastPage+"&categoryName="+categoryName;
					}
					
					if (naviStartPage <= 1) {
						naviPrevItemClasses = disabledItemClasses;
						naviFirstLink = disabledLink;
						naviPrevLink = disabledLink;
					}
					if (naviEndPage >= naviLastPage) {
						naviNextItemClasses = disabledItemClasses;
						naviNextLink = disabledLink;
						naviLastLink = disabledLink;
					}
				%>
					<li class="<%=naviPrevItemClasses%>">
						<a class="page-link" href="<%=naviFirstLink%>">처음으로</a>
					</li>
					<li class="<%=naviPrevItemClasses%>">
						<a class="page-link" href="<%=naviPrevLink%>">이전</a>
					</li>
				<%
					for (int naviPage = naviStartPage; naviPage <= naviEndPage; naviPage += 1) {
						if (naviPage > naviLastPage) {
							break;
						}

						String naviItemClasses = defaultItemClasses;
						String naviLink = "/mall-admin/category/categoryList.jsp?currentPage="+naviPage;
						
						if (!categoryName.equals("")) {
							naviLink = "/mall-admin/category/categoryList.jsp?currentPage="+naviPage+"&categoryName="+categoryName;
						}

						if (naviPage == currentPage) {
							naviItemClasses = activeItemClasses;
						}
				%>
						<li class="<%=naviItemClasses%>">
							<a class="page-link" href="<%=naviLink%>"><%=naviPage %></a>
						</li>
				<%
					}
				%>
					<li class="<%=naviNextItemClasses%>">
						<a class="page-link" href="<%=naviNextLink%>">다음</a>
					</li>
					<li class="<%=naviNextItemClasses%>">
						<a class="page-link" href="<%=naviLastLink%>">마지막으로</a>
					</li>
			</ul>
		</div>
		<div class="col-4">
			<a class="btn btn-outline-dark btn-block" href="/mall-admin/category/addCategory.jsp">상품 카테고리 추가</a>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>