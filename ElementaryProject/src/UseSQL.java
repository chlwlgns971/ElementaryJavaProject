import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseSQL { //아이디와 비밀번호 체크
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "testID";
	String password = "java";
	Connection conn=null;
	Statement stat = null;
	ResultSet rs=null;
	public boolean checkIDandPW(String userID,String userPW) {
		boolean check=true;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String ID="";
			String PW="";
			rs=stat.executeQuery("SELECT COUNT(MEM_ID) FROM MEMBER WHERE MEM_ID='"+userID+"'");
			while(rs.next()) {
				ID=rs.getString(1);
			}
			if(ID.equals("0")) {
				check=false;
			}
			else {
				rs=stat.executeQuery("SELECT MEM_PW FROM MEMBER WHERE MEM_ID='"+userID+"'");
				while(rs.next()) {
					PW=rs.getString(1);
				}
				if(PW.equals(userPW)) check=true;
				else check=false;
			}
		}	
		catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			try {
				if(rs !=null) rs.close();
				if(stat !=null) stat.close();
				if(conn !=null) conn.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	public void delete(String userID) {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=stat.executeQuery("DELETE FROM MEMBER WHERE MEM_ID='"+userID+"'");
			System.out.println("회원탈퇴에 성공하였습니다.");
		}	
		catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} 
		finally {
			try {
				if(rs !=null) rs.close();
				if(stat !=null) stat.close();
				if(conn !=null) conn.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
}
