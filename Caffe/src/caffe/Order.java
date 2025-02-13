package caffe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Order {
	
	private Scanner sc;
	private Test t1;
	public Connection dbconn;
	private Menu m;
	
	Order(Menu _m) {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		dbconn = null;
		m = _m;
		
	}
	
	public void run() throws SQLException {
		
		String str = "";
		System.out.println("===============================================================");
		System.out.println("주문 관리프로그램을 실행합니다.");
		
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
	}

	// 주문 추가 메소드
	public void addOrder() {
			
			int qty = 0; // 메뉴 개수
			String mnum = "";
			String qt = "";
			int num = 0;
			
			m.printMenu();
			
			while(true) {
				
				System.out.print("주문할 메뉴 번호를 입력하세요: ");
				mnum = sc.nextLine();
				if(t1.isNumber(mnum) == false) { break;}
				num = Integer.parseInt(mnum);
				
				System.out.print("수량을 입력하세요: ");
				qt = sc.nextLine();
				if(t1.isNumber(qt) == false) {break;}
				qty = Integer.parseInt(qt);
				
				String orderadd = "insert into jumoon set menu = (select name from menu where id = "+ num +")"
						  + ", qty = " + qty + ", total = (select price from menu where id = " + num+")" + "*" + qty;
				m.setConnection();
				try {			
					Statement st = this.m.dbconn.createStatement();
					st.executeUpdate(orderadd);
					st.close();
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				m.disConnection();
				
				
			} // while문 종료
			
		} // addOrder메소드 종료
		
	// 주문 출력 메소드
	public void printOrder() {
		
		int sot = 0;
		m.setConnection();
		
		try {
			
			Statement pr = this.m.dbconn.createStatement();
	    	String printorder = "select id, menu, qty, total from jumoon order by id";
	    	ResultSet po = pr.executeQuery(printorder);
	    	
	    	System.out.println("====주문 내역 출력========");
	    	System.out.println("   메뉴명   " + "    수량  " + "    가격    ");
	    	
	    	while(po.next()) {		
	    		System.out.println(po.getInt("id") + "." + po.getString("menu") + "\t" + po.getInt("qty") + "개\t" + 
	    							 po.getInt("total") + "원");
	    	}
	    	String stotal = "select sum(total) retotal from jumoon";
	    	ResultSet p1 = pr.executeQuery(stotal); 
	    	if(p1.next()) {
	    		sot = p1.getInt("retotal");
	    	} 
	    	System.out.println("총 금액: " + sot);
	    	
	    	p1.close();
	    	po.close();
	    	pr.close();
	    	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		m.disConnection();
			
	}

	// 주문 삭제 메소드
	public void deleteOrder() throws SQLException {
		
		String str = "";
		int number = 0;
		String i = "";
		System.out.println("=============주문 삭제 전============");
		printOrder();
		
		System.out.print("하나씩 지우시겠습니까? : ");
		str = sc.nextLine();
		
		while(true) {
			
			if(str.equals("ㅇㅇ")) {
				
				System.out.print("삭제할 주문표 번호를 입력하세요 : ");
				i = sc.nextLine();
				if(t1.isNumber(i) == false) { break;}		
				number = Integer.parseInt(i);
				if(number == 0) { break;} 
				
					String delorder = "delete from jumoon where id = " + number;
					m.setConnection();
					Statement st = this.m.dbconn.createStatement();
					st.executeUpdate(delorder);
					st.close();
				
					m.disConnection();

				} else {
					
					System.out.print("정말로 모든 데이터를 삭제하시겠습니까?");
					str = sc.nextLine();
					if(t1.strTest(str) == false ) { break;}
					if(str.equals("예")) {
						
						String delall = "delete from jumoon";
						m.setConnection();
						Statement st = this.m.dbconn.createStatement();
						st.executeUpdate(delall);
						st.close();
					
						m.disConnection();
							
					}
				}

		}
	}

	// 주문 확정 메소드
	public void confirmedOrder() throws SQLException{
		
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
	}
	
	// 테이블 카피 메소드
	public void copyOrder(String _mobile) throws SQLException {
		
		m.setConnection();
		Statement copy = this.m.dbconn.createStatement();
    	
    	String cp = "insert into revenue(menu, qty, total, mobile) select menu,qty,total,'" + _mobile + "' from jumoon";
    	copy.executeUpdate(cp);
    	
    	String del = "delete from jumoon";
    	copy.executeUpdate(del);
    	copy.close();
    	m.disConnection();
    	
	}
	
}