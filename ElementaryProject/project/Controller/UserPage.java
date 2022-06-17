package Controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import DAO.UserPageDAO;
import DTO.UserPageDTO;

public class UserPage {
	String selectMenu;
	Scanner sc=new Scanner(System.in);
	UserPageDAO userDao=new UserPageDAO();
	UserPageDTO userDto;
	int num;
	private String userID;
	UserPage(String id){
		this.userID=id;
	}
	public void userPage() {
		Map<String, String> rsvResult= new HashMap<>();
		userDto=new UserPageDTO(this.userID);
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
					rsvResult=userDao.searchRsv(userDto.getLoginID());
					System.out.println("===================================================================================");
					System.out.println("   예약번호      이름     차번호    대여차종   지점   결제금액      대여일         반납일");
					System.out.println("===================================================================================");
					Set<String> result=rsvResult.keySet();
					Iterator<String> iter=result.iterator();
					while(iter.hasNext()) {
						String key=iter.next();
						String value=rsvResult.get(key);
						System.out.println(key+value);
					}
				}
				else if(num==3) {
					editUserInfo(userDto.getLoginID());
				}
				else if(num==4) {
					if(deleteAccount(userDto.getLoginID())) break;
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
	public boolean deleteAccount(String id) {
		boolean checkDelete=true;
		String pw="";
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw=sc.nextLine();
		System.out.println("==================================================");
		if(userDao.checkIDandPW(id,pw)) {
			check:while(true) {
				System.out.println("정말 삭제하시겠습니까? (y/n)");
				String str=sc.nextLine();
				str=str.replaceAll("\\s", "");//공백제거
				if (str.equals("y")) {
					userDao.delete(this.userID);
					System.out.println("삭제가 완료되었습니다. 메인화면으로 돌아갑니다.");
					this.userID="";
					checkDelete=true;
					break check;
				}
				else if(str.equals("n")) {
					checkDelete=false;
					break check;
				}
				else System.out.println("올바른 값을 입력해주세요.");
			}
		}
		else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			userPage();
		}
		return checkDelete;
	}
	public void editUserInfo(String id) {
		String select="";
		int num=0;
		String res="";
		System.out.println("==================================================");
		System.out.println("변경할 항목을 선택해주세요.");
		System.out.println("1.핸드폰번호  2.주소  3.비밀번호");
		System.out.println("==================================================");
		select=sc.nextLine();
		try {
			num=Integer.parseInt(selectMenu);
			if(num==1) {
				res = "update member set mem_hp = '" + y + "' where mem_id = '" +id+ "'";
			    userDao.updateMemberHP(res);
			    System.out.println("변경이 완료되었습니다.");
			}
			else if(num==2) {
				res = "update member set mem_add = '" + y + "' where mem_id = '" +id+ "'";
			    userDao.updateMemberAdd(res);
			    System.out.println("변경이 완료되었습니다.");
			}
			else if(num==3) {
				res = "update member set mem_pw = '" + y + "' where mem_id = '" +id+ "'";
			    userDao.updateMemberPW(res);
			    System.out.println("변경이 완료되었습니다.");
			}
			else System.out.println("올바른메뉴를 선택해주세요");
		}
		catch (Exception e) {
			System.out.println("올바른 값을 입력해주세요");
		}
	}
}
