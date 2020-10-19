package dao;

import java.sql.*;

import vo.*;


public class AdminDao {
	public Admin login(Admin admin) throws Exception {
		// 리턴값으로 보내줄 관리자 객체를 만듦
		Admin returnAdmin = null;
		
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
		String sql = "SELECT admin_id, admin_pw FROM admin WHERE admin_id=? AND admin_pw=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, admin.adminId);
		stmt.setString(2, admin.adminPw);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행 및 ResultSet을 ArrayList로 변환
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs+"<-rs"); // 테스트용 출력
		if (rs.next()) {
			returnAdmin = new Admin();
			returnAdmin.adminId = rs.getString("admin_id");
		}
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
		
		// null이 리턴되면 로그인에 실패한 것
		return returnAdmin;
	}
}
