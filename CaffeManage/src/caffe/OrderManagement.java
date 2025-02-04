package caffe;

import java.util.ArrayList;
import java.util.Scanner;

class OrderManagement { 
	
	private Scanner sc;
	private Test t1;	
	MenuManagement menu;
	
	// 메뉴 관리에서 사용하는 변수
	private ArrayList<String> menuName;
	private ArrayList<Integer> menuPrice;
	
	// 주문 관리에서 사용하는 변수
	private ArrayList<String> OrderName; // 주문 메뉴명
	private ArrayList<Integer> OrderSum; // 주문 메뉴의 가격
	private ArrayList<Integer> OrderCount; // 주문 메뉴의 수량
	private int asum; // 모든 주문한 메뉴들의 총 합
	
	// 주문 확정 및 매출 관리에서 사용할 변수
	private ArrayList<String> conMenu; // 매출에서 사용할 메뉴명
	private ArrayList<Integer> conCount; // 매출에서 사용할 수량
	private ArrayList<Integer> conPrice; // 매출에서 사용할 가격
	private ArrayList<String> mobileN; // 모바일 번호 저장을 위한 리스트
	
	public OrderManagement(MenuManagement _menu) {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		
		menuName = _menu.getMenuName();
		menuPrice = _menu.getMenuPrice();
		
		this.menu = _menu; // 파라미터로 받은 클래스 안에 모든 값과 데이터들을 선언한 menu에 저장
		OrderName = new ArrayList<>();
		OrderCount = new ArrayList<>();
		OrderSum = new ArrayList<>();
		asum = 0; // 모든 메뉴의 총합
		
		conMenu = new ArrayList<>();
		conCount = new ArrayList<>();
		conPrice = new ArrayList<>();
		mobileN = new ArrayList<>();

	}
	
	public ArrayList<String> getMenuName() {
		return menuName;
	}

	public void setMenuName(ArrayList<String> menuName) {
		this.menuName = menuName;
	}

	public ArrayList<Integer> getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(ArrayList<Integer> menuPrice) {
		this.menuPrice = menuPrice;
	}

	public ArrayList<String> getOrderName() {
		return OrderName;
	}

	public void setOrderName(ArrayList<String> orderName) {
		OrderName = orderName;
	}

	public ArrayList<Integer> getOrderSum() {
		return OrderSum;
	}

	public void setOrderSum(ArrayList<Integer> orderSum) {
		OrderSum = orderSum;
	}

	public ArrayList<Integer> getOrderCount() {
		return OrderCount;
	}

	public void setOrderCount(ArrayList<Integer> orderCount) {
		OrderCount = orderCount;
	}

	public int getAsum() {
		return asum;
	}

	public void setAsum(int asum) {
		this.asum = asum;
	}

	public ArrayList<String> getConMenu() {
		return conMenu;
	}

	public void setConMenu(ArrayList<String> conMenu) {
		this.conMenu = conMenu;
	}

	public ArrayList<Integer> getConCount() {
		return conCount;
	}

	public void setConCount(ArrayList<Integer> conCount) {
		this.conCount = conCount;
	}

	public ArrayList<Integer> getConPrice() {
		return conPrice;
	}

	public void setConPrice(ArrayList<Integer> conPrice) {
		this.conPrice = conPrice;
	}

	public ArrayList<String> getMobileN() {
		return mobileN;
	}

	public void setMobileN(ArrayList<String> mobileN) {
		this.mobileN = mobileN;
	}

