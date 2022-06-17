import java.time.LocalDate;

public class TestClass {

	public static void main(String[] args) {

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		String month = now.getMonth().toString();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue();
		System.out.println(now); // 2022-06-17
		System.out.println(year); // 2022
		System.out.println(month + "(" + monthValue + ")"); //JUNE(6)
		System.out.println(dayOfMonth); // 17
		System.out.println(dayOfYear); // 168
		System.out.println(dayOfWeek + "(" + dayOfWeekValue + ")"); //FRIDAY(5)

	}

}
