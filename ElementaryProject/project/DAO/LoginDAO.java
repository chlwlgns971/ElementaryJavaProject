package DAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.SignUpDTO;
import util.DBConnection;

public class LoginDAO extends DBConnection {
	public void insert(SignUpDTO dto) {
		Connection conn=null;
		Statement stat=null;
		try {
			conn=getConnection();
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String count="";
			rs=stat.executeQuery("SELECT COUNT(MEM_ID) FROM MEMBER WHERE MEM_ID='"+userID+"'");
			while(rs.next()) {
				count=rs.getString(1);
			}
			if(count.equals("0")) check=true;
			else check=false;
		}
		catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
	}
}
