package Controller;

import java.util.Scanner;

public class ManageMenu {
	Scanner sc = new Scanner(System.in);
	int num = 0;
	String str = "";
	public void manageMenu() {
		while(true)
		{
			System.out.println("==============================================");
			System.out.println("=================관리자 페이지===================");
			System.out.println("1. 조회  2. 수정  3. 로그아웃");
			System.out.println("==============================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			ManageSelect ms=new ManageSelect();
			ManageUpdate mu=new ManageUpdate();
			try {
				num = Integer.parseInt(str);
				if (num == 1) {
					ms.ShowManagerSelect();
	
				} else if (num == 2) {
					mu.ShowManagerUpdate();
				} else if (num == 3) {
					System.out.println("프로그램을 종료합니다.");
					break;
	
				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");
	
			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

}
