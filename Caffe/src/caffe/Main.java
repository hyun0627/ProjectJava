package caffe;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String select = ""; // 선택 값을 저장하기 위한 변수
		Menu menu = new Menu();
		Order order = new Order(menu);
		
		System.out.println("===============================================================");
		System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X: 종료 ]");
		
		while(true) {	
			
			System.out.print("작업을 선택하세요 : ");
			select = sc.nextLine(); // 어떤 작업을 선택할건지 입력하는 menuselect(ms) 스캐너
						
			if(select.equals("x") || select.equals("X") || new Test().strTest(select) == false) {
				
				System.out.println("프로그램을 종료합니다.");
				System.out.println("===============================================================");
				break;
				
			} else {
				
				if(select.equals("m") || select.equals("M")) {
					
					menu.run();
					
				} else if(select.equals("o") || select.equals("O")) {
		
					order.run();
					
				} else if(select.equals("s") || select.equals("S")) {
					
					Sales sales = new Sales(menu, order);
					sales.run();
				} 
			}			
		}
		
		sc.close();

	}

}
