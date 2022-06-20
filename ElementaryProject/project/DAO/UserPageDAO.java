package DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.DBConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserPageDAO extends DBConnection {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;

	public boolean checkIDandPW(String userID, String userPW) {
		boolean check = true;
		try {
			conn = getConnection();
			stat = getStatement();
			String PW = "";
			rs = stat.executeQuery("SELECT MEM_PW FROM MEMBER WHERE MEM_ID='" + userID + "'");
			while (rs.next()) {
				PW = rs.getString(1);
			}
			if (PW.equals(userPW))
				check = true;
			else
				check = false;
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
		return check;
	}

	public void delete(String str) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery("DELETE FROM MEMBER WHERE MEM_ID='" + str + "'");
			System.out.println("회원탈퇴에 성공하였습니다.");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패"+e.getMessage());
		} finally {
			close(rs, stat, conn);
		}
	}

	public void deleteRsv(String str) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery("DELETE FROM RSV WHERE RSV_NO='" + str + "'");
			System.out.println("예약삭제 완료.");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			close(rs, stat, conn);
		}
	}

	public Map<String, String> searchRsv(String id) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery("SELECT RSV_NO, REPLACE(RSV_ID,'" + id
					+ "',(SELECT MEM_NAME FROM MEMBER WHERE MEM_ID='" + id + "')), RSV_CNO"
					+ ", CAR_NAME, BRA_NAME, RSV_PAY, TO_CHAR(RSV_DATE,'YYYY-MM-DD'), TO_CHAR(RSV_RET,'YYYY-MM-DD') "
					+ "FROM RSV INNER JOIN CAR ON(CAR_NO=RSV_CNO) INNER JOIN BRANCH ON(BRA_NO=RSV_BNO)"
					+ "WHERE RSV_ID='" + id + "'ORDER BY RSV_NO DESC");
			// column갯수 구하기
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String key = "";
			String str = ""; // hashmap에 넣을 출력값
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (i == 1) {
						key = rs.getNString(i);
					} else {
						str += " \t| " + rs.getString(i);
					}
				}
				resultMap.put(key, str);
				str = "";
				key = "";
			}
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패: " + e.getMessage());
		} finally {
			close(rs, stat, conn);
		}
		return resultMap;
	}

	// 회원 정보수정
	public void editUserInfo(String res) {
		try {
			conn = getConnection();
			stat = getStatement();
			rs = stat.executeQuery(res);
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}

	// 예약가능한 차량조회
	public Map<String, String> chkRsv(String x, int sm1, int sd1, int em1, int ed1) {
		Map<String, String> resultMap = new HashMap<>();
		// map : 결과를 담음
		Map<String, String> map = new HashMap<>();

		try {
			conn = getConnection();
			stat = getStatement();
			String query = "select car_no,car_cls,car_pay,car_col,car_bno,car_name from car where car_bno='" + x
					+ "' and car_no not in  (select car_no from car inner join rsv on(car_no=rsv_cno) where(rsv_date>='2022-"
					+ sm1 + "-" + sd1 + "' and rsv_date<='2022-" + em1 + "-" + ed1 + "')" + "    or (rsv_ret>='2022-"
					+ sm1 + "-" + sd1 + "' and rsv_ret<='2022-" + em1 + "-" + ed1 + "')) order by 1";

			rs = stat.executeQuery(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			// column갯수 구하기
			int columnCount = rsmd.getColumnCount();
			String carNo = ""; // hashmap에 넣을 키 값
			String str = " "; // hashmap에 넣을 출력값
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (i == 1) { // 1.차량번호
						carNo = rs.getString(i);
					} else { // 2. 차량분류 3. 대여료 4. 차량 색상 5.지점번호 6. 모델명
						str += rs.getString(i) + "   |   ";
						if (str.length() < 8) {
							System.out.println(" ");
						}
					}
				}
				resultMap.put(carNo, str);
				str = "";
			}
			Set<String> result = resultMap.keySet();

			Iterator<String> iter = result.iterator();

			while (iter.hasNext()) {
				carNo = iter.next().toString();
				String value = resultMap.get(carNo);

				map.put(carNo, value);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs, stat, conn);
		}
		return map;

	}

	public void selRsCar(String regNo, String selrCn, String memId, int sm2, int sd2, int em2, int ed2) {
		try {
			conn = getConnection();
			stat = getStatement();

			String s1 = null;
			String s2 = null;

			if (sm2 < 10) {
				s1 = "0" + sm2;
			} else {
				s1 = "" + sm2;
			}

			if (sd2 < 10) {
				s2 = "0" + sd2;
			} else {
				s2 = "" + sd2;
			}
			
			String sqlStr = "insert into rsv(rsv_date, rsv_ret, rsv_pay, rsv_bno, rsv_cno, rsv_id, rsv_no)   "
					+ "values ('2022-" + sm2 + "-" + sd2 + "'," + "'2022-" + em2 + "-" + ed2 + "'," + "(TO_DATE('2022-"
					+ em2 + "-" + ed2 + "') - TO_DATE('2022-" + sm2 + "-" + sd2
					+ "')+1) * (select car_pay  FROM CAR where car_no='" + selrCn + "'),'" + regNo + "','" + selrCn
					+ "','" + memId + "', fn_create_rsvno( '2022" + s1 + s2 + "'))";
			int res = stat.executeUpdate(sqlStr);
			System.out.println("예약이 완료되었습니다. ");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		} finally {
			close(rs, stat, conn);
		}
	}

}
