package caffe;

import java.util.ArrayList; 
import java.util.Scanner;

class MenuManagement{ 
	
	private Scanner sc;
	private Test t1;
	private ArrayList<String> menuName;
	private ArrayList<Integer> menuPrice;
	
	// 생성자
	public MenuManagement() {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		
		menuName = new ArrayList<>();
		menuPrice = new ArrayList<>();
		
	}
	
	// 실행 메소드인 run메소드
	public void run() {
		
		String menu;

		System.out.println("===============================================================");
		System.out.println("메뉴 관리프로그램을 실행합니다.");

		while(true) {
			System.out.println("[ C: 메뉴 추가 || U: 메뉴 수정 || D: 메뉴 삭제 || R: 메뉴 보기 || X or 공백: 종료 ]");
			System.out.print("작업을 선택하세요: ");
			menu = sc.nextLine();
			
			if(menu.equals("x") || menu.equals("X") || t1.strTest(menu) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else {
				if(menu.equals("c") || menu.equals("C")) {
					System.out.println("메뉴 추가 프로세스를 실행합니다.");
					addMenu();
				} else if(menu.equals("u") || menu.equals("U")) {
					System.out.println("메뉴 수정 프로세스를 실행합니다.");
					editMenu();
				} else if(menu.equals("d") || menu.equals("D")) {
					System.out.println("메뉴 삭제 프로세스를 실행합니다.");
					deleteMenu();
				} else if(menu.equals("r") || menu.equals("R")) {
					System.out.println("메뉴 보기 프로세스를 실행합니다.");
					printMenu();				
				}
			} // if-else문 종료
			
		} // while문 종료
	} // run 메소드 종료
	
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

	// 메뉴 추가 메소드
	public void addMenu() {
			
		String name = ""; // 메뉴명을 저장할 변수
		int price = 0; // 가격을 저장할 변수
		String str = ""; // price에 값을 저장하기 전에 유효성 검사를 진행하기 위해 선언한 변수
			
		while(true) {
			System.out.print("메뉴명을 입력하세요 : ");
			name = sc.nextLine();
					
			// name의 유효성검사를 진행 후 false면 break, 아니면 가격을 입력받고 유효성검사를 진행해 false면 break,
			// 둘 다 유효성 검사를 진행해 true면 리스트에 값을 add
			if(t1.strTest(name) == false ) {
					
				System.out.println("프로그램 종료");
				System.out.println("===============================================================");
				break;
					
			} else {
					
				System.out.print("가격을 입력하세요 : ");
				str = sc.nextLine();
					
				if(t1.isNumber(str) == false) { 
						
					System.out.println("프로그램 종료");
					System.out.println("===============================================================");
					break; 
						
				}			
				price = Integer.parseInt(str);
			}
			// 리스트에 값을 add
			menuName.add(name);
			menuPrice.add(price);			
		} // while문 종료
			
	}

	// 메뉴 이름, 가격 수정 메소드
	public void editMenu() {
			
		String name = ""; // 메뉴 이름을 입력받고 저장할 변수
		String p = ""; // 정수형 유효성 검사용 변수
		String search = ""; // 무엇을 수정할 지 입력받고 저장하기 위해 선언한 변수
		String str = ""; // 여러 가지 질문들의 대한 답을 저장하기 위한 변수
		int price = 0; // 메뉴 가격을 입력받고 저장할 변수
		int index = 0; // 메뉴를 찾을 때 사용할 변수

		printMenu();
			
		while(true) {
				
			System.out.print("바꾸고 싶은 값을 이름으로 찾으시겠습니까? : "); 
			str = sc.nextLine();
				
			// 입력값을 유효성 검사를 진행해 false면 break
			if(t1.strTest(str) == false) { break; }
				
			// 입력값이 "예" 이거나 "yes"이거나 "ㅇㅇ" 이라면 if문 실행 아니면 else문 실행
			if(str.equals("예") || str.equals("yes") || str.equals("ㅇㅇ")) {
				System.out.print("바꾸고 싶은 값을 입력하세요 : ");
				search = sc.nextLine(); // 바꾸고 싶은 값을 search에 저장
				System.out.print("하나만 바꾸시겠습니까? 둘 다 바꾸시겠습니까?");
				str = sc.nextLine();
					
				// str의 유효성 검사 진행
				if(t1.strTest(str) == false) {break;}
					
				if(str.equals("하나") || str.equals("one")) {
						
					for(int i = 0; i < menuName.size(); i++) {
							
						if(menuName.get(i).equals(search)) {
								
							System.out.print("이름을 바꾸시겠습니까? 가격을 바꾸시겠습니까? : ");
							str = sc.nextLine();
								
							if(t1.strTest(str) == false) {break;}
								
							// str에 저장된 값이 이름이나 name이면 이름을 변경 아니면 가격을 변경
							if(str.equals("이름") || str.equals("name")) {
									
								System.out.print("메뉴의 바뀔 이름을 입력하십시오 : ");
								name = sc.nextLine();
									
								if(t1.strTest(name)) {break;}
								
								menuName.set(i, name);
									
							} else if(str.equals("가격") || str.equals("price")) {
									
								System.out.print("얼마로 바꾸시겠습니까? : ");
								p = sc.nextLine();
									
								if(t1.isNumber(p) == false) {break;}
									
								price = Integer.parseInt(p);
								menuPrice.set(i, price);
									
							} // 168줄 if-else if문 종료
						} // 161줄 if문 종료
					} // 159줄 for문 종료 
				} // 157줄 if문 종료
				// 157줄 if문의 else if문
				else if(str.equals("둘다") || str.equals("all")) {
						
					for(int i = 0; i < menuName.size(); i++) {
							
						if(menuName.get(i).equals(search)) {
								
							System.out.print("바꿀 메뉴 이름을 입력하세요 : ");
							name = sc.nextLine();
								
							if(t1.strTest(name) == false) {break;}
								
							System.out.print("바꿀 메뉴의 가격을 입력하세요 : ");
							p = sc.nextLine();
								
							if(t1.isNumber(p) == false) {break;}
								
							price = Integer.parseInt(p);
						
							menuName.set(i, name);
							menuPrice.set(i, price);
						} //195줄 if문 종료
					} // 194줄 for문 종료
				} // 192줄 else-if문 종료
					
			} // 148줄 if문 종료
			else { // 148줄 if문에 대한 else문
					
				System.out.print("바꾸고 싶은 메뉴의 메뉴번호를 입력하세요 : ");
				p = sc.nextLine();
					
				if(t1.isNumber(p) == false) {break;}
					
				index = Integer.parseInt(p);
					
				if(index < 1) { break; }
					
				System.out.print("둘 다 수정하시겠습니까? 하나만 수정하시겠습니까? : ");
				str = sc.nextLine();
					
				if(str.equals("하나") || str.equals("one")) {
						
					System.out.print("이름을 바꾸시겠습니까? 가격을 바꾸시겠습니까? : ");
					str = sc.nextLine();
						
					if(t1.strTest(str) == false) {break;}
						
					if(str.equals("이름") || str.equals("name")) {
							
						System.out.print("메뉴의 바뀔 이름을 입력하십시오 : ");
						name = sc.nextLine();
							
						if(t1.strTest(name) == false) {break;}
							
						menuName.set((index-1), name);
									
					} else if(str.equals("가격") || str.equals("price")) {			
							
						System.out.print("얼마로 바꾸시겠습니까? : ");
						p = sc.nextLine();
							
						if(t1.isNumber(p) ==false) {break;}
							
						price = Integer.parseInt(p);
						menuPrice.set((index-1), price);
								
					} // 235줄 if-244줄 else if문 종료
						
				} // 230줄 if문 종료
				else { // 230줄 if문에 대한 else문
						
					System.out.print("메뉴의 바뀔 이름을 입력하세요 : ");
					name = sc.nextLine();
						
					if(t1.strTest(name) == false) {break;}
						
					System.out.print("얼마로 바꾸시겠습니까? : ");
					p = sc.nextLine();
						
					if(t1.isNumber(p) == false) {break;}
					
					price = Integer.parseInt(p);
						
					menuName.set((index-1), name);
					menuPrice.set((index-1), price);
						
				} // 261줄 else문 종료
					
			} // 148줄 if문, 218줄 else문 종료 지점 
				
		} // 139줄 while문 종료 
			
	} // 수정 메소드 종료
	
	// 메뉴 이름, 가격 삭제 메소드
	public void deleteMenu() {
			
		System.out.println("=============메뉴 삭제 전=============");
		printMenu();
			
		while(true) {
				
			int number = 0; // 제거할 메뉴 번호를 입력받아 저장할 변수
			String str = ""; // 메뉴 번호를 저장 하기 전 유효성 검사를 진행하기 위해 선언한 변수
				
			System.out.print("제거 할 메뉴 번호를 입력하세요 : ");
			str = sc.nextLine();
				
			if(t1.isNumber(str) == false) {break;}
				
			number = Integer.parseInt(str);
				
			if(number < 1) {break;}
				
			menuName.remove((number-1));
			menuPrice.remove((number-1));
				
		} // while문 종료
			
		System.out.println("=============메뉴 삭제 후=============");
		printMenu();
			
	} // 삭제 메소드 종료
	
	// 메뉴 이름, 가격 출력 메소드
	public void printMenu() {
			
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡ메뉴판 출력ㅡㅡㅡㅡㅡㅡㅡㅡ");
		// menuName 리스트에 size만큼 반복문을 돌면서 메뉴 이름 리스트와 메뉴 가격 리스트를 출력
		for(int i = 0; i < menuName.size(); i ++) {
			System.out.println((i+1) + "." + menuName.get(i) +"\t" + "Price: " + menuPrice.get(i));		
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			
	} // 출력 메소드 종료
	
}
