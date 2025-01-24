import java.util.ArrayList;
import java.util.Scanner;
 
public class Control {  
	// 모든 아래에 있는 메소드에 사용하기 위해서는 위에다가 선언해주어야 함
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		 
		// alName, alPrice -> 메뉴 관리 메소드에서 사용하는 변수
		ArrayList<String> alName = new ArrayList<>(); // 메뉴명을 저장할 리스트
		ArrayList<Integer> alPrice = new ArrayList<>(); // 가격을 저장할 리스트
		
		// alNum, alMeprice, alOranme -> 주문 관리 메소드에서 사용하는 변수
		ArrayList<Integer> alNum = new ArrayList<>(); // 메뉴의 개수를 저장할 변수
		ArrayList<Integer> alMeprice = new ArrayList<>(); // 메뉴의 각 가격을 저장하기 위한 변수
		ArrayList<String> alOrname = new ArrayList<>(); // 주문에서 메뉴명을 저장할 리스트
		
		// conMenu, conNum, conPrice, mobileN -> 실적관리에서 사용하는 메소드
		ArrayList<String> conMenu = new ArrayList<>(); // 확정된 메뉴의 이름을 저장할 리스트
		ArrayList<Integer> conNum = new ArrayList<>(); // 확정된 메뉴의 개수를 저장할 리스트
		ArrayList<Integer> conPrice = new ArrayList<>(); // 확정된 메뉴의 가격을 저장할 리스트
		ArrayList<String> mobileN = new ArrayList<>(); // 모바일 번호를 저장할 리스트

		String select = ""; // 입력값을 저장하기 위한 변수
		int sumPrice = 0; // 총 가격을 저장하기 위한 변수
		String str = "";
		
