package bankingv06;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다.
 */
public class BankingSystemMain {

	public static Scanner scan = new Scanner(System.in);


	public static void menuShow() {
		System.out.println(
				"########## 메뉴를 입력하세요(ver04)##########");
		System.out.print("1. 계좌 개설 ");
		System.out.print("2. 입금");
		System.out.println("3. 출금 ");
		System.out.print("4. 전체계좌정보 출력 ");
		System.out.println("5. 계좌삭제");
		System.out.println("6. 프로그램종료");
		System.out.print("메뉴선택 >>>");
	}
	
	public static void main(String[] args) throws MenuSelectException {

	
		AccountManager AM = new AccountManager(50);
		ObjectOutputStream Out = new ObjectOutputStream();
		ObjectInputStream In = new ObjectInputStream();

		while(true) {
			
			In.readAcc();
			
			menuShow();
			int choice = scan.nextInt();
			scan.nextLine();
			
			if(choice < 1 || 7 < choice) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
			}
			
			try {
				switch(choice) {
				case 1:
					//계좌의 정보 입력
					AM.makeAccount();
					break;
				case 2:
					//입금 메뉴
					AM.depositMoney();
					break;
				case 3:
					//출금 메뉴
					AM.withdrawMoney();
					break;
				case 4:
					//전체계좌정보 출력
					AM.showAccInfo();
					break;
				case 5:
					AM.deleteAccount();
					break;
				case 6:
					AM.asSubMenu();
					break;
				case 7:
					System.out.print("프로그램종료");
					Out.saveAcc();
					return;
				} //switch 끝
			}//try 끝
			catch (InputMismatchException e) {
				System.out.println("메뉴는 1~7 사이의 \"정수만\" 입력해주세요");
			}
		} //while 끝
	} //main 끝
 //class 끝
}
