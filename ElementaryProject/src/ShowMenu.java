import java.util.Scanner;

public class ShowMenu {
	String userID;
	String userPW;
	Scanner sc=new Scanner(System.in);
	UseSQL usesql=new UseSQL();
	public void deleteAccount() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		userID=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		userPW=sc.nextLine();
		System.out.println("==================================================");
		if(usesql.checkIDandPW(userID,userPW)) usesql.delete(userID);
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
	public void login() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		userID=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		userPW=sc.nextLine();
		System.out.println("==================================================");
		if(usesql.checkIDandPW(userID,userPW)) System.out.println("로그인에 성공하였습니다.");
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
}
