package bk.schemav02;

import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다.
 */
public class AccountManager {
	
	static Scanner scan = new Scanner(System.in);
	
	//계좌정보 저장을 위한 인스턴스배열
	 Account[] accounts = new Account[50];
	//개설된 계좌정보 카운트용 변수
	 int accCnt = 0;
		
	
	
	// 계좌개설을 위한 함수
		public void makeAccount() {
			System.out.println("----- 신규계좌 개설 -----");
			System.out.println("1. 보통계좌   2. 신용계좌");
			int choice = scan.nextInt();
			scan.nextLine();
			
			System.out.print("계좌번호 : "); String a = scan.nextLine();
			System.out.print("이    름 : "); String n = scan.nextLine();
			System.out.print("잔    고 : "); int b = scan.nextInt();
			scan.nextLine();
			
			Account ac = null;
			
			if( choice == 1) {
				System.out.print("이자(정수) : "); int inter = scan.nextInt();
				scan.nextLine();
				
				ac = new NormalAccount(a, n, b, inter);
			}
			if( choice == 2) {
				System.out.print("이자(정수) : "); int inter = scan.nextInt();
				scan.nextLine();
				System.out.print("신용등급(A, B, C) : "); String credit = scan.nextLine();
				
				int HCInt = 0;
				if(credit.equalsIgnoreCase("a")) {
					HCInt = 7;					
				}
				else if(credit.equalsIgnoreCase("b")) {
					HCInt = 4;					
				}
				else if(credit.equalsIgnoreCase("a")) {
					HCInt = 2;					
				}
				
				
				ac = new HighCreditAccount(a, n, b, inter, credit, HCInt);
			}
			
			//계좌정보 저장
			accounts[accCnt++] = ac;
			System.out.println("신규계좌 개설 완료");
		}
		// 입    금
		public void depositMoney() {
			System.out.println("-----   입    금   -----");
			System.out.print("계좌번호 : "); String dAcc = scan.nextLine();
			System.out.print("입 금 액 : "); int dpM = scan.nextInt();
			scan.nextLine();
			
			for(int i = 0 ; i < accCnt ; i++) {
				if(accounts[i].getAccNum().equals(dAcc)) {
					accounts[i].deposit(dpM);
					
					System.out.println("입금이 완료되었습니다.");
				}
				else {
					System.out.println("입금 실패");
				}
			}
			
		}
		// 출    금
		public void withdrawMoney() {
			System.out.println("-----   출    금   -----");
			System.out.println("계좌번호 : "); String wAcc = scan.nextLine();
			System.out.println("출 금 액 : "); int wdM = scan.nextInt();
			scan.nextLine();
			
			for(int i = 0 ; i < accCnt ; i++) {
				if(accounts[i].getAccNum().equals(wAcc)) {
					accounts[i].withdraw(wdM);
				}
			}
		} 
		
		// 전체계좌정보출력
		public void showAllInfo() {
			for(int i = 0 ; i < accCnt ; i++) {
				//toString을 오버라이딩 했으므로 인스턴스를 그대로 출력
				Account ac = accounts[i];
				ac.showAccInfo();
			}
			System.out.println("----- 전체계좌정보가 출력됨 -----");
		}  
}
