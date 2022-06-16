import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DBConnect { //아이디와 비밀번호 체크
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.143.25:1521:xe";
	String user = "RENTCAR";
	String password = "java";
	Connection conn=null;
	Statement stat = null;
	ResultSet rs=null;
	boolean check=true;
	public boolean checkID(String userID) {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
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
	public boolean checkIDandPW(String userID,String userPW) {
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
	public void signUp(String userID, String passWord, String name, String regNo1, String addr, String userPN) {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=stat.executeQuery("INSERT INTO MEMBER VALUES('"+userID+"','"+passWord+"','"+name+"'"
					+ ",'"+regNo1+"','"+addr+"','"+userPN+"')");
			System.out.println("회원가입이 완료되었습니다.");
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
	public void searchRsv(String id) {
		Map<Integer, String> resultMap= new HashMap<>();
		Map<Integer, String> rsvNO= new HashMap<>();
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=stat.executeQuery("SELECT RSV_NO, REPLACE(RSV_ID,'"+id+"',(SELECT MEM_NAME FROM MEMBER WHERE MEM_ID='"+id+"')), RSV_CNO"
					+ ", CAR_NAME,REPLACE(RSV_BNO, RSV_BNO, (SELECT BRA_NAME FROM BRANCH INNER JOIN RSV ON(RSV_BNO=BRA_NO) WHERE RSV_ID='"+id+"'))"
							+ ", RSV_PAY, TO_CHAR(RSV_DATE,'YYYY-MM-DD'), TO_CHAR(RSV_RET,'YYYY-MM-DD') FROM RSV INNER JOIN CAR ON(CAR_NO=RSV_CNO) WHERE RSV_ID='"+id+"'"
									+ "ORDER BY RSV_NO DESC");
			//column갯수 구하기
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int num=1; //hashmap에 넣을 키 값
			String str=""; //hashmap에 넣을 출력값
			System.out.println("===================================================================================");
			System.out.println("번호    예약번호      이름     차번호    대여차종   지점   결제금액      대여일         반납일");
			System.out.println("===================================================================================");
			while (rs.next()) {
				for(int i=1; i<=columnCount; i++) {
					str+=rs.getString(i)+" | ";
				}
				resultMap.put(num, str);
				rsvNO.put(num, rs.getString("RSV_NO"));
				num++;
				str="";
			}
			Set<Integer> result=resultMap.keySet();
			Iterator<Integer> iter=result.iterator();
			while(iter.hasNext()) {
				int no=iter.next().intValue();
				String value=resultMap.get(no);
				System.out.println(no+" | "+value);
			}
//			while(true) {
//				System.out.println("===================================================================================");
//				System.out.println("1. 메뉴로 돌아가기    2. 수정");
//			}
			
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
