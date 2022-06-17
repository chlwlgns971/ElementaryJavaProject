package Controller;
import java.util.Scanner;

import DAO.LoginDAO;

public class LoginUI {
	private String userID;
	String id;
	String pw;
	Scanner sc=new Scanner(System.in);
	LoginDAO login=new LoginDAO();
	public void login() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		id=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(id.equals("admin")) {
			if(login.checkIDandPW(id,pw)) {
				this.userID=id;
				System.out.println("관리자 로그인에 성공하였습니다.");
				ManageMenu mm=new ManageMenu();
				mm.manageMenu();
			}
			else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		else if(login.checkIDandPW(id,pw)) {
			this.userID=id;
			System.out.println("로그인에 성공하였습니다.");
			UserPage up=new UserPage(this.userID);
			up.userPage();
		}
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
}
