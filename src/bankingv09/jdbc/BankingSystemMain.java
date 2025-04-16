package bankingv09.jdbc;

import java.util.Scanner;

/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다.
 */
public class BankingSystemMain {

	public static Scanner scan = new Scanner(System.in);

	public static void menuShow() {
		System.out.println(
				"########## 메뉴를 입력하세요(ver01)##########");
		System.out.print("1. 계좌 개설 ");
		System.out.println("2. 입금");
		System.out.print("3. 출금 ");
		System.out.println("4. 전체계좌정보 출력");
		System.out.print("5. 지정계좌정보 출력 ");
		System.out.println("6. 계좌삭제");
		System.out.println("7. 프로그램종료");
		System.out.print("메뉴선택 >>>");
	}
	
	public static void main(String[] args) {

		/*
		주소록 관리 프로그램에서 전반적인 기능을 구현하기위한 클래스.
		이런 클래스를 보통 핸들러 혹은 메니저 클래스라고 한다. */
		
		/* 
		 무한루프로 while문 작성. 프로그램을 종료할때까지는 무한히
		 반복하며 친구의 정보를 입력, 수정, 삭제, 검색을 하게된다. */
		
		
		/*
		void showMenu();       // 메뉴출력
		void makeAccount();    // 계좌개설을 위한 함수
		void depositMoney();    // 입    금
		void withdrawMoney(); // 출    금
		void showAccInfo();  // 전체계좌정보출력	
		*/
		while(true) {
			//제일 먼저 메뉴를 출력한다.
			menuShow();
			int choice = scan.nextInt();
			//메뉴 입력을 위한 버퍼(Buffer) 제거
			scan.nextLine();
			//입력받은 정수에 따라 분기해서 각 기능을 실행한다.
			switch(choice) {
			case 1:
				//계좌의 정보 입력
				new MakeAcc().dbExecute();
				break;
			case 2:
				//입금 메뉴
				new Deposit().dbExecute();
				break;
			case 3:
				//출금 메뉴
				new Withdraw().dbExecute();;
				break;
			case 4:
				//전체계좌정보 출력
				new ShowAll().dbExecute();;
				break;
			case 5:
				//검색계좌정보 출력
				new ScAcc().dbExecute();
				break;
			case 6:
				new delAcc().dbExecute();
				break;
			case 7:
				System.out.print("프로그램종료");
				System.exit(0);
			} //switch 끝
		} //while 끝
	} //main 끝
 //class 끝

}
