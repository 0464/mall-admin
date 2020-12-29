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
<title>ordersList</title>
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
	
	OrdersPage ordersPage = new OrdersPage();

	if (request.getParameter("currentPage") != null) {
		ordersPage.currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}

	String ordersState = "";
	if (request.getParameter("ordersState") != null) {
		ordersState = request.getParameter("ordersState");
	}
	
	OrdersDao ordersDao = new OrdersDao();
	ArrayList<OrdersAndProduct> list = null;

	if (ordersState.equals("")) {
		list = ordersDao.selectOrdersListWithPage(ordersPage);
	} else {
		list = ordersDao.selectOrdersListWithPageSearchByOrdersState(ordersPage, ordersState);
	}
%>
<div>
<div><br>
	<h1>주문 목록</h1>
</div>
<div>
	<ul class="pagination">
		<%
			String searchItemClasses = defaultItemClasses;
			if (ordersState.equals("")) {
				searchItemClasses = activeItemClasses;
			}
		%>
			<li class="<%=searchItemClasses%>">
				<a class="page-link" href="/mall-admin/orders/ordersList.jsp">전체 주문 목록 표시</a>
			</li>
		<%

			ArrayList<String> ordersStateList = ordersDao.selectOrdersStateList();
			for (String state : ordersStateList) {
				searchItemClasses = defaultItemClasses;
				if (ordersState.equals(state)) {
					searchItemClasses = activeItemClasses;
				}
		%>
				<li class="<%=searchItemClasses%>">
					<a class="page-link" href="/mall-admin/orders/ordersList.jsp?ordersState=<%=state%>"><%=state %></a>
				</li>
		<%
			}
		%>
	</ul>
	<table class="table">
		<thead>
			<tr>
				<!-- <th>orders_id</th> -->
				<th>orders_date</th>
				<th>product_name</th>
				<!-- <th>orders_amount</th> -->
				<!-- <th>orders_price</th> -->
				<th>member_email</th>
				<!-- <th>orders_addr</th> -->
				<th>orders_state</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (list.size() == 0) {
			%>
					<tr class="table-secondary">
						<td colspan="5" style="text-align: center">주문 내역 없음</td>
					</tr>
			<%
				}

				final String paidLineClasses = "table-primary";
				final String deliveryStartLineClasses = "table-warning";
				final String deliveryEndLineClasses = "table-success";
				final String cancelLineClasses = "table-danger";
			
				for (OrdersAndProduct oap : list) {

					String lineClasses = "";
					if (oap.orders.ordersState.equals("결제완료")) {
						lineClasses = paidLineClasses;
					} else if (oap.orders.ordersState.equals("배송준비중")) {
						lineClasses = deliveryStartLineClasses;
					} else if (oap.orders.ordersState.equals("배송완료")) {
						lineClasses = deliveryEndLineClasses;
					} else if (oap.orders.ordersState.equals("주문취소")) {
						lineClasses = cancelLineClasses;
					}
			%>
					<tr class="<%=lineClasses%>">
						<!-- <td><%=oap.orders.ordersId %></td> -->
						<td><%=oap.orders.ordersDate.replace(".0", "") %></td>
						<td><%=oap.product.productName %></td>
						<!-- <td><%=oap.orders.ordersAmount %></td> -->
						<!-- <td><%=oap.orders.ordersPrice %></td> -->
						<td><%=oap.orders.memberEmail %></td>
						<!-- <td><%=oap.orders.ordersAddr %></td> -->
						<td><%=oap.orders.ordersState %></td>
						<td><a class="btn btn-sm btn-outline-dark" href="/mall-admin/orders/ordersOne.jsp?ordersId=<%=oap.orders.ordersId%>">상세보기</a></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<hr>
	<div class="row">
		<div class="col-12">
			<ul class="pagination">
				<%

					int currentPage = ordersPage.currentPage;
					int naviAmount = ordersPage.naviAmount;
					int naviLastPage = 0;

					if (ordersState.equals("")) {
						naviLastPage = ordersPage.getLastPage();
					} else {
						naviLastPage = ordersPage.getLastPageSearchByOrdersState(ordersState);
					}
					int naviStartPage = currentPage - (currentPage%naviAmount) + 1;

					if (currentPage%naviAmount == 0) {
						naviStartPage -= naviAmount;
					}
					int naviEndPage = naviStartPage + naviAmount - 1;

					String naviPrevItemClasses = defaultItemClasses;
					String naviFirstLink = "/mall-admin/orders/ordersList.jsp";
					String naviPrevLink = "/mall-admin/orders/ordersList.jsp?currentPage="+(naviStartPage-1);

					String naviNextItemClasses = defaultItemClasses;
					String naviNextLink = "/mall-admin/orders/ordersList.jsp?currentPage="+(naviEndPage+1);
					String naviLastLink = "/mall-admin/orders/ordersList.jsp?currentPage="+naviLastPage;
					

					if (!ordersState.equals("")) {
						naviFirstLink = "/mall-admin/orders/ordersList.jsp?ordersState="+ordersState;
						naviPrevLink = "/mall-admin/orders/ordersList.jsp?currentPage="+(naviStartPage-1)+"&ordersState="+ordersState;
						naviNextLink = "/mall-admin/orders/ordersList.jsp?currentPage="+(naviEndPage+1)+"&ordersState="+ordersState;
						naviLastLink = "/mall-admin/orders/ordersList.jsp?currentPage="+naviLastPage+"&ordersState="+ordersState;
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
						String naviLink = "/mall-admin/orders/ordersList.jsp?currentPage="+naviPage;
						
						if (!ordersState.equals("")) {
							naviLink = "/mall-admin/orders/ordersList.jsp?currentPage="+naviPage+"&ordersState="+ordersState;
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
	</div>
</div>
</div>
</div>
</body>
</html>