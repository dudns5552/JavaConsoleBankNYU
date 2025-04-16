package bk.schemav01;

import java.util.Scanner;

/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다.
 */
public class BankingSystemMain {

	//키보드 입력을 위한 인스턴스
	static Scanner scan = new Scanner(System.in);
	//매니저의 메소드를 불러오기위한 인스턴스
	static AccountManager AM = new AccountManager();
	
	

	public static void showMenu() {
		System.out.println();
		System.out.println("-----은행 프로그램v.schema-----");
		System.out.println("1. 계좌계설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 프로그램종료");
	}
	
    

	
	public static void main(String[] args) {
		
		
		while(true) {
			//메뉴출력
			showMenu();
			System.out.print("메뉴입력 : ");
			int key = scan.nextInt();
			scan.nextLine(); //버퍼에 남은 엔터키 제거
			
			
			switch(key) {
			case ICustomDefine.MAKE:
//				System.out.println("계좌개설");
				AM.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				System.out.println("입금");
				AM.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				System.out.println("출금");
				AM.withdrawMoney();
				break;
			case ICustomDefine.INQUITE:
//				System.out.println("계좌정보출력");
				AM.showAllInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("종료");
				System.exit(0);
				break;
			}//switch end
		}//while end
	}//main end
}//class end






















