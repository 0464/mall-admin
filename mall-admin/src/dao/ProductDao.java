package dao;

import java.util.*;
import java.sql.*;

import vo.*;


public class ProductDao {
	public ArrayList<ProductAndCategory> selectProductList() throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<ProductAndCategory> list = new ArrayList<ProductAndCategory>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT product.product_id, product.category_id, category.category_name, product.product_name, product.product_price, product.product_soldout FROM product INNER JOIN category ON product.category_id=category.category_id";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 쿼리 실행 및 결과값을 ProductAndCategory 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductAndCategory productAndCategory = new ProductAndCategory();
			productAndCategory.product = new Product();
			productAndCategory.category = new Category();
			productAndCategory.product.productId = rs.getInt("product_id");
			productAndCategory.product.categoryId = rs.getInt("category_id");
			productAndCategory.category.categoryName = rs.getString("category.category_name");
			productAndCategory.product.productName = rs.getString("product_name");
			productAndCategory.product.productPrice = rs.getInt("product_price");
			productAndCategory.product.productSoldout = rs.getString("product_soldout");
			
			list.add(productAndCategory);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}
	public ArrayList<ProductAndCategory> selectProductListWithPage(ProductPage page) throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<ProductAndCategory> list = new ArrayList<ProductAndCategory>();
		
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT product.product_id, product.category_id, category.category_name, product.product_name, product.product_price, product.product_soldout FROM product INNER JOIN category ON product.category_id=category.category_id LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(2, page.rowPerPage);
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductAndCategory productAndCategory = new ProductAndCategory();
			productAndCategory.product = new Product();
			productAndCategory.category = new Category();
			productAndCategory.product.productId = rs.getInt("product_id");
			productAndCategory.product.categoryId = rs.getInt("category_id");
			productAndCategory.category.categoryName = rs.getString("category.category_name");
			productAndCategory.product.productName = rs.getString("product_name");
			productAndCategory.product.productPrice = rs.getInt("product_price");
			productAndCategory.product.productSoldout = rs.getString("product_soldout");
			
			list.add(productAndCategory);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Product> 형태로 결과 반환
		return list;
	}

	public ArrayList<ProductAndCategory> selectProductListSearchByCategoryId(int categoryId) throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		ArrayList<ProductAndCategory> list = new ArrayList<ProductAndCategory>();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT product.product_id, product.category_id, category.category_name, product.product_name, product.product_price, product.product_soldout FROM product INNER JOIN category ON product.category_id=category.category_id WHERE product.category_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		
		// 쿼리 실행 및 결과값을 Product 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductAndCategory productAndCategory = new ProductAndCategory();
			productAndCategory.product = new Product();
			productAndCategory.category = new Category();
			productAndCategory.product.productId = rs.getInt("product_id");
			productAndCategory.product.categoryId = rs.getInt("category_id");
			productAndCategory.category.categoryName = rs.getString("category.category_name");
			productAndCategory.product.productName = rs.getString("product_name");
			productAndCategory.product.productPrice = rs.getInt("product_price");
			productAndCategory.product.productSoldout = rs.getString("product_soldout");
			
			list.add(productAndCategory);
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return list;
	}

	public ArrayList<ProductAndCategory> selectProductListWithPageSearchByCategoryId(ProductPage page, int categoryId) throws Exception {
		// 리턴값으로 보내줄 리스트를 만듦
		ArrayList<ProductAndCategory> list = new ArrayList<ProductAndCategory>();
		
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT product.product_id, product.category_id, category.category_name, product.product_name, product.product_price, product.product_soldout FROM product INNER JOIN category ON product.category_id=category.category_id WHERE product.category_id=? LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		stmt.setInt(2, (page.currentPage-1)*page.rowPerPage); // 페이지는 1페이지부터 시작하지만, 인덱스는 0번 행부터 시작하기에 1을 빼주고 rowPerPage를 곱함
		stmt.setInt(3, page.rowPerPage);
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductAndCategory productAndCategory = new ProductAndCategory();
			productAndCategory.product = new Product();
			productAndCategory.category = new Category();
			productAndCategory.product.productId = rs.getInt("product_id");
			productAndCategory.product.categoryId = rs.getInt("category_id");
			productAndCategory.category.categoryName = rs.getString("category.category_name");
			productAndCategory.product.productName = rs.getString("product_name");
			productAndCategory.product.productPrice = rs.getInt("product_price");
			// productAndCategory.product.productContent = rs.getString("product_content"); // 무지막지하게 긴 데이터라는 전제 하에 제외하고 불러옴
			productAndCategory.product.productSoldout = rs.getString("product_soldout");
			
			list.add(productAndCategory);
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// ArrayList<Product> 형태로 결과 반환
		return list;
	}
	

	public int selectProductCount() throws Exception {
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
		
		// 쿼리문 생성
		String sql = "SELECT COUNT(*) FROM product";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 생성된 쿼리문 실행 및 ResultSet에서 count 추출
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		return count;
	}

	public int selectProductCountSearchByCategoryId(int categoryId) throws Exception {
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
		
		// 쿼리문 생성
		String sql = "SELECT COUNT(*) FROM product WHERE category_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		
		// 생성된 쿼리문 실행 및 ResultSet에서 count 추출
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		return count;
	}

	public Product selectProductOne(int productId) throws Exception {
		// 리턴값으로 내보낼 리스트를 만듦
		Product product = new Product();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT category_id, product_name, product_price, product_content, product_soldout FROM product WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);

		
		// 쿼리 실행 및 결과값을 Product 객체의 리스트로 변환
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			product.productId = productId;
			product.categoryId = rs.getInt("category_id");
			product.productName = rs.getString("product_name");
			product.productPrice = rs.getInt("product_price");
			product.productContent = rs.getString("product_content");
			product.productSoldout = rs.getString("product_soldout");
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return product;
	}

	public ProductAndCategory selectProductAndCategoryOne(int productId) throws Exception {
		// 리턴값으로 내보낼 ProductAndCategory 객체를 만듦
		ProductAndCategory productAndCategory = new ProductAndCategory();
		productAndCategory.product = new Product();
		productAndCategory.category = new Category();
		
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "SELECT product.category_id, category.category_name, product.product_name, product.product_price, product.product_content, product.product_soldout FROM product INNER JOIN category ON product.category_id=category.category_id WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			productAndCategory.product.productId = productId;
			productAndCategory.product.categoryId = rs.getInt("product.category_id");
			productAndCategory.category.categoryName = rs.getString("category.category_name");
			productAndCategory.product.productName = rs.getString("product.product_name");
			productAndCategory.product.productPrice = rs.getInt("product.product_price");
			productAndCategory.product.productContent = rs.getString("product.product_content");
			productAndCategory.product.productSoldout = rs.getString("product.product_soldout");
		}
		
		// DB 작업 완료후 접속 해제
		conn.close();
		
		return productAndCategory;
	}
	public void insertProduct(Product product) throws Exception {
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "INSERT INTO product(category_id, product_name, product_price, product_content, product_soldout) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.categoryId);
		stmt.setString(2, product.productName);
		stmt.setInt(3, product.productPrice);
		stmt.setString(4, product.productContent);
		stmt.setString(5, product.productSoldout);
		
		// 쿼리 실행
		stmt.executeUpdate();
		
		// DB 작업 완료후 접속 해제
		conn.close();
	}
	
	public void updateProductSoldout(int productId, String productSoldout) throws Exception {
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "UPDATE product SET product_soldout=? WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(2, productId); // WHERE절 뒤에 있으므로 2번이 됨
		if (productSoldout.equals("Y")) {
			stmt.setString(1, "N");
		} else {
			stmt.setString(1, "Y");
		}
		
		// 쿼리 실행
		int row = stmt.executeUpdate();
		System.out.println(row+"행 수정됨");
		
		// DB 작업 완료후 접속 해제
		conn.close();
	}
	public void updateProduct(Product product) throws Exception {
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "UPDATE product SET category_id=?, product_name=?, product_price=?, product_content=? WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(5, product.productId); // WHERE절 뒤에 있으므로 5번이 됨
		stmt.setInt(1, product.categoryId);
		stmt.setString(2, product.productName);
		stmt.setInt(3, product.productPrice);
		stmt.setString(4, product.productContent);
		
		// 쿼리 실행
		stmt.executeUpdate();
		
		// DB 작업 완료후 접속 해제
		conn.close();
	}
	public void deleteProduct(Product product) throws Exception {
		// DB 접속에 필요한 드라이버 및 주소와 ID,PW
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		
		// 쿼리문 생성
		String sql = "DELETE FROM product WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.productId);
		
		// 쿼리 실행
		stmt.executeUpdate();
		
		// DB 작업 완료후 접속 해제
		conn.close();
	}
}
