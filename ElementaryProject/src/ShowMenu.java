import java.util.Scanner;

public class ShowMenu {
	private String userID;
	String id;
	String pw;
	Scanner sc=new Scanner(System.in);
	DBConnect dbc=new DBConnect();
	String selectMenu;
	int num;
	//Main main=new Main();
	public void deleteAccount() {
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(dbc.checkIDandPW(this.userID,pw)) {
			check:while(true) {
				System.out.println("정말 삭제하시겠습니까? (y/n)");
				String str=sc.nextLine();
				str=str.replaceAll("\\s", "");//공백제거
				if (str.equals("y")) {
					dbc.delete(id);
					System.out.println("삭제가 완료되었습니다. 메인화면으로 돌아갑니다.");
					this.userID="";
					break check;
				}
				else if(str.equals("n")) break check;
				else System.out.println("올바른 값을 입력해주세요.");
			}
			userPage();
		}
		else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			userPage();
		}
	}
	public void login() {
		System.out.println("==================================================");
		System.out.print("아이디를 입력하세요: ");
		id=sc.nextLine();
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(id.equals("admin")) {
			if(dbc.checkIDandPW(id,pw)) {
				this.userID=id;
				System.out.println("관리자 로그인에 성공하였습니다.");
				//userPage();
			}
			else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		else if(dbc.checkIDandPW(id,pw)) {
			this.userID=id;
			System.out.println("로그인에 성공하였습니다.");
			userPage();
		}
		else System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
	}
	public void userPage() {
		while(true) {
			System.out.println("=================================================================");
			System.out.println("1:예약 2:나의 예약조회 3:회원정보수정 4:회원탈퇴 5:메인화면으로 돌아가기(로그아웃)");
			System.out.println("=================================================================");
			selectMenu=sc.nextLine();
			try {
				num=Integer.parseInt(selectMenu);
				if(num==1) {
					//예약메소드 필요
				}
				else if(num==2) {
					dbc.searchRsv(this.userID);
				}
				else if(num==4) {
					num=0;
					deleteAccount();
					break;
				}
				else if(num==5) {
					this.userID="";
					break;
				}
				else System.out.println("올바른 메뉴번호가 아닙니다.");
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요");
			}
		}
	}
}
