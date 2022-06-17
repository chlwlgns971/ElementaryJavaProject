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
					String select="";// 메뉴선택용 변수
					int selectNum=0;//select변수 정수변환용
					rsvResult=userDao.searchRsv(userDto.getLoginID());
					System.out.println("========================================================================================================");
					System.out.println("예약번호\t\t|이름\t|차번호\t\t|대여차종\t|지점\t|결제금액\t\t|대여일\t\t|반납일\t\t|");
					System.out.println("========================================================================================================");
					Set<String> result=rsvResult.keySet();
					Iterator<String> iter=result.iterator();
					while(iter.hasNext()) {
						String key=iter.next();
						String value=rsvResult.get(key);
						System.out.println(key+value);
					}
					System.out.println("1.삭제 2.확인 ");
					select=sc.nextLine();
					try {
						selectNum=Integer.parseInt(select);
						switch(selectNum) {
						case 1:
								if(rsvResult.size()==0) {
									System.out.println("예약내역이 없습니다.");
									break;
								}
								else {
									while(iter.hasNext()) {
										String key=iter.next();
										String value=rsvResult.get(key);
										System.out.println(key+value);
									}
									while(true) {
										System.out.println("삭제할 예약코드를 입력해주세요:");
										select=sc.nextLine();
										if(rsvResult.get(select)=="") {
											System.out.println("잘못된 예약번호입니다.");
										}
										else {
											userDao.deleteRsv(select);
											break;
										}
									}
									break;
								}
						case 2:
							break;
						}	
					}catch(Exception e) {
						System.out.println("올바르지 않은 값입니다.");
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
		String edit="";
		System.out.println("==================================================");
		System.out.println("변경할 항목을 선택해주세요.");
		System.out.println("1.핸드폰번호  2.주소  3.비밀번호");
		System.out.println("==================================================");
		select=sc.nextLine();
		try {
			num=Integer.parseInt(select);
			if(num==1) {
				System.out.print("바꿀 핸드폰 번호를 입력해주세요(000-0000-0000 형태):");
				edit=sc.nextLine();
				res = "update member set mem_hp = '" + edit + "' where mem_id = '" +id+ "'";
			    userDao.editUserInfo(res);
			    System.out.println("변경이 완료되었습니다.");
			    edit="";
			    res="";
			}
			else if(num==2) {
				System.out.print("바꿀 주소를 입력해주세요:");
				edit=sc.nextLine();
				res = "update member set mem_add = '" + edit + "' where mem_id = '" +id+ "'";
			    userDao.editUserInfo(res);
			    System.out.println("변경이 완료되었습니다.");
			    edit="";
			    res="";
			}
			else if(num==3) {
				System.out.print("바꿀 비밀번호를 입력해주세요:");
				edit=sc.nextLine();
				res = "update member set mem_pw = '" + edit + "' where mem_id = '" +id+ "'";
			    userDao.editUserInfo(res);
			    System.out.println("변경이 완료되었습니다.");
			    edit="";
			    res="";
			}
			else System.out.println("올바른메뉴를 선택해주세요");
		}
		catch (Exception e) {
			System.out.println("올바른 값을 입력해주세요");
		}
	}
}
