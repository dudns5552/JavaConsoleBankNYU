package bk.schemav03;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다.
 */
public class BankingSystemMain {

	//키보드 입력을 위한 인스턴스
	static Scanner scan = new Scanner(System.in);
	//매니저의 메소드를 불러오기위한 인스턴스
	static AccountManager AM = new AccountManager();
	static MenuSelectException MSE = new MenuSelectException();
	
	

	public static void showMenu() {
		System.out.println();
		System.out.println("----- 은행 프로그램v3.schema -----");
		System.out.println("1. 계좌계설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 프로그램종료");
	}
	
    

	
	public static void main(String[] args) {
		
		
		while(true) {
			try {
				//메뉴출력
				showMenu();
				System.out.print("메뉴입력 : ");
				int key = scan.nextInt();
				scan.nextLine(); //버퍼에 남은 엔터키 제거
				
				
				switch(key) {
				case ICD.MAKE:
	//				System.out.println("계좌개설");
					AM.makeAccount();
					break;
				case ICD.DEPOSIT:
	//				System.out.println("입금");
					AM.depositMoney();
					break;
				case ICD.WITHDRAW:
	//				System.out.println("출금");
					AM.withdrawMoney();
					break;
				case ICD.INQUITE:
	//				System.out.println("계좌정보출력");
					AM.showAllInfo();
					break;
				case ICD.EXIT:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
				default: //지정된 숫자 이외 숫자 예외처리
					MSE.exception();
					break;
				}//switch end
			}//try end
			catch (InputMismatchException e) {
				System.out.println("[문자입력오류]숫자를 입력해주세요.");
				e.printStackTrace();
			}
			
			
			
		}//while end
	}//main end
}//class end






















