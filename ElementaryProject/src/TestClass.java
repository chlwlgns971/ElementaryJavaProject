import java.util.*;

public class TestClass {

	public static void main(String[] args) {
		String str="";
		Scanner sc= new Scanner(System.in);
		System.out.println("값을 입력하세요: ");
		str=sc.nextLine();
		str=str.replaceAll("\\s", "");
		if(str.equals("")) System.out.println("str은 공백입니다.");
		else System.out.println("str은 공백이 아닙니다.");
		System.out.println(str);

	}

}
