import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SignUp { //회원가입 클래스 
	private String userID; //아이디
	private String passWord; //비밀번호
	private String name; //이름
	private String regNo1; //주민등록번호 앞자리
	private String addr; //주소
	private String userPN; //휴대폰번호
	Scanner sc=new Scanner(System.in);
	String str="";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "testID";
	String password = "java";
	Connection conn=null;
	Statement stat = null;
	ResultSet rs=null;
	SignUp(){
		setID();
		setPW();
		setName();
		setRegNo1();
		setAddr();
		setPN();
		sendToSQL();
	}
	public void setID() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			while(true) {
				System.out.println("==================================================");
				System.out.println("(아이디는 공백이 허용되지 않으며 최대 10글자만 입력가능합니다.)");
				System.out.print("(필수)가입할 아이디를 입력해주세요: ");
				str=sc.nextLine();
				str=str.replaceAll("\\s", ""); //공백제거
				if(str.equals("")) {
					System.out.println("아이디는 공백일 수 없습니다.");
					str="";
				}
				else if(str.length()>10) {
					System.out.println("10글자를 초과하였습니다.");
					str="";
				}
				else {
					String count="";
					rs=stat.executeQuery("SELECT COUNT(MEM_ID) FROM MEMBER WHERE MEM_ID='"+str+"'");
					while(rs.next()) {
						count=rs.getString(1);
					}
					if(count.equals("0")) {
						this.userID=str;
						str="";
						break;
					}
					else System.out.println("중복된 아이디입니다.");
					
				}
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
		
	}
	public void setPW() {
		while(true) {
			System.out.println("==================================================");
			System.out.println("(비밀번호는 공백이 허용되지 않으며 최대 10글자만 입력가능합니다.)");
			System.out.print("(필수)비밀번호를 입력해주세요: ");
			str=sc.nextLine();
			str=str.replaceAll("\\s", "");
			if(str.equals("")) {
				System.out.println("비밀번호는 공백일 수 없습니다.");
				str="";
			}
			else if(str.length()>10) {
				System.out.println("10글자를 초과하였습니다.");
				str="";
			}
			else {
				this.passWord=str;
				str="";
				break;
			}
		}
	}
	public void setName() {
		while(true) {
			System.out.println("==================================================");
			System.out.print("(필수)이름을 입력해주세요: ");
			str=sc.nextLine();
			str=str.replaceAll("\\s", "");
			if(str.equals("")) {
				System.out.println("이름은 공백일 수 없습니다.");
				str="";
			}
			else {
				this.name=str;
				str="";
				break;
			}
		}
	}
	public void setRegNo1() {
		while(true) {
			System.out.println("==================================================");
			System.out.print("(필수)주민번호 앞자리를 입력해주세요(6자리): ");
			str=sc.nextLine();
			str=str.replaceAll("\\s", "");
			if(str.equals("")) {
				System.out.println("주민번호는 공백일 수 없습니다.");
				str="";
			}
			else if(str.length()!=6) {
				System.out.println("잘못된 입력입니다.");
				str="";
			}
			else {
				this.regNo1=str;
				str="";
				break;
			}
		}
	}
	public void setPN() {
		while(true) {
			System.out.println("==================================================");
			System.out.print("(필수)휴대폰 번호를 입력하세요: ");
			str=sc.nextLine();
			str=str.replaceAll("\\s", "");
			if(str.equals("")) {
				System.out.println("휴대폰 번호는 공백일 수 없습니다.");
				str="";
			}
			else {
				this.userPN=str;
				str="";
				break;
			}
		}
	}
	public void setAddr() {
		System.out.println("==================================================");
		System.out.print("(선택)집주소를 입력하세요: ");
		str=sc.nextLine();
		if(str.equals("")) this.addr="";
		else {
			this.addr=str;
			str="";
		}
	}
	public void sendToSQL() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=stat.executeQuery("INSERT INTO MEMBER VALUES('"+this.userID+"','"+this.passWord+"','"+this.name+"'"
					+ ",'"+this.regNo1+"','"+this.addr+"','"+this.userPN+"')");
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
}
