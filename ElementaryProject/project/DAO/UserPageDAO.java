package DAO;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import util.DBConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserPageDAO extends DBConnection {
	Connection conn=null;
	Statement stat=null;
	ResultSet rs=null;
	public boolean checkIDandPW(String userID,String userPW) {
		boolean check=true;
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
	public void delete(String userID) {
		try {
			conn=getConnection();
			stat=getStatement();
			rs=stat.executeQuery("DELETE FROM MEMBER WHERE MEM_ID='"+userID+"'");
			System.out.println("회원탈퇴에 성공하였습니다.");
		}	
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			close(rs,stat,conn);
		}	
	}
	public Map<String, String> searchRsv(String id) {
		Map<String, String> resultMap= new HashMap<>();
		try {
			conn=getConnection();
			stat=getStatement();
			rs=stat.executeQuery("SELECT RSV_NO, REPLACE(RSV_ID,'"+id+"',(SELECT MEM_NAME FROM MEMBER WHERE MEM_ID='"+id+"')), RSV_CNO"
					+ ", CAR_NAME,REPLACE(RSV_BNO, RSV_BNO, (SELECT BRA_NAME FROM BRANCH INNER JOIN RSV ON(RSV_BNO=BRA_NO) WHERE RSV_ID='"+id+"'))"
							+ ", RSV_PAY, TO_CHAR(RSV_DATE,'YYYY-MM-DD'), TO_CHAR(RSV_RET,'YYYY-MM-DD') FROM RSV INNER JOIN CAR ON(CAR_NO=RSV_CNO) WHERE RSV_ID='"+id+"'"
									+ "ORDER BY RSV_NO DESC");
			//column갯수 구하기
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String key="";
			String str=""; //hashmap에 넣을 출력값
			while (rs.next()) {
				for(int i=1; i<=columnCount; i++) {
					if(i==1) {
						key=rs.getNString(i);
					}
					else {
						str+=" | "+rs.getString(i);
					}
				}
				resultMap.put(key, str);
				str="";
				key="";
			}
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			close(rs,stat,conn);
		}
		return resultMap;	
	}
	//회원 휴대폰번호 변경
	public void updateMemberHP(String res) {
		try {
			conn=getConnection();
			stat=getStatement();
			rs = stat.executeQuery(res);
		}catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}
	// 회원 주소 변경
  	public void updateMemberAdd(String res) {
  		try {
  			conn=getConnection();
			stat=getStatement();
			rs = stat.executeQuery(res);

  		}catch (SQLException e) {
  			System.out.println("오라클 연결 실패");
  		}
  	}
  	// 회원 비밀번호 변경
    public void updateMemberPW(String res) {
    	try {
    		conn=getConnection();
			stat=getStatement();
    		rs = stat.executeQuery(res);

    	}catch (SQLException e) {
    		System.out.println("오라클 연결 실패");
      	}
    }
}
