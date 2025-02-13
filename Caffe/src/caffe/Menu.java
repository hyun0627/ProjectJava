package caffe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
	
	private Scanner sc;
	private Test t1;
	public Connection dbconn;
	
	Menu() {
		
		sc = new Scanner(System.in);
		t1 = new Test();
		this.dbconn = null;

	}
	
	public void run() throws SQLException {
		
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
					updateMenu();
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
			// 입력받은 값을 sql문으로 실행시키는 작업
			String insertsql = "insert into menu set name ='" + name +"', price = " + price;
			this.setConnection();
			try {
				
				Statement st = this.dbconn.createStatement();
				st.executeUpdate(insertsql);
				st.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			this.disConnection();
		}
	}
	
	public void updateMenu() throws SQLException {

		String name = "";
		String i = "";
		String str = "";
		String pr = "";
		int uprice = 0;
		while(true) {
			
			System.out.print("변경할 메뉴 번호를 입력하시오: ");
			i = sc.nextLine();
			if(t1.isNumber(i) == false) { break;}
			
			System.out.print("하나만 변경하시겠습니까? 둘 다 변경하시겠습니까?");
			str = sc.nextLine();
			if(t1.strTest(str) == false) { break;}
			
			if(str.equals("하나")) {
				System.out.print("이름을 변경하시겠습니까? 가격을 변경하시겠습니까?");
				str = sc.nextLine();
				if(t1.strTest(str) == false) { break;}
				
				// 이름만 수정
				if(str.equals("이름")) {
					System.out.print("어떤 이름으로 변경하시겠습니까? : ");
					name = sc.nextLine();
					
					String updatesql = "update menu set name ='" + name + "',updated = current_timestamp()" + " where id = " + i;
					this.setConnection();
					Statement st = this.dbconn.createStatement();
					st.executeUpdate(updatesql);
					st.close();
					this.disConnection();
					
					// 가격만 수정
				} else {
					System.out.print("어떤 가격으로 변경하시겠습니까? : ");
					pr = sc.nextLine();
					if(t1.strTest(pr) == false ) { break;}
					uprice = Integer.parseInt(pr);
					
					String updatesql = "update menu set price = " + uprice + ",updated = current_timestamp()" + " where id = " + i;
					this.setConnection();
					Statement st = this.dbconn.createStatement();
					st.executeUpdate(updatesql);
					st.close();
					this.disConnection();
				}
				// 한번에 두개 다 수정
			} else {
				System.out.print("어떤 이름으로 변경하시겠습니까? : ");
				name = sc.nextLine();
				
				System.out.print("얼마로 수정하시겠습니까? : ");
				pr = sc.nextLine();
				uprice = Integer.parseInt(pr);
				
				String updatesql = "update menu set name ='" + name +"', price = " + uprice + 
						 "',updated = current_timestamp()" + " where id = " + i;
				this.setConnection();
				Statement st = this.dbconn.createStatement();
				st.executeUpdate(updatesql);
				st.close();
				this.disConnection();
				
			}
		}
		
	}

	public void deleteMenu() throws SQLException {
		
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
			
			String updatesql = "delete from menu " + "where id = " + number;
			this.setConnection();
			Statement st = this.dbconn.createStatement();
			st.executeUpdate(updatesql);
			st.close();
			
			this.disConnection();
			
		} // while문 종료
			
		System.out.println("=============메뉴 삭제 후=============");
		printMenu();
		
	}
	
	public void printMenu() {
		
		this.setConnection();
		
		try {
			
			Statement st = this.dbconn.createStatement();
	    	String sql = "select id, name, price from menu order by id";
	    	ResultSet rs = st.executeQuery(sql);
	    	
	    	System.out.println("====메뉴판 출력========");
	    	System.out.println("   메뉴명   " + "    가격  ");
	    	
	    	while(rs.next()) {		
	    		System.out.println(rs.getInt("id") + "." + rs.getString("name") + "\t" + rs.getInt("price") + "원");
	    	}
	    	
	    	rs.close();
	    	st.close();
	    	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.disConnection();
		
	}
	
	public void setConnection() {
		
	    String dbDriver = "com.mysql.cj.jdbc.Driver";
	    String dbUrl = "jdbc:mysql://127.0.0.1:3306/kdt";
	    String dbUser = "root";
	    String dbPassword = "himedia";
	    
	    try {
	    	
	    	Class.forName(dbDriver);
	    	this.dbconn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	    	
	    } catch(SQLException e) {
	    	
	    	System.out.println("DB Connection 실패");
	    	e.printStackTrace();
	    	
	    } catch(ClassNotFoundException e) {
	    	
	    	System.out.println("DB Connection 실패");
	    	e.printStackTrace();
	    	
	    }
	}
	
	public void disConnection() {
		
		try {
			this.dbconn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
