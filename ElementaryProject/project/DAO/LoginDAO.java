package DAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.SignUpDTO;
import util.DBConnection;

public class LoginDAO extends DBConnection {
	Connection conn=null;
	Statement stat=null;
	ResultSet rs=null;
	boolean check=true;
	public void insert(SignUpDTO dto) {
		try {
			conn=getConnection();
			stat=getStatement();
			rs=stat.executeQuery("INSERT INTO MEMBER VALUES('"+dto.getUserID()+"','"+dto.getPassWord()+"','"+dto.getName()+"'"
					+ ",'"+dto.getRegNo()+"','"+dto.getAddr()+"','"+dto.getUserPH()+"')");
			System.out.println("회원가입이 완료되었습니다.");
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			close(rs,stat,conn);
		}
	}
	public boolean checkID(String userID) {
		try {
			conn=getConnection();
			stat=getStatement();
			String count="";
			rs=stat.executeQuery("SELECT COUNT(MEM_ID) FROM MEMBER WHERE MEM_ID='"+userID+"'");
			while(rs.next()) {
				count=rs.getString(1);
			}
			if(count.equals("0")) check=true;
			else check=false;
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			close(rs,stat,conn);
		}
		return check;
	}
	public boolean checkIDandPW(String userID,String userPW) {
		try {
			conn=getConnection();
			stat=getStatement();
			String PW="";
			rs=stat.executeQuery("SELECT MEM_PW FROM MEMBER WHERE MEM_ID='"+userID+"'");
			while(rs.next()) {
				PW=rs.getString(1);
			}
			if(PW.equals(userPW)) check=true;
			else check=false;
		}	
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			close(rs,stat,conn);
		}
		return check;
	}
}
