package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBConnection;

public class ManageDAO extends DBConnection {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;

	public void selectMember(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
			while (rs.next()) {
				System.out.print(rs.getString("MEM_ID"));
				System.out.print(" ");
				System.out.print(rs.getString("MEM_PW"));
				System.out.print(" ");
				System.out.print(rs.getString("MEM_NAME"));
				System.out.print(" ");
				System.out.print(rs.getString("MEM_REGNO"));
				System.out.print(" ");
				System.out.print(rs.getString("MEM_ADD"));
				System.out.print(" ");
				System.out.print(rs.getString("MEM_HP"));
				System.out.println();
			}
			// rs = stat.executeQuery(sql); '명령문'

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 차량 전체 정보 조회
	public void selectCar(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
			while (rs.next()) {
				System.out.print(rs.getString("CAR_NO"));
				System.out.print(" ");
				System.out.print(rs.getString("CAR_CLS"));
				System.out.print(" ");
				System.out.print(rs.getString("CAR_PAY"));
				System.out.print(" ");
				System.out.print(rs.getString("CAR_COL"));
				System.out.print(" ");
				System.out.print(rs.getString("CAR_BNO"));
				System.out.print(" ");
				System.out.print(rs.getString("CAR_NAME"));
				System.out.println();
			}
			// rs = stat.executeQuery(sql); '명령문'

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 예약 정보 조회
	public void selectRsv(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
			while (rs.next()) {
				System.out.print(rs.getString("TO_CHAR(RSV_DATE,'YYYY-MM-DD')"));
				System.out.print(" ");
				System.out.print(rs.getString("TO_CHAR(RSV_RET,'YYYY-MM-DD')"));
				System.out.print(" ");
				System.out.print(rs.getString("RSV_PAY"));
				System.out.print(" ");
				System.out.print(rs.getString("RSV_BNO"));
				System.out.print(" ");
				System.out.print(rs.getString("RSV_CNO"));
				System.out.print(" ");
				System.out.print(rs.getString("RSV_ID"));
				System.out.print(" ");
				System.out.print(rs.getString("RSV_NO"));
				System.out.println();
			}
			// rs = stat.executeQuery(sql); '명령문'

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점 전체 정보 조회
	public void selectBra(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
			while (rs.next()) {
				System.out.print(rs.getString("BRA_NO"));
				System.out.print(" ");
				System.out.print(rs.getString("BRA_NAME"));
				System.out.print(" ");
				System.out.print(rs.getString("BRA_ADD"));
				System.out.print(" ");
				System.out.print(rs.getString("BRA_MAN"));
				System.out.print(" ");
				System.out.print(rs.getString("BRA_TEL"));
				System.out.println(" ");
			}
			// rs = stat.executeQuery(sql); '명령문'

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 모델 전체 정보 조회
	public void selectMod(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
			while (rs.next()) {
				System.out.print(rs.getString("MOD_NAME"));
				System.out.print(" ");
				System.out.print(rs.getString("MOD_BRAND"));
				System.out.print(" ");
				System.out.print(rs.getString("MOD_FUEL"));
				System.out.print(" ");
				System.out.print(rs.getString("MOD_CC"));
				System.out.print(" ");
				System.out.print(rs.getString("MOD_PER"));
				System.out.println(" ");
			}
			// rs = stat.executeQuery(sql); '명령문'

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	public void updateMemberHP(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 회원 주소 변경
	public void updateMemberAdd(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 회원 비밀번호 변경
	public void updateMemberPW(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점 주소 변경
	public void updateBranchAdd(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점 담당자 변경
	public void updateBranchManager(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점 구내전화번호 변경
	public void updateBranchTel(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점테이블 컬럼 삽입
	public void InsertBranchColumn(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 지점테이블 컬럼 삭제
	public void DeleteBranchColumn(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 차량 대여료 변경
	public void UpdateCarPay(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 모델테이블 컬럼데이터 추가
	public void InsertModel(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	// 모델테이블 컬럼데이터 삭제
	public void DeleteModel(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);

		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

}
