package caffe;

import java.util.ArrayList;
import java.util.Scanner;

public class SalesManagement {
	
	private Scanner sc; 
	private Test t1;	
	
	MenuManagement menu;
	OrderManagement order;
	
	private ArrayList<String> conMenu;
	private ArrayList<Integer> conCount;
	private ArrayList<Integer> conPrice;
	private ArrayList<String> mobileN;
	int sumPrice;
	
	
	public SalesManagement(MenuManagement _menu, OrderManagement _order) {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		
		this.menu = _menu;
		this.order = _order;
		
		sumPrice = _order.getAsum(); // 총합
		conMenu = _order.getConMenu();
		conCount = _order.getConCount();
		conPrice = _order.getConPrice();
		mobileN = _order.getMobileN();
		
	}
	
	// 실행 메소드
	public void run() {
		
		String sales = "";
		
		System.out.println("===============================================================");
		System.out.println("실적 관리프로그램을 실행합니다.");
		System.out.println("[ R: 매출 보기 || X or 공백: 완료 ]");
		
		while(true) {
			System.out.print("작업을 선택하세요: ");
			sales = sc.nextLine();
			
			if(sales.equals("x") || sales.equals("X") || t1.strTest(sales) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else if(sales.equals("r") || sales.equals("R")) {
				System.out.println("매출 보기");
				printSales();
			} // if-else문 종료
		} // while문 종료
		
	} // 실행 메소드 종료
	
	public void printSales() {
		
		int i = 0;
		int index = 0;
		int reSum = 0;
		sumPrice = 0;
		
		while(i < mobileN.size()) {
			if(i == 0) {
				System.out.println((index+1)+".모바일 번호: " + mobileN.get(i));
				System.out.println("메뉴: " + conMenu.get(i) + "\t\t개수: " + conCount.get(i) + "\t\t가격: " + conPrice.get(i));
				sumPrice += conPrice.get(i);
				reSum += conPrice.get(i);
			} else if(mobileN.get(i) == mobileN.get((i-1))) {
				System.out.println("메뉴: " + conMenu.get(i) + "\t\t개수: " + conCount.get(i) + "\t\t가격: " + conPrice.get(i));
				sumPrice += conPrice.get(i);
				reSum += conPrice.get(i);
			} else {
				System.out.println("총 가격: " + sumPrice);
				index += 1;
				sumPrice = 0;
				System.out.println("=========================================================");
				System.out.println((index+1)+".모바일 번호: " + mobileN.get(i));
				System.out.println("메뉴: " + conMenu.get(i) + "\t\t개수: " + conCount.get(i) + "\t\t가격: " + conPrice.get(i));
				sumPrice += conPrice.get(i);
				reSum += conPrice.get(i);
			}
			i++;
		}
		System.out.println("총 가격: " + sumPrice);
		System.out.println("=========================================================");
		System.out.println("진짜 총가격: " + reSum);
		System.out.println("=========================================================");
	}

}
