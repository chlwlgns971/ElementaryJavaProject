package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
//	//Singleton 클래스로 만들기 위해서 생성자를 private로 선언
//	private DBConnection() {};
//	//하나의 객체를 주소로 저장할 변수 선언
//	private static DBConnection obj;
//	//선언한 static변수에 객체를 생성해주는 메서드 선언
//	public static DBConnection sharedInstance() {
//		if(obj==null) {
//			obj=new DBConnection();
//		}
//		return obj;
//	}
	
	//데이터베이스 연결을 수행해주는 메서드
	public static Connection getConnection() {
		Connection conn=null;
		String url = "jdbc:oracle:thin:@192.168.143.25:1521:xe";
		String user = "RENTCAR";
		String password = "java";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			System.out.println("클래스 로드 실패:"+e.getMessage());
		}
		try {
			conn=DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			System.out.println("연결실패:"+e.getMessage());
		}
		return conn;
	}
	public static Statement getStatement() {
		Statement stat=null;
		try {
			Connection conn=getConnection();
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			System.out.println("연결실패:"+e.getMessage());
		}
		return stat;
	}
	//데이터베이스 연결을 해제하는 메서드
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		try {
			if(rs!=null) rs.close();
			if(stat!=null) stat.close();
			if(conn!=null)conn.close();
		}
		catch(Exception e) {
			System.out.println("해제 실패:"+e.getMessage());
		}
	}
}