		System.out.println("===============================================================");
		System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X: 종료 ]");
		while(true) {	
			System.out.print("작업을 선택하세요 : ");
			select = sc.nextLine(); // 어떤 작업을 선택할건지 입력하는 menuselect(ms) 스캐너
						
			if(select.equals("x") || select.equals("X") || strTest(select) == false) {
				break;
			} else {
				if(select.equals("m") || select.equals("M")) {
					doMenu(alName, alPrice); // 메소드 호출(호출부)
				} else if(select.equals("o") || select.equals("O")) {
					doOrder(alName, alPrice, alNum, alMeprice, alOrname,mobileN,conMenu,conNum,conPrice,sumPrice);
				} else if(select.equals("s") || select.equals("S")) {
					doSales(conMenu, conNum, conPrice, sumPrice, mobileN);
				} 
			}			
		}
		
		sc.close();
		System.out.println("===============================================================");
		System.out.println("프로그램을 종료합니다.");
		
	}
	
	// 메소드(선언부)
	// 메소드 선언: 클래스 안에, 위치는 public static void main 위에 여도 상관 없고 main부분 끝나고 그 아래에 선언해도 상관 없음
	// 메뉴 관리 전체적인걸 담당하는 메소드
	public static void doMenu(ArrayList<String> _alName, ArrayList<Integer> _alPrice) {
		String menu = "";

		System.out.println("===============================================================");
		System.out.println("메뉴 관리프로그램을 실행합니다.");

		while(true) {
			System.out.println("[ C: 메뉴 추가 || U: 메뉴 수정 || D: 메뉴 삭제 || R: 메뉴 보기 || X or 공백: 종료 ]");
			System.out.print("작업을 선택하세요: ");
			menu = sc.nextLine();
			
			if(menu.equals("x") || menu.equals("X") || strTest(menu) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else {
				if(menu.equals("c") || menu.equals("C")) {
					System.out.println("메뉴 추가 프로세스를 실행합니다.");
					addMenu(_alName, _alPrice);
				} else if(menu.equals("u") || menu.equals("U")) {
					System.out.println("메뉴 수정");
					editMenu(_alName, _alPrice);
				} else if(menu.equals("d") || menu.equals("D")) {
					System.out.println("메뉴 삭제");
					deleteMenu(_alName, _alPrice);
				} else if(menu.equals("r") || menu.equals("R")) {
					System.out.println("메뉴 보기");
					printMenu(_alName, _alPrice);
				}
			}
			
		}
		
	}
	
	// 주문 관리 전체적인걸 담당하는 메소드
	public static void doOrder(ArrayList<String> _alName, ArrayList<Integer> _alPrice, ArrayList<Integer> _alNum,
								  ArrayList<Integer> _alMeprice, ArrayList<String> _alOrname, ArrayList<String> _mobileN,
								  ArrayList<String> _conMenu, ArrayList<Integer> _conNum, ArrayList<Integer> _conPrice,
								  int _sumPrice) {
		String order = "";
		
		System.out.println("===============================================================");
		System.out.println("주문 관리프로그램을 실행합니다.");
		System.out.println("현재 메뉴판");
		printMenu(_alName, _alPrice);
		System.out.println("[ C: 주문 추가 || D: 주문 삭제 || R: 주문 보기 || T: 주문 확정 || X or 공백: 종료 ]");
		
		while(true) {
			System.out.print("작업을 선택하세요: ");
			order = sc.nextLine();

			if(order.equals("x") || order.equals("X") || strTest(order) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else {
				if(order.equals("c") || order.equals("C")) {
					System.out.println("주문 추가");
					addOrder(_alName,_alPrice,_alNum,_alMeprice, _alOrname);
				} else if(order.equals("d") || order.equals("D")) {
					System.out.println("주문 삭제");
					deleteOrder(_alOrname, _alNum, _alMeprice, _sumPrice);
				} else if(order.equals("r") || order.equals("R")) {
					System.out.println("주문 보기");
					printOrder(_alOrname, _alNum, _alMeprice, _sumPrice);
					System.out.println("[ C: 주문 추가 || D: 주문 삭제 || R: 주문 보기 || T: 주문 확정 || X or 공백: 종료 ]");
				} else if(order.equals("t") || order.equals("T")) {
					System.out.println("주문 확정");
					confirmedOrder(_alOrname, _alNum, _alMeprice, _mobileN, _conMenu, _conNum, _conPrice);
				}
			}
		}
		
	}
		
	// 실적 관리 전체적인걸 담당하는 메소드
	public static void doSales(ArrayList<String> _conMenu, ArrayList<Integer> _conNum, 
								 ArrayList<Integer> _conPrice, int _sumPrice, ArrayList<String> _mobileN) {
		String sales = "";
		ArrayList<String> mobileN = new ArrayList<>();
		
		System.out.println("===============================================================");
		System.out.println("실적 관리프로그램을 실행합니다.");
		System.out.println("[ R: 매출 보기 || X or 공백: 완료 ]");
		
		while(true) {
			System.out.print("작업을 선택하세요: ");
			sales = sc.nextLine();
			
			if(sales.equals("x") || sales.equals("X") || strTest(sales) == false) {
				System.out.println("===============================================================");
				System.out.println("[ M: 메뉴 관리 || O: 주문 관리 || S: 실적 관리 || X or 공백: 종료 ]");
				break;
			} else if(sales.equals("r") || sales.equals("R")) {
				System.out.println("매출 보기");
				printSales(_conMenu, _conNum, _conPrice, _sumPrice, _mobileN);
			}
		}
		
	}
	
	// 메뉴 추가 메소드
	public static void addMenu(ArrayList<String> _alName, ArrayList<Integer> _alPrice) {
		String mn = ""; // 메뉴명을 저장할 변수
		int mPrice = 0; // 가격을 저장할 변수
		String mp = "";
		
		while(true) {
			System.out.print("메뉴명을 입력하세요 : ");
			mn = sc.nextLine();
			
			
			if(strTest(mn) == false ) {
				System.out.println("프로그램 종료");
				System.out.println("===============================================================");
				break;
			} else {
				System.out.print("가격을 입력하세요 : ");
				mp = sc.nextLine();
				if(isNumber(mp) == false) { 
					System.out.println("프로그램 종료");
					System.out.println("===============================================================");
					break; 
				}			
				mPrice = Integer.parseInt(mp);
			}
			_alName.add(mn);
			_alPrice.add(mPrice);		
			
		}	
		printMenu(_alName, _alPrice);
		
	}
	
	// 메뉴 수정 메소드
	public static void editMenu(ArrayList<String> _alName, ArrayList<Integer> _alPrice) {
		String menuName = ""; // 메뉴 이름을 입력받고 저장할 변수
		int menuPrice = 0; // 메뉴 가격을 입력받고 저장할 변수
		int index = 0; // 메뉴를 찾을 때 사용할 인덱스
		String mp = ""; // menuPrice 값을 저장하기 전에 공백이거나 특수기호가 들어있는지 확인하는 변수 
		String select = ""; // 무엇을 수정할 지 입력받고 저장할 메소드
		String str = "";

		printMenu(_alName, _alPrice);
		
		while(true) {
			
			System.out.print("바꾸고 싶은 값을 이름으로 찾으시겠습니까? : "); 
			select = sc.nextLine();
			
			if(strTest(select) == false) { break; }
			
			if(select.equals("예") || select.equals("yes") || select.equals("ㅇㅇ")) {
				System.out.print("바꾸고 싶은 값을 입력하세요 : ");
				select = sc.nextLine();
				System.out.print("하나만 바꾸시겠습니까? 둘 다 바꾸시겠습니까?");
				str = sc.nextLine();
				
				if(str.equals("하나") || str.equals("one")) {
					
					for(int i = 0; i < _alName.size(); i++) {
						if(_alName.get(i).equals(select)) {
							System.out.print("이름을 바꾸시겠습니까? 가격을 바꾸시겠습니까? : ");
							str = sc.nextLine();
							
							if(str.equals("이름") || str.equals("name")) {
								System.out.print("메뉴의 바뀔 이름을 입력하십시오 : ");
								menuName = sc.nextLine();
								if(strTest(menuName)) {break;}
								_alName.set(i, menuName);
								
							} else if(str.equals("가격") || str.equals("price")) {
								System.out.print("얼마로 바꾸시겠습니까? : ");
								mp = sc.nextLine();
								if(isNumber(mp) == false) {break;}
								menuPrice = Integer.parseInt(mp);
								_alPrice.set(i, menuPrice);
								
							}
						}
					} 	
				} else if(str.equals("둘다") || str.equals("all")) {
					
					for(int i = 0; i < _alName.size(); i++) {
						if(_alName.get(i).equals(select)) {
							System.out.print("바꿀 메뉴 이름을 입력하세요 : ");
							menuName = sc.nextLine();
							
							System.out.print("바꿀 메뉴의 가격을 입력하세요 : ");
							mp = sc.nextLine();
							if(isNumber(mp) == false) {break;}
							menuPrice = Integer.parseInt(mp);
							
							_alName.set(i, menuName);
							_alPrice.set(i, menuPrice);
						}
					}
				}
				
			} else {
				System.out.print("바꾸고 싶은 메뉴의 메뉴번호를 입력하세요 : ");
				mp = sc.nextLine();
				if(isNumber(mp) == false) {break;}
				menuPrice = Integer.parseInt(mp);
				index = Integer.parseInt(sc.nextLine());
				
				if(index < 1) { break; }
				
				System.out.print("둘 다 수정하시겠습니까? 하나만 수정하시겠습니까? : ");
				str = sc.nextLine();
				
				if(str.equals("하나") || str.equals("one")) {
					
					System.out.print("이름을 바꾸시겠습니까? 가격을 바꾸시겠습니까? : ");
					str = sc.nextLine();
					
					if(str.equals("이름") || str.equals("name")) {
						System.out.print("메뉴의 바뀔 이름을 입력하십시오 : ");
						menuName = sc.nextLine();
						_alName.set((index-1), menuName);
								
					} else if(str.equals("가격") || str.equals("price")) {				
						System.out.print("얼마로 바꾸시겠습니까? : ");
						mp = sc.nextLine();
						if(isNumber(mp) ==false) {break;}
						menuPrice = Integer.parseInt(mp);
						_alPrice.set((index-1), menuPrice);
								
					}								 
				} else {
					
					System.out.print("메뉴의 바뀔 이름을 입력하세요 : ");
					menuName = sc.nextLine();
					System.out.print("얼마로 바꾸시겠습니까? : ");
					mp = sc.nextLine();
					if(isNumber(mp) == false) {break;}
					menuPrice = Integer.parseInt(mp);
					_alName.set((index-1), menuName);
					_alPrice.set((index-1), menuPrice);
					
				}
				
			} // if-else if문 끝
			
		} // while문 끝
		
	}
	
	// 메뉴 삭제 메소드
	public static void deleteMenu(ArrayList<String> _alName , ArrayList<Integer> _alPrice) {
		
		System.out.println("=============메뉴 삭제 전=============");
		printMenu(_alName, _alPrice);
		
		while(true) {
			
			int menuN = 0; // 제거할 메뉴 번호를 입력받아 저장할 변수
			String mp = "";
			
			System.out.print("제거 할 메뉴 번호를 입력하세요 : ");
			mp = sc.nextLine();
			if(strTest(mp) == false) {break;}
			menuN = Integer.parseInt(mp);
			
			if(menuN < 1) {break;}
			_alName.remove((menuN-1));
			_alPrice.remove((menuN-1));
			
		}
		
		System.out.println("=============메뉴 삭제 후=============");
		printMenu(_alName, _alPrice);
		
	}
	
	// 메뉴 출력 메소드
	public static void printMenu(ArrayList<String> _alName, ArrayList<Integer> _alPrice) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡ메뉴판 출력ㅡㅡㅡㅡㅡㅡㅡㅡ");
		for(int i = 0; i < _alName.size(); i ++) {
			System.out.println((i+1) + "." + _alName.get(i) +"\t" + "Price: " + _alPrice.get(i));		
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}
	
	// 주문 추가 메소드
	public static void addOrder(ArrayList<String> _alName, ArrayList<Integer> _alPrice, ArrayList<Integer> _alNum, 
								    ArrayList<Integer> _alMeprice, ArrayList<String> _alOrname) {
		
		String menu = ""; // 주문할 메뉴의 이름을 저장할 변수
		int meNum = 0; // 메뉴 넘버
		int num = 0; // 메뉴 개수
		String str = "";
		
		while(true) {
			System.out.print("주문할 메뉴 번호를 입력하세요 : ");
			str = sc.nextLine(); // 메뉴 번호
			if(strTest(str) == false) {break;}
			meNum = Integer.parseInt(str);
			if(meNum <= 0) {break;}
			
			System.out.print("몇개 주문하시겠습니까? : ");
			str = sc.nextLine();
			if(isNumber(str) == false) {break;}
			num = Integer.parseInt(str);
			if(num <= 0) {break;}
			_alOrname.add(_alName.get(meNum-1));
			_alNum.add(num);
			_alMeprice.add(_alPrice.get(meNum-1) * num);
			
		}
		
		/*// 메뉴 이름으로 추가하는 거
		while(true) {
			System.out.print("주문할 메뉴의 이름을 입력하세요 : ");
			menu = sc.nextLine();
			
			if(menu.equals("종료") || menu.equals(" ")) {
				System.out.println("[ C: 메뉴 추가 || U: 메뉴 수정 || D: 메뉴 삭제 || R: 메뉴 보기 || X or 공백: 종료 ]");
				break;
			}
			
			for(int i = 0; i < _alName.size(); i++) {
				if(_alName.get(i).equals(menu)) {
					System.out.print("몇개 주문하시겠습니까? : ");
					str = sc.nextLine();
					if(str.equals(" ")) {break;}
					if(!isNumber(str)) {break;}
					meNum = Integer.parseInt(str);
					if(meNum <= 0) {break;}
					_alOrname.add(_alName.get(i));
					_alNum.add(meNum);
					_alMeprice.add(_alPrice.get(i) * meNum);
				}
			}
		}
		*/
			
	}
	
	// 주문 보기 메소드
	public static void printOrder(ArrayList<String> _alOrname, ArrayList<Integer> _alNum,
									ArrayList<Integer> _alMeprice, int _sumPrice) {
	
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡ주문판 출력ㅡㅡㅡㅡㅡㅡㅡㅡ");
		for(int i = 0; i < _alNum.size(); i ++) {
			System.out.println((i+1) + "." + _alOrname.get(i) +"\t" + "개수: " + _alNum.get(i) + "\t" + "가격: " + _alMeprice.get(i));
			_sumPrice += _alMeprice.get(i);
		}
		System.out.println("총 가격: " + _sumPrice);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
	}
	
	// 주문 삭제 메소드(메뉴 하나 선택해서 전체 삭제)
	public static void deleteOrder(ArrayList<String> _alOrname, ArrayList<Integer> _alNum,ArrayList<Integer> _alMeprice, int _sumPrice) {
		
		String str = "";
		System.out.println("=============주문 삭제 전=============");
		printOrder(_alOrname, _alNum,_alMeprice,_sumPrice);
		
		System.out.print("하나씩 지우시겠습니까? : ");
		str = sc.nextLine();
		while(true) {
			
			if(str.equals("ㅇㅇ")) {
				System.out.print("삭제할 메뉴의 번호를 입력하세요 : ");
				int i = sc.nextInt();
				if(i == 0) {
					break;
				} else {
					_sumPrice -= _alMeprice.get(i-1);
					_alOrname.remove(i-1);
					_alNum.remove(i-1);
					_alMeprice.remove(i-1);
				}		
			} else {
				_alOrname.clear();
				_alNum.clear();
				_alMeprice.clear();
				_sumPrice = 0;
				break;
			}
			
		}		
		
	}
	
	// 주문 확정 메소드
	public static void confirmedOrder(ArrayList<String> _alOrname, ArrayList<Integer> _alNum,ArrayList<Integer> _alMeprice,
										  ArrayList<String> _mobileN, ArrayList<String> _conMenu, ArrayList<Integer> _conNum, 
										  ArrayList<Integer> _conPrice) {
		
		String str = ""; // 확정 시킬건지 아닐건지 저장하는 변수
		String mobile = ""; // 모바일 번호를 입력받을 변수
		
		// 주문을 확정 시킬건지 아닐건지 물어보는 코드
		System.out.print("주문을 확정시키겠습니까? : ");
		str = sc.nextLine();
		if(strTest(str) == false) {
			return;
		} else {
			System.out.print("적립을 하시겠습니까? : ");
			str = sc.nextLine();
			if(strTest(str) == false || str.equals("ㄴㄴ")) {
				mobile = " ";
				copyOrder(_alOrname, _alNum, _alMeprice, _conMenu, _conNum, _conPrice, _mobileN, mobile);
			}
			if(str.equals("ㅇㅇ") || str.equals("dd")) {
				System.out.print("모바일 번호를 입력하세요: ");
				mobile = sc.nextLine();
				copyOrder(_alOrname, _alNum, _alMeprice, _conMenu, _conNum, _conPrice, _mobileN, mobile);
			}
		}
		
	}
	
	// 주문한 메뉴들을 매출 리스트에 복사하는 메소드
	public static void copyOrder(ArrayList<String> _alOrname, ArrayList<Integer> _alNum, ArrayList<Integer> _alMeprice,
									ArrayList<String> _conMenu, ArrayList<Integer> _conNum, ArrayList<Integer> _conPrice,
									ArrayList<String> _mobileN, String _mobile) {
		
		for(int i = 0; i < _alOrname.size(); i++) {
			_conMenu.add(_alOrname.get(i));
			_conNum.add(_alNum.get(i));
			_conPrice.add(_alMeprice.get(i));
			_mobileN.add(_mobile);
		}
		
		_alOrname.clear();
		_alNum.clear();
		_alMeprice.clear();
		
	}
	
	// 매출 출력 메소드
	public static void printSales(ArrayList<String> _conMenu, ArrayList<Integer> _conNum,
								    ArrayList<Integer> _conPrice, int _sumPrice, ArrayList<String> _mobileN) {
		int i = 0;
		int index = 0;
		int reSum = 0;
		
		while(i < _mobileN.size()) {
			if(i == 0) {
				System.out.println((index+1)+".모바일 번호: " + _mobileN.get(i));
				System.out.println("메뉴: " + _conMenu.get(i) + "\t\t개수: " + _conNum.get(i) + "\t\t가격: " + _conPrice.get(i));
				_sumPrice += _conPrice.get(i);
				reSum += _conPrice.get(i);
			} else if(_mobileN.get(i) == _mobileN.get((i-1))) {
				System.out.println("메뉴: " + _conMenu.get(i) + "\t\t개수: " + _conNum.get(i) + "\t\t가격: " + _conPrice.get(i));
				_sumPrice += _conPrice.get(i);
				reSum += _conPrice.get(i);
			} else {
				System.out.println("총 가격: " + _sumPrice);
				index += 1;
				_sumPrice = 0;
				System.out.println("=========================================================");
				System.out.println((index+1)+".모바일 번호: " + _mobileN.get(i));
				System.out.println("메뉴: " + _conMenu.get(i) + "\t\t개수: " + _conNum.get(i) + "\t\t가격: " + _conPrice.get(i));
				_sumPrice += _conPrice.get(i);
				reSum += _conPrice.get(i);
			}
			i++;
		}
		System.out.println("총 가격: " + _sumPrice);
		System.out.println("=========================================================");
		System.out.println("진짜 총가격: " + reSum);
		System.out.println("=========================================================");
	}
	
	// String 유효성검사 메소드
	public static boolean strTest(String _str) {
		
		// 공백이 아니여야 하고 !isNumber()이 모두 참이라면
		if(_str == null || _str.isEmpty()) {
			return false;
		}
		
		return true;
	}

	// isNaN 메소드
	public static boolean isNumber(String str) {
		// null이거나 비어있으면 false
		if(str == null || str.isEmpty() || Integer.parseInt(str) <= 0) {
			return false;
		}
		
		return str.matches("-?\\d*(\\.\\d+)?");
		
	}

}