	// 실행 메소드
	public void run() {
		
		String str = "";
		menu.printMenu();
		System.out.println("===============================================================");
		System.out.println("주문 관리프로그램을 실행합니다.");
		System.out.println("현재 메뉴판");
		
		System.out.println("[ C: 주문 추가 || D: 주문 삭제 || R: 주문 보기 || T: 주문 확정 || X or 공백: 종료 ]");
		
		while(true) {
			System.out.print("작업을 선택하세요: ");
			str = sc.nextLine();

			if(str.equals("x") || str.equals("X") || t1.strTest(str) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else {
				if(str.equals("c") || str.equals("C")) {
					System.out.println("주문 추가");
					addOrder();
				} else if(str.equals("d") || str.equals("D")) {
					System.out.println("주문 삭제");
					deleteOrder();
				} else if(str.equals("r") || str.equals("R")) {
					System.out.println("주문 보기");
					printOrder();
					System.out.println("[ C: 주문 추가 || D: 주문 삭제 || R: 주문 보기 || T: 주문 확정 || X or 공백: 종료 ]");
				} else if(str.equals("t") || str.equals("T")) {
					System.out.println("주문 확정");
					confirmedOrder();
				}
			}
		}
	} // 실행 메소드 종료
	
	// 주문 추가 메소드
	public void addOrder() {
		
		int meNum = 0; // 메뉴 넘버
		int num = 0; // 메뉴 개수
		String str = "";
		
		while(true) {
			
			System.out.print("주문할 메뉴 번호를 입력하세요 : ");
			str = sc.nextLine(); // 메뉴 번호
			
			if(t1.strTest(str) == false) {break;}
			
			meNum = Integer.parseInt(str);
			
			if(meNum <= 0) {break;}
			
			System.out.print("몇개 주문하시겠습니까? : ");
			str = sc.nextLine();
			
			if(t1.isNumber(str) == false) {break;}
			
			num = Integer.parseInt(str);
			
			if(num <= 0) {break;}
			
			OrderName.add(menuName.get(meNum-1));
			OrderCount.add(num);
			OrderSum.add(menuPrice.get(meNum-1) * num);
			
		} // while문 종료
		
	} // addOrder메소드 종료

	// 주문 출력 메소드
	public void printOrder() {
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡ주문판 출력ㅡㅡㅡㅡㅡㅡㅡㅡ");
		for(int i = 0; i < OrderCount.size(); i ++) {
			System.out.println((i+1) + "." + OrderName.get(i) +"\t" + "개수: " + OrderCount.get(i) + "\t" + "가격: " + OrderSum.get(i));
			asum += OrderSum.get(i);
		}
		System.out.println("총 가격: " + asum);
		asum = 0;
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
	}
	
	// 주문 삭제 메소드
	public void deleteOrder() {
		
		String str = "";
		System.out.println("=============주문 삭제 전============");
		printOrder();
		
		System.out.print("하나씩 지우시겠습니까? : ");
		str = sc.nextLine();
		
		while(true) {
			
			if(str.equals("ㅇㅇ")) {
				System.out.print("삭제할 메뉴의 번호를 입력하세요 : ");
				int i = sc.nextInt();
				if(i == 0) {
					break;
				} else {
					asum -= OrderSum.get(i-1);
					OrderName.remove(i-1);
					OrderCount.remove(i-1);
					OrderSum.remove(i-1);
				}		
			} else {
				OrderName.clear();
				OrderCount.clear();
				OrderSum.clear();
				asum = 0;
				break;
			}
			
		}
	} // 주문 삭제 메소드 종료

	// 주문 확정 메소드
	public void confirmedOrder() {
		
		String str = ""; // 확정 시킬건지 아닐건지 저장하는 변수
		String mobile = ""; // 모바일 번호를 입력받을 변수
		
		// 주문을 확정 시킬건지 아닐건지 물어보는 코드
		System.out.print("주문을 확정시키겠습니까? : ");
		str = sc.nextLine();
		if(t1.strTest(str) == false) {
			return;
		} else {
			System.out.print("적립을 하시겠습니까? : ");
			str = sc.nextLine();
			if(t1.strTest(str) == false || str.equals("ㄴㄴ")) {
				mobile = " ";
				copyOrder(mobile);
			}
			if(str.equals("ㅇㅇ") || str.equals("dd")) {
				System.out.print("모바일 번호를 입력하세요: ");
				mobile = sc.nextLine();
				copyOrder(mobile);
			}
		} // if-else문 종료
		
	} // 메뉴 확정 메소드 종료
	
	// 리스트 카피 메소드
	public void copyOrder(String _mobile) {
		
		for(int i = 0; i < OrderName.size(); i++) {
			conMenu.add(OrderName.get(i));
			conCount.add(OrderCount.get(i));
			conPrice.add(OrderSum.get(i));
			mobileN.add(_mobile);
		}
		
		OrderName.clear();
		OrderCount.clear();
		OrderSum.clear();
	}
	
}