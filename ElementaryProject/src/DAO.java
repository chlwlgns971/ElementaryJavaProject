import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			System.out.println("클래스 로드 실패:"+e.getMessage());
		}
	}
	//Singleton 클래스로 만들기 위해서 생성자를 private로 선언
	private DAO() {};
	//하나의 객체를 주소로 저장할 변수 선언
	private static DAO obj;
	//선언한 static변수에 객체를 생성해주는 메서드 선언
	public static DAO sharedInstance() {
		if(obj==null) {
			obj=new DAO();
		}
		return obj;
	}
	
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private String url = "jdbc:oracle:thin:@192.168.143.25:1521:xe";
	private String user = "RENTCAR";
	private String password = "java";
	
	//데이터베이스 연결을 수행해주는 메서드
	private Connection getConnection() {
		try {
			conn=DriverManager.getConnection(this.url, this.user, this.password);
		}catch(Exception e) {
			System.out.println("연결실패:"+e.getMessage());
		}
		return conn;
	}
	//데이터베이스 연결을 해제하는 메서드
	private void close() {
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
