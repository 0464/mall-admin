package dao;

import java.util.*;
import java.sql.*;

import vo.*;


public class OrdersDao {
	public ArrayList<OrdersAndProduct> selectOrdersList() throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT orders.orders_id, orders.orders_date, orders.product_id, product.product_name, orders.orders_amount, orders.orders_price, orders.member_email, orders.orders_addr, orders.orders_state FROM orders INNER JOIN product ON orders.product_id=product.product_id";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 OrdersAndProduct 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			OrdersAndProduct ordersAndProduct = new OrdersAndProduct();
			ordersAndProduct.orders = new Orders();
			ordersAndProduct.product = new Product();
			
			ordersAndProduct.orders.ordersId = rs.getInt("orders.orders_id");
			ordersAndProduct.orders.ordersDate = rs.getString("orders.orders_date");
			ordersAndProduct.orders.productId = rs.getInt("orders.product_id");
			ordersAndProduct.product.productName = rs.getString("product.product_name");
			ordersAndProduct.orders.ordersAmount = rs.getInt("orders.orders_amount");
			ordersAndProduct.orders.ordersPrice = rs.getInt("orders.orders_price");
			ordersAndProduct.orders.memberEmail = rs.getString("orders.member_email");
			ordersAndProduct.orders.ordersAddr = rs.getString("orders.orders_addr");
			ordersAndProduct.orders.ordersState = rs.getString("orders.orders_state");
			
			list.add(ordersAndProduct);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	
	public ArrayList<OrdersAndProduct> selectOrdersListWithPage(OrdersPage page) throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT orders.orders_id, orders.orders_date, orders.product_id, product.product_name, orders.orders_amount, orders.orders_price, orders.member_email, orders.orders_addr, orders.orders_state FROM orders INNER JOIN product ON orders.product_id=product.product_id LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(2, page.rowPerPage);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 OrdersAndProduct 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			OrdersAndProduct ordersAndProduct = new OrdersAndProduct();
			ordersAndProduct.orders = new Orders();
			ordersAndProduct.product = new Product();
			
			ordersAndProduct.orders.ordersId = rs.getInt("orders.orders_id");
			ordersAndProduct.orders.ordersDate = rs.getString("orders.orders_date");
			ordersAndProduct.orders.productId = rs.getInt("orders.product_id");
			ordersAndProduct.product.productName = rs.getString("product.product_name");
			ordersAndProduct.orders.ordersAmount = rs.getInt("orders.orders_amount");
			ordersAndProduct.orders.ordersPrice = rs.getInt("orders.orders_price");
			ordersAndProduct.orders.memberEmail = rs.getString("orders.member_email");
			ordersAndProduct.orders.ordersAddr = rs.getString("orders.orders_addr");
			ordersAndProduct.orders.ordersState = rs.getString("orders.orders_state");
			
