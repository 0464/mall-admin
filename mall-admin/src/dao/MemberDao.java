package dao;

import java.sql.*;
import java.util.ArrayList;

import vo.*;

public class MemberDao {
	public ArrayList<Member> memberList() throws Exception {
		ArrayList<Member> list = new ArrayList<Member>();
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		String sql = "select member_email, member_name, member_date from member";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Member m = new Member();
			m.setMemberEmail(rs.getString("member_email"));
			m.setMemberName(rs.getString("member_name"));
			m.setMemberDate(rs.getString("member_date"));
			list.add(m);
		}
		conn.close();
		return list;
	}
	public Member memberOne(String memberEmail) throws Exception {
		Member member = new Member();
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		String sql = "select member_email, member_name from member where member_email=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			member.setMemberEmail(rs.getString("member_email"));
			member.setMemberName(rs.getString("member_name"));
		}
		conn.close();
		return member;
	}
	public void deleteMember(String memberEmail) throws Exception {
		// DB를 사용하기 위한 정보들
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		
		// DB 드라이버 로드 및 연결(접속)
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn+"<-conn"); // 테스트용 출력
		
		String sql = "delete from member where member_email=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		System.out.println(stmt+"<-stmt"); // 테스트용 출력
		
		// 생성된 쿼리문 실행
		stmt.executeUpdate();
		
		// 접속한 DB와의 연결을 끊어줌
		conn.close();
	}
}
