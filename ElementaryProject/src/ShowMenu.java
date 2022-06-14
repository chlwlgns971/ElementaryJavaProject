import java.util.Scanner;

public class ShowMenu {
	private String userID;
	private String userPW;
	String id;
	String pw;
	Scanner sc=new Scanner(System.in);
	DBConnect dbc=new DBConnect();
	public void deleteAccount() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		id=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(dbc.checkIDandPW(id,pw)) dbc.delete(id);
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
	public void login() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		id=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(dbc.checkIDandPW(id,pw)) System.out.println("로그인에 성공하였습니다.");
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
	public void userMenu() {
		System.out.println("==================================================");
		System.out.println("1:예약 2:나의 예약조회 3:회원정보수정 4:메인화면으로 돌아가기(로그아웃)");
		System.out.println("==================================================");
	}
}
