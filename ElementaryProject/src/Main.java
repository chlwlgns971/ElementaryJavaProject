import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=0;
		String str="";
		while(true) {
			System.out.println("==================================================");
			System.out.println("메뉴 진입을 위한 번호를 입력하세요.");
			System.out.println("1:로그인 2:회원가입 3:프로그램 종료");
			System.out.println("==================================================");
			str=sc.nextLine();
			str=str.replaceAll("\\s", "");
			ShowMenu showMenu=new ShowMenu();
			try {
				num=Integer.parseInt(str);
				if(num==1) {
					showMenu.login();
				}
				else if(num==2) {
					SetMemInfo su=new SetMemInfo();
				}
				else if(num==3) {
					System.out.println("프로그램을 종료합니다.");
					break;
				}
				else System.out.println("올바른 메뉴 번호가 아닙니다.");
				
			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		
		}	
	}
}