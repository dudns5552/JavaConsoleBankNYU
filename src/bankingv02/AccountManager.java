package bankingv02;

import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다.

구현해야하는 기능
	void showMenu();       // 메뉴출력
	void makeAccount();    // 계좌개설을 위한 함수
	void depositMoney();    // 입    금
	void withdrawMoney(); // 출    금
	void showAccInfo();  // 전체계좌정보출력	
 */
public class AccountManager {
	
	private Account[] Accounts;
	private int numOfAccount;
	public static Scanner scan = new Scanner(System.in);
	
	
	public AccountManager(int num) {
		Accounts = new Account[num];
		numOfAccount = 0;
	}


	public void makeAccount() {
		
		System.out.println("## makeAccount 호출됨 ##");
		//입력값을 저장할 변수 생성
		String mNum, mOwner;
		int mBalance;
		int mInt;
		String mCredit;
		//공통 정보 3가지를 입력받음
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		int choice = scan.nextInt();
		scan.nextLine();
		
		
		System.out.print("계좌번호 : "); mNum = scan.nextLine();
		System.out.print("고객이름 : "); mOwner = scan.nextLine();
		System.out.print("잔고 : "); mBalance = scan.nextInt();
		scan.nextLine();
		
		if( choice == 1) {
			System.out.println("기본이자%(정수형태로입력) : ");
			mInt = scan.nextInt();
			scan.nextLine();
			
			NormalAccount NA = new NormalAccount(mNum, mOwner, mBalance, mInt);
			
			Accounts[numOfAccount++] = NA;
			
		}
		else if( choice == 2) {
			System.out.println("기본이자%(정수형태로입력) : ");
			mInt = scan.nextInt();
			scan.nextLine();
			
			System.out.println("신용등급(A,B,C등급) : ");
			mCredit = scan.nextLine();
			
			HighCreditAccount CA = 
					new HighCreditAccount(
							mNum, mOwner, mBalance, mInt, mCredit);
			
			Accounts[numOfAccount++] = CA;
		}

		System.out.println("계좌계설이 완료되었습니다.");
		
	}	
	
	/*
	입금과정
	입금할 계좌와 입금할 금액 입력
	입금성공여부
	 */
	public void depositMoney() {
		
		System.out.println("***입  금***");
		
		boolean isFind = false;
		System.out.println("입금하실 계좌와 금액을 입력하세요.");
		System.out.println("계좌번호 : ");
		String dpAcc = scan.nextLine();
		System.out.println("입금액 : ");
		int dpMoney = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < numOfAccount; i++) {
			if(dpAcc.compareTo(Accounts[i].getAccNum())== 0 ) {
				if(Accounts[i] instanceof NormalAccount) {
					((NormalAccount)Accounts[i]).setBalance(
						    ((NormalAccount)Accounts[i]).getBalance() +
						    (int)(((NormalAccount)Accounts[i]).getBalance() * 
						    		((double)((NormalAccount)Accounts[i]).getNomInt() / 100)) +
						    dpMoney
						);
					System.out.println("입금이 완료되었습니다.");
					isFind = true;
				}
				else if (Accounts[i] instanceof HighCreditAccount) {
					((HighCreditAccount)Accounts[i]).setBalance(
						    ((HighCreditAccount)Accounts[i]).getBalance() +
						    (int)(((HighCreditAccount)Accounts[i]).getBalance() * 
						    		((double)((HighCreditAccount)Accounts[i]).getNomInt() / 100)) +
						    (int)(((HighCreditAccount)Accounts[i]).getBalance() * 
						    		((double)((HighCreditAccount)Accounts[i]).getHCInt() / 100)) +
						    dpMoney
						);
					System.out.println("입금이 완료되었습니다.");
					isFind = true;
				}
			}
		}
		if(isFind == false) {
			System.out.println("##찾는 계좌가 없습니다.##");
		}
	}
	
	public void withdrawMoney() {
		
		System.out.println("***출  금***");
		
		boolean isFind = false;
		System.out.println("출금하실 계좌와 금액을 입력하세요.");
		System.out.println("계좌번호 : ");
		String wdAcc = scan.nextLine();
		System.out.println("출금액 : ");
		int wdMoney = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < numOfAccount; i++) {
			/*
			문자열 비교를 위한 메서드 중 compareTo()를 사용해서
			검색 기능 구현. "문자열1.compareTo(문자열2)" 형식으로
			사용하고 일치하는 경우 0을 반환한다. */
			if(wdAcc.compareTo(Accounts[i].getAccNum())== 0 ) {
				
				isFind = true;
				
				if ((Accounts[i].getBalance() - wdMoney) >= 0) {
					
					Accounts[i].setBalance(Accounts[i].getBalance()- wdMoney);
					System.out.println("출금이 완료되었습니다.");
				}
				else {
					System.out.println("잔액이 부족합니다. "
							+ "메인메뉴로 이동합니다.");
					return;
				}
			}
		}
		
		if(isFind == false) {
			System.out.println("##찾는 계좌가 없습니다.##");
		}
	}
	
	public void showAccInfo() {
		System.out.println("## showAllData 호출됨 ##");
		/*
		고딩친구반복 : 저장된 정보의 갯수만큼 반복해서 전체 정보를 출력한다.
		 	4개의 멤버변수를 모두 출력한다.*/
		for(int i = 0; i < numOfAccount; i++) {
			Accounts[i].showAccData();
		}
			System.out.println("##전체계좌정보가 출력되었습니다.");
		
	}
	
}





















