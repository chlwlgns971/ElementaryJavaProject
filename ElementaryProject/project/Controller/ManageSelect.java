package Controller;

import java.util.Scanner;

import DAO.ManageDAO;

public class ManageSelect {
	ManageDAO manageDao = new ManageDAO();
	Scanner sc = new Scanner(System.in);
	int num = 0;
	String str = "";

	public void ShowManagerSelect() {
		while (true) {
			System.out.println("===================================================================");
			System.out.println("=============================관리자 페이지============================");
			System.out.println("1.회원 조회 2.지점 조회 3.차량 조회 4.모델 조회 5.예약 조회 6.뒤로가기");
			System.out.println("===================================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);
				if (num == 1) {
					ManagerMemberShowInfo();
				} else if (num == 2) {
					ManagerBranchShowInfo();
				} else if (num == 3) {
					ManagerCarShowInfo();
				} else if (num == 4) {
					ManagerModelShowInfo();
				} else if (num == 5) {
					ManagerRSVShowInfo();
				} else if (num == 6) {
					System.out.println("프로그램을 종료합니다.");
					break;

				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");

			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

	public void ManagerMemberShowInfo() {
		while (true) {
			System.out.println("====================================================");
			System.out.println("=================회원테이블 조회 페이지===================");
			System.out.println("1. 회원 전체 조회  2. 회원 선택 조회  3. 뒤로가기");
			System.out.println("====================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("==================================================");
					System.out.println("회원ID 회원PW 회원명 생년월일         주소               핸드폰번호");
					System.out.println("==================================================");
					String str = "select * from member where mem_id != 'admin'";
					manageDao.selectMember(str);

					
				} else if (num == 2) {
					System.out.println("이름을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("==================================================");
					System.out.println("회원ID 회원PW 회원명 생년월일         주소               핸드폰번호");
					System.out.println("==================================================");
					String str = "select* from MEMBER WHERE MEM_NAME='" + name + "'";
					manageDao.selectMember(str);

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

	public void ManagerBranchShowInfo() {
		while (true) {
			System.out.println("====================================================");
			System.out.println("=================지점테이블 조회 페이지===================");
			System.out.println("1. 지점 전체 조회  2. 지점 선택 조회  3. 뒤로가기");
			System.out.println("====================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("=====================================================");
					System.out.println("번호 지점명               주소                 담당자         전화번호");
					System.out.println("=====================================================");
					String str = "select* from BRANCH";
					manageDao.selectBra(str);

				} else if (num == 2) {
					System.out.println("지점명을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("=====================================================");
					System.out.println("번호 지점명               주소                 담당자         전화번호");
					System.out.println("=====================================================");
					String str = "select* from BRANCH WHERE BRA_NAME='" + name + "'";
					manageDao.selectBra(str);

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

	public void ManagerCarShowInfo() {
		while (true) {
			System.out.println("=======================================");
			System.out.println("===========차량테이블 조회 페이지=============");
			System.out.println("1. 전체 차량 조회  2. 지점별 차량 조회  3. 돌아가기  ");
			System.out.println("=======================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("===============================");
					System.out.println("  차량번호   차종  대여료  색상 번호 차량명");
					System.out.println("===============================");
					String str = "select* from CAR";
					manageDao.selectCar(str);

				} else if (num == 2){
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					System.out.println("===============================");
					System.out.println("지점번호 차량번호  차종  대여료  색상 차량명");
					System.out.println("===============================");
					String str = "select  bra_no, car_no, car_cls, car_pay, car_col, car_name "
							+ "from branch, car where bra_no = car_bno and bra_no = '" + no + "'";
					manageDao.selectCar2(str);
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

	public void ManagerModelShowInfo() {
		while (true) {
			System.out.println("====================================================");
			System.out.println("=================모델테이블 조회 페이지===================");
			System.out.println("1. 모델 전체 조회  2. 모델 선택 조회  3. 뒤로가기");
			System.out.println("====================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("============================");
					System.out.println("모델명   제조사      연료    배기량  인승");
					System.out.println("============================");
					String str = "select* from MODEL";
					manageDao.selectMod(str);

				} else if (num == 2) {
					System.out.println("모델명을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("============================");
					System.out.println("모델명   제조사      연료    배기량  인승");
					System.out.println("============================");
					String str = "select* from MODEL WHERE MOD_NAME='" + name + "'";
					manageDao.selectMod(str);

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

	public void ManagerRSVShowInfo() {
		while (true) {
			System.out.println("====================================================");
			System.out.println("=================예약테이블 조회 페이지===================");
			System.out.println("1. 예약 전체 조회  2. 예약 선택 조회  3. 뒤로가기");
			System.out.println("====================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("==========================================================");
					System.out.println("      예약일            반납일        결제금액 번호   차량번호  아이디       예약번호");
					System.out.println("==========================================================");
					String str = "SELECT TO_CHAR(RSV_DATE,'YYYY-MM-DD'), "
							+ "TO_CHAR(RSV_RET,'YYYY-MM-DD'), RSV_PAY, RSV_BNO," + "RSV_CNO, RSV_ID, RSV_NO FROM RSV";
							

					manageDao.selectRsv(str);

				} else if (num == 2) {
					System.out.println("아이디를 입력하세요.");
					String name = sc.nextLine();
					System.out.println("==========================================================");
					System.out.println(" 예약일       반납일     결제금액  번호   차량번호  아이디   예약번호");
					System.out.println("==========================================================");
					String str = "SELECT TO_CHAR(RSV_DATE,'YYYY-MM-DD'), "
							+ "TO_CHAR(RSV_RET,'YYYY-MM-DD'), RSV_PAY, RSV_BNO,"
							+ "RSV_CNO, RSV_ID, RSV_NO from RSV WHERE RSV_ID='" + name + "'";
					manageDao.selectRsv(str);

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
