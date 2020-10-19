package dao;

import java.util.ArrayList;
import java.sql.*;

import vo.*;


public class CategoryDao {
	public ArrayList<Category> selectCategoryList() throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<Category> list = new ArrayList<Category>();
		
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
		String sql = "SELECT category_id, category_name FROM category";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			Category category = new Category();
			category.categoryId = rs.getInt("category_id");
			category.categoryName = rs.getString("category_name");
			
			list.add(category);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Category> 형태로 결과 반환
		return list;
	}
	public ArrayList<Category> selectCategoryListWithPage(CategoryPage page) throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<Category> list = new ArrayList<Category>();
		
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
		String sql = "SELECT category_id, category_name FROM category LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(2, page.rowPerPage);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			Category category = new Category();
			category.categoryId = rs.getInt("category_id");
			category.categoryName = rs.getString("category_name");
			
			list.add(category);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Category> 형태로 결과 반환
		return list;
	}
	
	public ArrayList<Category> selectCategoryListSearchByCategoryName(String categoryName) throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<Category> list = new ArrayList<Category>();
		
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
		String sql = "SELECT category_id, category_name FROM category WHERE category_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+categoryName+"%");
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			Category category = new Category();
			category.categoryId = rs.getInt("category_id");
			category.categoryName = rs.getString("category_name");
			
			list.add(category);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Category> 형태로 결과 반환
		return list;
	}
	public ArrayList<Category> selectCategoryListWithPageSearchByCategoryName(CategoryPage page, String categoryName) throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<Category> list = new ArrayList<Category>();
		
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
		String sql = "SELECT category_id, category_name FROM category WHERE category_name LIKE ? LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+categoryName+"%");
		stmt.setInt(2, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(3, page.rowPerPage);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		while (rs.next()) {
			Category category = new Category();
			category.categoryId = rs.getInt("category_id");
			category.categoryName = rs.getString("category_name");
			
			list.add(category);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Category> 형태로 결과 반환
		return list;
	}
	
	public int selectCategoryCount() throws Exception {
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
		String sql = "SELECT COUNT(*) FROM category";
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
	public int selectCategoryCountSearchByCategoryName(String categoryName) throws Exception {
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
		String sql = "SELECT COUNT(*) FROM category WHERE category_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+categoryName+"%");
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
	public Category selectCategoryOne(int categoryId) throws Exception {
		// 리턴값으로 보내줄 카테고리를 만듦
		Category category = new Category();
		
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
		String sql = "SELECT category_name FROM category WHERE category_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 Category로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			category.categoryId = categoryId;
			category.categoryName = rs.getString("category_name");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// Category 형태로 결과 반환
		return category;
	}
	
	public void insertCategory(Category category) throws Exception {
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
		String sql = "INSERT INTO category(category_name) VALUES(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, category.categoryName);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행
		stmt.executeUpdate();
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
	}
	
	public void updateCategory(Category category) throws Exception {
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
		String sql = "UPDATE category SET category_name=? WHERE category_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, category.categoryName);
		stmt.setInt(2, category.categoryId);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행
		stmt.executeUpdate();
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
	}
	public void deleteCategory(int categoryId) throws Exception {
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
		String sql = "DELETE FROM category WHERE category_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행
		stmt.executeUpdate();
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
	}
}
