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
<title>productList</title>
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
	
	ProductPage productPage = new ProductPage();

	if (request.getParameter("currentPage") != null) {
		productPage.currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int categoryId = -1;
	if (request.getParameter("categoryId") != null) {
		categoryId = Integer.parseInt(request.getParameter("categoryId"));
	}
	ProductDao productDao = new ProductDao();

	ArrayList<ProductAndCategory> list = null;
	if (categoryId == -1) {
		list = productDao.selectProductListWithPage(productPage);
	} else {
		list = productDao.selectProductListWithPageSearchByCategoryId(productPage, categoryId);
	}
%>
<div>
<div><br>
	<h1>상품 목록</h1>
</div>
<div>
	<ul class="pagination">
		<%
			// 검색한 카테고리가 없다면 전체 카테고리 표시 버튼의 색상을 강조
			String searchItemClasses = defaultItemClasses;
			if (categoryId == -1) {
				searchItemClasses = activeItemClasses;
			}
		%>
			<li class="<%=searchItemClasses%>">
				<a class="page-link" href="/mall-admin/product/productList.jsp">전체 카테고리 표시</a>
			</li>
		<%
			CategoryDao categoryDao = new CategoryDao();
			ArrayList<Category> categoryList = categoryDao.selectCategoryList();
			
			for (Category c : categoryList) {
				// 검색한 카테고리와 일치하면 버튼 색상을 강조
				searchItemClasses = defaultItemClasses;
				if (categoryId == c.categoryId) {
					searchItemClasses = activeItemClasses;
				}
		%>
			<li class="<%=searchItemClasses%>">
				<a class="page-link" href="/mall-admin/product/productList.jsp?categoryId=<%=c.categoryId%>"><%=c.categoryName %></a>
			</li>
		<%
			}
		%>
	</ul>
	<table class="table">
		<thead>
			<tr>
				<th>product_id</th>
				<th>category_name</th>
				<th>product_name</th>
				<th>product_price</th>
				<th>product_soldout</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (list.size() == 0) {
			%>
				<tr class="table-secondary">
					<td colspan="6" style="text-align: center">상품 없음</td>
				</tr>
			<%
				}
				final String defaultLineClasses = "table-success";
				final String soldoutLineClasses = "table-danger";
				
				for (ProductAndCategory pac : list) {
					String lineClasses = defaultLineClasses;
					String soldoutText = "판매중";
					if (pac.product.productSoldout.equals("Y")) {
						lineClasses = soldoutLineClasses;
						soldoutText = "품절";
					}
			%>
					<tr class="<%=lineClasses%>">
						<td><%=pac.product.productId %></td>
						<td><%=pac.category.categoryName %></td>
						<td><%=pac.product.productName %></td>
						<td><%=pac.product.productPrice %></td>
						<td><%=soldoutText %></td>
						<td><a class="btn btn-sm btn-outline-dark" href="/mall-admin/product/productOne.jsp?productId=<%=pac.product.productId%>">상세보기</a></td>
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
					int currentPage = productPage.currentPage;
					int naviAmount = productPage.naviAmount;
					int naviLastPage = 0;
					if (categoryId == -1) {
						naviLastPage = productPage.getLastPage();
					} else {
						naviLastPage = productPage.getLastPageSearchByCategoryId(categoryId);
					}

					int naviStartPage = currentPage - (currentPage%naviAmount) + 1;

					if (currentPage%naviAmount == 0) {
						naviStartPage -= naviAmount;
					}
					int naviEndPage = naviStartPage + naviAmount - 1;
					String naviPrevItemClasses = defaultItemClasses;
					String naviFirstLink = "/mall-admin/product/productList.jsp";
					String naviPrevLink = "/mall-admin/product/productList.jsp?currentPage="+(naviStartPage-1);
					// 다음 관련 페이징 버튼에 적용할 HTML 소스의 일부를 변수로 선언
					String naviNextItemClasses = defaultItemClasses;
					String naviNextLink = "/mall-admin/product/productList.jsp?currentPage="+(naviEndPage+1);
					String naviLastLink = "/mall-admin/product/productList.jsp?currentPage="+naviLastPage;
					
					// 검색 조건이 추가될 경우, 링크를 수정함
					if (categoryId != -1) {
						naviFirstLink = "/mall-admin/product/productList.jsp?categoryId="+categoryId;
						naviPrevLink = "/mall-admin/product/productList.jsp?currentPage="+(naviStartPage-1)+"&categoryId="+categoryId;
						naviNextLink = "/mall-admin/product/productList.jsp?currentPage="+(naviEndPage+1)+"&categoryId="+categoryId;
						naviLastLink = "/mall-admin/product/productList.jsp?currentPage="+naviLastPage+"&categoryId="+categoryId;
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
					// 각 페이지로 이동하는 버튼들을 for문으로 생성, rowPerPage에 영향을 받음
					for (int naviPage = naviStartPage; naviPage <= naviEndPage; naviPage += 1) {
						if (naviPage > naviLastPage) {
							break;
						}
						
						String naviItemClasses = defaultItemClasses;
						String naviLink = "/mall-admin/product/productList.jsp?currentPage="+naviPage;
						
						if (categoryId != -1) {
							naviLink = "/mall-admin/product/productList.jsp?currentPage="+naviPage+"&categoryId="+categoryId;
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
			<a class="btn btn-outline-dark btn-block" href="/mall-admin/product/addProduct.jsp">상품 추가</a>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>