import java.util.Scanner;

public class SetMemInfo { //회원가입 클래스 
	private String userID; //아이디
	private String passWord; //비밀번호
	private String name; //이름
	private String regNo1; //주민등록번호 앞자리
	private String addr; //주소
	private String userPN; //휴대폰번호
	Scanner sc=new Scanner(System.in);
	String str="";
	DBConnect dbc=new DBConnect();
	SetMemInfo(){
		setID();
		setPW();
		setName();
		setRegNo1();
		setAddr();
		setPN();
		dbc.signUp(this.userID, this.passWord, this.name, this.regNo1, this.addr, this.userPN);
	}
	public void setID() {
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
				if(dbc.checkID(str)) {
					this.userID=str;
					str="";
					break;
				}
				else System.out.println("중복된 아이디입니다.");
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
}
