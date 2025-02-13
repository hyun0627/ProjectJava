package caffe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sales {

	private Scanner sc; 
	private Test t1;	
	public Connection dbconn;
	
	Menu m;
	Order o;
	
	Sales(Menu _m, Order _o) {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		dbconn = null;
		m = _m;
		o = _o;
		
	}
	
	public void run() throws SQLException {
		
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
	}
	
	public void printSales() throws SQLException {
		
		int sot = 0;
		m.setConnection();
					
		Statement sales = this.m.dbconn.createStatement();
	    String printsales = "select id, menu, qty, total, mobile from revenue order by id";
	    ResultSet sa = sales.executeQuery(printsales);
	    	
	    System.out.println("=============주문 내역 출력====================");
	    System.out.println("   메뉴명   " + "   수량  " + "  가격    " + "        모바일 번호    ");
	    	
	    while(sa.next()) {		
	    	System.out.println(sa.getInt("id") + "." + sa.getString("menu") + "\t" + sa.getInt("qty") + "개\t" + 
	    						 sa.getInt("total") + "원" + "\t\t" + sa.getString("mobile"));
	   	}
	    String stotal = "select sum(total) retotal from revenue";
	    ResultSet st = sales.executeQuery(stotal); 
	    if(st.next()) {
	   		sot = st.getInt("retotal");
	   	} 
	   	System.out.println("총 금액: " + sot);
	    System.out.println("============================================");
	    	
	    st.close();
	    sa.close();
	    sales.close();		
		m.disConnection();
		
	}
	
}
