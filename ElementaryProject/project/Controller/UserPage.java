package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import DAO.UserPageDAO;
import DTO.UserPageDTO;

public class UserPage {
	String selectMenu;
	LocalDate now = LocalDate.now();
	Scanner sc = new Scanner(System.in);
	UserPageDAO userDao = new UserPageDAO();
	UserPageDTO userDto;
	int num;
	private String userID;

	UserPage(String id) {
		this.userID = id;
	}

	public void userPage() {
		Map<String, String> rsvResult = new HashMap<>();
		userDto = new UserPageDTO(this.userID);
		while (true) {
			sc.nextLine();
			System.out.println("=================================================================");
			System.out.println("1:예약 2:나의 예약조회 3:회원정보수정 4:회원탈퇴 5:메인화면으로 돌아가기(로그아웃)");
			System.out.println("=================================================================");
			selectMenu = sc.nextLine();
			try {
				num = Integer.parseInt(selectMenu);
				if (num == 1) {
					makeRsv();
				} else if (num == 2) {
					String select = "";// 메뉴선택용 변수
					int selectNum = 0;// select변수 정수변환용
					rsvResult = userDao.searchRsv(userDto.getLoginID());
					System.out.println(
							"========================================================================================================");
					System.out.println("예약번호\t\t|이름\t|차번호\t\t|대여차종\t|지점\t|결제금액\t\t|대여일\t\t|반납일\t\t|");
					System.out.println(
							"========================================================================================================");
					Set<String> result = rsvResult.keySet();
					Iterator<String> iter = result.iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						String value = rsvResult.get(key);
						System.out.println(key + value);
					}
					System.out.println("1.삭제 2.확인 ");
					select = sc.nextLine();
					try {
						selectNum = Integer.parseInt(select);
						switch (selectNum) {
						case 1:
							if (rsvResult.size() == 0) {
								System.out.println("예약내역이 없습니다.");
								break;
							} else {
								while (iter.hasNext()) {
									String key = iter.next();
									String value = rsvResult.get(key);
									System.out.println(key + value);
								}
								while (true) {
									System.out.println("삭제할 예약코드를 입력해주세요:");
									select = sc.nextLine();
									if (rsvResult.get(select) == "") {
										System.out.println("잘못된 예약번호입니다.");
									} else {
										userDao.deleteRsv(select);
										break;
									}
								}
								break;
							}
						case 2:
							break;
						}
					} catch (Exception e) {
						System.out.println("올바르지 않은 값입니다.");
					}
				} else if (num == 3) {
					editUserInfo(userDto.getLoginID());
				} else if (num == 4) {
					if (deleteAccount(userDto.getLoginID()))
						break;
				} else if (num == 5) {
					this.userID = "";
					break;
				} else
					System.out.println("올바른 메뉴번호가 아닙니다.");
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요");
			}
		}
	}

	public boolean deleteAccount(String id) {
		boolean checkDelete = true;
		String pw = "";
		System.out.println("==================================================");
		System.out.println("비밀번호를 입력하세요: ");
		pw = sc.nextLine();
		System.out.println("==================================================");
		if (userDao.checkIDandPW(id, pw)) {
			check: while (true) {
				System.out.println("정말 삭제하시겠습니까? (y/n)");
				String str = sc.nextLine();
				str = str.replaceAll("\\s", "");// 공백제거
				if (str.equals("y")) {
					userDao.delete(id);
					System.out.println("삭제가 완료되었습니다. 메인화면으로 돌아갑니다.");
					this.userID = "";
					checkDelete = true;
					break check;
				} else if (str.equals("n")) {
					checkDelete = false;
					break check;
				} else
					System.out.println("올바른 값을 입력해주세요.");
			}
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			userPage();
		}
		return checkDelete;
	}

	public void editUserInfo(String id) {
		String select = "";
		int num = 0;
		String res = "";
		String edit = "";
		System.out.println("==================================================");
		System.out.println("변경할 항목을 선택해주세요.");
		System.out.println("1.핸드폰번호  2.주소  3.비밀번호  4.뒤로가기");
		System.out.println("==================================================");
		select = sc.nextLine();
		try {
			num = Integer.parseInt(select);
			if (num == 1) {
				System.out.print("바꿀 핸드폰 번호를 입력해주세요(000-0000-0000 형태):");
				edit = sc.nextLine();
				res = "update member set mem_hp = '" + edit + "' where mem_id = '" + id + "'";
				userDao.editUserInfo(res);
				System.out.println("변경이 완료되었습니다.");
				edit = "";
				res = "";
			} else if (num == 2) {
				System.out.print("바꿀 주소를 입력해주세요:");
				edit = sc.nextLine();
				res = "update member set mem_add = '" + edit + "' where mem_id = '" + id + "'";
				userDao.editUserInfo(res);
				System.out.println("변경이 완료되었습니다.");
				edit = "";
				res = "";
			} else if (num == 3) {
				System.out.print("바꿀 비밀번호를 입력해주세요:");
				edit = sc.nextLine();
				res = "update member set mem_pw = '" + edit + "' where mem_id = '" + id + "'";
				userDao.editUserInfo(res);
				System.out.println("변경이 완료되었습니다.");
				edit = "";
				res = "";
			}else if(num==4) {
				
			}
			else
				System.out.println("올바른메뉴를 선택해주세요");
		} catch (Exception e) {
			System.out.println("올바른 값을 입력해주세요");
		}
	}

	public void makeRsv() {
		Map<String, String> map = new HashMap<String, String>();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		String regno="";
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		while(true) {
			System.out.println("지역번호를 입력하세요(02: 서울, 042: 대전, 032: 인천, 053: 대구, 062: 광주, 052: 울산, 051: 부산, 064: 제주 ");
			System.out.print(">>");
			regno = sc.next();
			if(regno.equals("02") || regno.equals("042")||regno.equals("032")|| regno.equals("053")|| regno.equals("062")
					|| regno.equals("052")|| regno.equals("051")|| regno.equals("064")) {
				break;
			}
			else {
				System.out.println("올바른 지역번호를 입력해주세요.");
			}
		}
		while(true) {
			System.out.print("대여 달을 입력하세요 ");
			a = sc.nextInt();
			if((a>12 || a<1)||(a<monthValue)){
				System.out.println("잘못된 값입니다.");
			}
			else break;
		}
		while(true) {
			System.out.print("대여 일을 입력하세요 ");
			b = sc.nextInt();
			if(b>31 || b<1) {
				System.out.println("잘못된 값입니다.");
			}
			
			else {
				if(a==monthValue) {
					if(b<dayOfMonth) {
						System.out.println("잘못된 값입니다.");
					}
					else break;
				}
			}
		}
		while(true) {
			System.out.print("반납할 딜을 입력하세요 ");
			c = sc.nextInt();
			if((c>12 || c<1)||(c<a)) {
				System.out.println("잘못된 값입니다.");
			}
			else break;
		}
		while(true) {
			System.out.print("반납 일을 입력하세요 ");
			d = sc.nextInt();
			if((d>31 || d<1)||(d<b)) {
				System.out.println("잘못된 값입니다.");
			}
			else break;
		}
		map = userDao.chkRsv(regno, a, b, c, d);

		System.out.println("===================================================================================");
		System.out.println("1.차량번호  2.차량분류 3.대여료 4.차량 색상 5.지점번호 6. 모델명");
		System.out.println("===================================================================================");

		// 정렬을 위함)
		List<String> keySetList = new ArrayList<>(map.keySet());
		Collections.sort(keySetList);

		// Map<String, String> rsvResult= new HashMap<>();
		for (String key : keySetList) {
			String value = map.get(key);

			System.out.println(key + " | " + value);
		}
		System.out.println("===================================================================================");

		System.out.println("===================================================================================");
		System.out.println("예약하실 차량번호를 입력하세요");
		System.out.print(">> ");
		String rcarNo = sc.next();
		System.out.println("===================================================================================");
		String memId = userDto.getLoginID();
		userDao.selRsCar(regno, rcarNo, memId, a, b, c, d);

	}

}
