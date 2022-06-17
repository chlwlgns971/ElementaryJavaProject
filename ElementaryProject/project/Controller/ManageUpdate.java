package Controller;

import java.util.Scanner;

import DAO.ManageDAO;

public class ManageUpdate {
	Scanner sc = new Scanner(System.in);
	ManageDAO manageDao = new ManageDAO();
	int num = 0;
	String str = "";
	public void ShowManagerUpdate() {
		while (true) {
			System.out.println("===================================================================");
			System.out.println("=============================관리자 페이지============================");
			System.out.println("1.회원테이블 수정 2.지점테이블 수정 3.차량테이블 수정 4.모델테이블 수정 5.뒤로가기");
			System.out.println("===================================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);
				if (num == 1) {
					ManagerMemberUpdateInfo();
				} else if (num == 2) {
					ManagerBranchUpdateInfo();
				} else if (num == 3) {
					ManagerCarUpdateInfo();
				} else if (num == 4) {
					ManagerModelUpdateInfo();
				} else if (num == 5) {
					break;

				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");

			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

	public void ManagerMemberUpdateInfo() {
		while (true) {
			System.out.println("====================================================");
			System.out.println("=================회원테이블 수정 페이지===================");
			System.out.println("1. 핸드폰번호 변경  2. 주소 변경  3. 비밀번호 변경 4. 뒤로가기");
			System.out.println("====================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("아이디를 입력하세요.");
					String id = sc.nextLine();
					System.out.println("변경할 핸드폰번호를 입력하세요.");
					String hp = sc.nextLine();
					str = "update member set mem_hp = '" + hp + "' where mem_id = '" + id + "'";
					manageDao.update(str);
					System.out.println("핸드폰 번호가 변경되었습니다.");

				} else if (num == 2) {
					System.out.println("아이디를 입력하세요.");
					String id = sc.nextLine();
					System.out.println("변경할 주소를 입력하세요.");
					String add = sc.nextLine();
					str = "update member set mem_add = '" + add + "' where mem_id = '" + id + "'";
					manageDao.update(str);
					System.out.println("주소가 변경되었습니다.");

				} else if (num == 3) {
					System.out.println("아이디를 입력하세요.");
					String id = sc.nextLine();
					System.out.println("변경할 비밀번호를 입력하세요.");
					String pw = sc.nextLine();
					str = "update member set mem_pw = '" + pw + "' where mem_id = '" + id + "'";
					manageDao.update(str);
					System.out.println("비밀번호가 변경되었습니다.");

				} else if (num == 4) {
					break;

				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");

			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

	public void ManagerBranchUpdateInfo() {
		while (true) {
			System.out.println("======================================================================");
			System.out.println("==========================지점테이블 수정 페이지============================");
			System.out.println("1.주소 변경  2.관리자 변경  3.전화번호 변경   4.컬럼 삽입    5.컬럼 삭제   6.뒤로가기");
			System.out.println("======================================================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					System.out.println("변경할 주소를 입력하세요.");
					String add = sc.nextLine();
					str = "update branch set bra_add = '" + add + "' where bra_no = '" + no + "'";
					manageDao.update(str);
					System.out.println("지점 주소가 변경되었습니다.");

				} else if (num == 2) {
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					System.out.println("변경된 관리자 이름을 입력하세요.");
					String man = sc.nextLine();
					str = "update branch set bra_man = '" + man + "' where bra_no = '" + no + "'";
					manageDao.update(str);
					System.out.println("관리자가 변경되었습니다.");

				} else if (num == 3) {
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					System.out.println("변경할 구내 전화번호를 입력하세요.");
					String tel = sc.nextLine();
					str = "update branch set bra_tel = '" + tel + "' where bra_no = '" + no + "'";
					manageDao.update(str);
					System.out.println("구내 전화번호가 변경되었습니다.");

				} else if (num == 4) {
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					System.out.println("지점명을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("지점주소를 입력하세요.");
					String add = sc.nextLine();
					System.out.println("지점관리자를 입력하세요.");
					String man = sc.nextLine();
					System.out.println("지점 전화번호를 입력하세요.");
					String tel = sc.nextLine();
					str = "insert into branch (bra_no, bra_name, bra_add, bra_man, bra_tel) " + "values ('" + no + "'"
							+ ", " + "'" + name + "'" + ", " + "'" + add + "'" + ", " + "'" + man + "'" + ", " + tel
							+ ")";
					manageDao.update(str);
					System.out.println("지점테이블에 컬럼이 삽입되었습니다.");

				} else if (num == 5) {
					System.out.println("지점번호를 입력하세요.");
					String no = sc.nextLine();
					str = "delete from branch where bra_no =  '" + no + "'";
					manageDao.update(str);
					System.out.println("지점테이블에 컬럼이 삭제되었습니다.");

				} else if (num == 6) {
					break;

				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");

			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

	public void ManagerCarUpdateInfo() {
	      while (true) {
	         System.out.println("===========================================");
	         System.out.println("=============차량테이블 수정 페이지===============");
	         System.out.println("1. 대여료 변경  2. 컬럼 삽입  3. 컬럼 삭제  4. 뒤로가기  ");
	         System.out.println("===========================================");
	         str = sc.nextLine();
	         str = str.replaceAll("\\s", "");
	         try {
	            num = Integer.parseInt(str);

	            if (num == 1) {
	               System.out.println("차량번호를 입력하세요.");
	               String no = sc.nextLine();
	               System.out.println("변경할 대여료 값을 입력하세요.");
	               String pay = sc.nextLine();
	               str = "update car set car_pay = '" + pay + "' where car_no = '" + no + "'";
	               manageDao.update(str);
	               System.out.println("차량 대여료가 변경되었습니다.");

	            }else if (num == 2) {
	               System.out.println("차량번호를 입력하세요.");
	               String no = sc.nextLine();
	               System.out.println("차량종류를 입력하세요.");
	               String cls = sc.nextLine();
	               System.out.println("대여료를 입력하세요.");
	               String pay = sc.nextLine();
	               System.out.println("차량색상을 입력하세요.");
	               String col = sc.nextLine();
	               System.out.println("지점번호를 입력하세요.");
	               String bno = sc.nextLine();
	               System.out.println("차량명을 입력하세요.");
	               String name = sc.nextLine();
	               str = "insert into car (car_no, car_cls, car_pay, car_col, car_bno, car_name) "
	                  + "values ('"+ no + "','"+ cls + "'," + pay + ",'" + col + "','" + bno + "','" + name + "')";
	               manageDao.update(str);
	               System.out.println("차량테이블에 컬럼이 삽입되었습니다.");

	            } else if (num == 3) {
	               System.out.println("차량번호를 입력하세요.");
	               String no = sc.nextLine();
	               str = "delete from car where car_no =  '" + no + "'";
	               manageDao.update(str);
	               System.out.println("차량테이블에 컬럼이 삭제되었습니다.");

	            }
	            else if (num == 4) {
	               break;

	            } else
	               System.out.println("올바른 메뉴 번호가 아닙니다.");

	         } catch (Exception e) {
	            System.out.println("적절한 숫자를 입력해주세요.");
	         }
	      }
	   }

	public void ManagerModelUpdateInfo() {
		while (true) {
			System.out.println("====================================");
			System.out.println("==========모델테이블 수정 페이지==========");
			System.out.println("1.컬럼 삽입    2.컬럼 삭제   3.뒤로가기");
			System.out.println("====================================");
			str = sc.nextLine();
			str = str.replaceAll("\\s", "");
			try {
				num = Integer.parseInt(str);

				if (num == 1) {
					System.out.println("모델명을 입력하세요.");
					String name = sc.nextLine();
					System.out.println("제조사를 입력하세요.");
					String brand = sc.nextLine();
					System.out.println("연료를 입력하세요.");
					String fuel = sc.nextLine();
					System.out.println("배기량을 입력하세요.");
					String cc = sc.nextLine();
					System.out.println("인승을 입력하세요");
					String per = sc.nextLine();
					int pers = Integer.parseInt(per);
					str = "insert into model (mod_name, mod_brand, mod_fuel, mod_cc, mod_per) " + "values ('" + name
							+ "'" + ", " + "'" + brand + "'" + ", " + "'" + fuel + "'" + ", " + "'" + cc + "'" + ", "
							+ per + ")";
					manageDao.update(str);
					System.out.println("모델테이블에 컬럼이 삽입되었습니다.");

				} else if (num == 2) {
					System.out.println("모델명 입력하세요.");
					String name = sc.nextLine();
					str = "delete from model where mod_name =  '" + name + "'";
					manageDao.update(str);
					System.out.println("모델테이블에 컬럼이 삭제되었습니다.");

				} else if (num == 3) {
					break;

				} else
					System.out.println("올바른 메뉴 번호가 아닙니다.");

			} catch (Exception e) {
				System.out.println("적절한 숫자를 입력해주세요.");
			}
		}
	}

}