			list.add(ordersAndProduct);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	public ArrayList<OrdersAndProduct> selectOrdersListSearchByOrdersState(String ordersState) throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT orders.orders_id, orders.orders_date, orders.product_id, product.product_name, orders.orders_amount, orders.orders_price, orders.member_email, orders.orders_addr FROM orders INNER JOIN product ON orders.product_id=product.product_id WHERE orders_state=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, ordersState);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 Orders 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			OrdersAndProduct ordersAndProduct = new OrdersAndProduct();
			ordersAndProduct.orders = new Orders();
			ordersAndProduct.product = new Product();
			
			ordersAndProduct.orders.ordersId = rs.getInt("orders.orders_id");
			ordersAndProduct.orders.ordersDate = rs.getString("orders.orders_date");
			ordersAndProduct.orders.productId = rs.getInt("orders.product_id");
			ordersAndProduct.product.productName = rs.getString("product.product_name");
			ordersAndProduct.orders.ordersAmount = rs.getInt("orders.orders_amount");
			ordersAndProduct.orders.ordersPrice = rs.getInt("orders.orders_price");
			ordersAndProduct.orders.memberEmail = rs.getString("orders.member_email");
			ordersAndProduct.orders.ordersAddr = rs.getString("orders.orders_addr");
			ordersAndProduct.orders.ordersState = ordersState;
			
			list.add(ordersAndProduct);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	
	public ArrayList<OrdersAndProduct> selectOrdersListWithPageSearchByOrdersState(OrdersPage page, String ordersState) throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT orders.orders_id, orders.orders_date, orders.product_id, product.product_name, orders.orders_amount, orders.orders_price, orders.member_email, orders.orders_addr FROM orders INNER JOIN product ON orders.product_id=product.product_id WHERE orders_state=? LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, ordersState);
		stmt.setInt(2, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(3, page.rowPerPage);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 Orders 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			OrdersAndProduct ordersAndProduct = new OrdersAndProduct();
			ordersAndProduct.orders = new Orders();
			ordersAndProduct.product = new Product();
			
			ordersAndProduct.orders.ordersId = rs.getInt("orders.orders_id");
			ordersAndProduct.orders.ordersDate = rs.getString("orders.orders_date");
			ordersAndProduct.orders.productId = rs.getInt("orders.product_id");
			ordersAndProduct.product.productName = rs.getString("product.product_name");
			ordersAndProduct.orders.ordersAmount = rs.getInt("orders.orders_amount");
			ordersAndProduct.orders.ordersPrice = rs.getInt("orders.orders_price");
			ordersAndProduct.orders.memberEmail = rs.getString("orders.member_email");
			ordersAndProduct.orders.ordersAddr = rs.getString("orders.orders_addr");
			ordersAndProduct.orders.ordersState = ordersState;
			
			list.add(ordersAndProduct);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	public ArrayList<String> selectOrdersStateList() throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<String> list = new ArrayList<String>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT DISTINCT orders_state FROM orders";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 Orders 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			list.add(rs.getString("orders_state"));
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	public int selectOrdersCount() throws Exception {
		// 리턴값으로 보내줄 int형 변수를 만듦
		int count = 0;
		
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT COUNT(*) FROM orders";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet에서 count 추출
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		return count;
	}
	public int selectOrdersCountSearchByOrdersState(String ordersState) throws Exception {
		// 리턴값으로 보내줄 int형 변수를 만듦
		int count = 0;
		
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT COUNT(*) FROM orders WHERE orders_state=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, ordersState);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet에서 count 추출
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		return count;
	}
	
	public Orders selectOrdersOne(int ordersId) throws Exception {
		Orders orders = new Orders();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT product_id, orders_date, orders_amount, orders_price, member_email, orders_addr, orders_state FROM orders WHERE orders_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ordersId);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 Orders 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			orders.ordersId = ordersId;
			orders.productId = rs.getInt("product_id");
			orders.ordersDate = rs.getString("orders_date");
			orders.ordersAmount = rs.getInt("orders_amount");
			orders.ordersPrice = rs.getInt("orders_price");
			orders.memberEmail = rs.getString("member_email");
			orders.ordersAddr = rs.getString("orders_addr");
			orders.ordersState = rs.getString("orders_state");
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return orders;
	}
	
	public OrdersAndProduct selectOrdersAndProductOne(int ordersId) throws Exception {
		OrdersAndProduct ordersAndProduct = new OrdersAndProduct();
		ordersAndProduct.orders = new Orders();
		ordersAndProduct.product = new Product();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "SELECT orders.orders_date, orders.product_id, product.product_name, orders.orders_amount, orders.orders_price, orders.member_email, orders.orders_addr, orders.orders_state FROM orders INNER JOIN product ON orders.product_id=product.product_id WHERE orders_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ordersId);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행 및 결과값을 Orders 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			ordersAndProduct.orders.ordersId = ordersId;
			ordersAndProduct.orders.ordersDate = rs.getString("orders.orders_date");
			ordersAndProduct.orders.productId = rs.getInt("orders.product_id");
			ordersAndProduct.product.productName = rs.getString("product.product_name");
			ordersAndProduct.orders.ordersAmount = rs.getInt("orders.orders_amount");
			ordersAndProduct.orders.ordersPrice = rs.getInt("orders.orders_price");
			ordersAndProduct.orders.memberEmail = rs.getString("orders.member_email");
			ordersAndProduct.orders.ordersAddr = rs.getString("orders.orders_addr");
			ordersAndProduct.orders.ordersState = rs.getString("orders.orders_state");
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return ordersAndProduct;
	}
	public void updateOrdersState(Orders orders) throws Exception {
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		// 쿼리문 생성
		String sql = "UPDATE orders SET orders_state=? WHERE orders_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(2, orders.ordersId);
		stmt.setString(1, orders.ordersState);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력

		// 쿼리 실행
		stmt.executeUpdate();
		
		// DB 작업 완료후 접속 해제
		conn.close();
	}
}
