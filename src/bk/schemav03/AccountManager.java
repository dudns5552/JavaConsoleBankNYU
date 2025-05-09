package bk.schemav03;

import java.util.InputMismatchException;
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
			
			try {
				
				System.out.println("----- 신규계좌 개설 -----");
				System.out.println("1. 보통계좌   2. 신용계좌");
				int choice = scan.nextInt();
				scan.nextLine();
				
				System.out.print("계좌번호 : "); String a = scan.nextLine();
				System.out.print("이    름 : "); String n = scan.nextLine();
				System.out.print("잔    고 : "); int b = scan.nextInt();
				scan.nextLine();
				
				if(b < 0) {
					System.out.println("잔고는 양수로 입력해주세요");
					return;
				}
				else if(!a.matches("//d+")) {
					System.out.println("계좌번호는 숫자만 입력해주세요");
					return;
				}
				else if(!n.matches("[a-zA-Z가-힣]+")) {
					System.out.println("이름엔 문자만 입력해주세요.");
					return;
				}
				
				
				Account ac = null;
				int inter = 0;
				
				
				switch(choice) {
				
				case 1:
					
					System.out.print("이자(정수 0~10 ) : "); inter = scan.nextInt();
					scan.nextLine();
					
					if(inter < 0 || 10 < inter) {
						System.out.println("이자는 0 ~ 10으로 입력해주세요");
					}
					
					
					ac = new NormalAccount(a, n, b, inter);
					break;
					
				case 2:
					System.out.print("이자(정수 0~10 ) : "); inter = scan.nextInt();
					scan.nextLine();
					System.out.print("신용등급(A, B, C) : "); String credit = scan.nextLine();
					
					int HCInt = 0;
					if(credit.equalsIgnoreCase("a")) {
						HCInt = 7;					
					}
					else if(credit.equalsIgnoreCase("b")) {
						HCInt = 4;					
					}
					else if(credit.equalsIgnoreCase("c")) {
						HCInt = 2;					
					}
					else if(inter < 0 || 10 < inter) {
						System.out.println("이자는 0~10으로 입력해주세요");
					}
					else {
						System.out.println("잘못된 입력입니다.");
						return;
					}
					
					ac = new HighCreditAccount(a, n, b, inter, credit, HCInt);
					break;
					
				default:
					System.out.println("잘못된 입력입니다.");
					makeAccount();
				}
				
				//계좌정보 저장
				accounts[accCnt++] = ac;
				
				//저장 확인 및 알림
				for(int i = 0 ; i < accCnt ; i++) {
					if(ac.equals(accounts[i])) {
						System.out.println("신규계좌 개설 완료");
						return;
					}
				}
				
				System.out.println("계좌개설에 실패하였습니다.");
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
			}
			
		}//계좌 개설 끝
		
		
		// 입    금
		public void depositMoney() {
			try {
				System.out.println("-----   입    금   -----");
				System.out.print("계좌번호 : "); String dAcc = scan.nextLine();
				System.out.print("입 금 액 : "); int dpM = scan.nextInt();
				scan.nextLine();
				
				if(dpM % 500 != 0) {
					System.out.println("입금은 500원 단위로만 할수 있습니다.");
					depositMoney();
				}
				else if(dpM < 0) {
					System.out.println("입금액은 양수로 입력해주세요");
				}
				
				
				for(int i = 0 ; i < accCnt ; i++) {
					if(accounts[i].getAccNum().equals(dAcc)) {
						accounts[i].deposit(dpM);
						
						System.out.println("입금되었습니다.");
					}
					else if(accounts[i].getAccNum().equals(dAcc) == false) {
						System.out.println("계좌가 존재하지 않습니다.");
						return;
					}
					else {
						System.out.println("입금 실패 [예외발생]");
					}
				}
			}
			catch (InputMismatchException e) {
				System.out.println("[ 문자입력 오류 ] 숫자를 입력해주세요");
				e.printStackTrace();
			}
		}
		// 출    금
		public void withdrawMoney() {
			try {
				System.out.println("-----   출    금   -----");
				System.out.println("계좌번호 : "); String wAcc = scan.nextLine();
				System.out.println("출 금 액 : "); int wdM = scan.nextInt();
				scan.nextLine();
				
				if(wdM < 0) {
					System.out.println("출금액은 양수로 입력해주세요");
					withdrawMoney();
				}
				else if(wdM % 1000 != 0) {
					System.out.println("출금은 1000원 단위로 가능합니다.");
				}
				else {
					System.out.println("출금 실패 [예외발생]");
				}
				
				
				
				
				
				for(int i = 0; i < accCnt ; i++) {
					if(accounts[i].getAccNum().equals(wAcc)) {
						if((accounts[i].getBalance() - wdM) > 0) {
							
							accounts[i].withdraw(wdM);
							System.out.println("출금되었습니다.");
							return;
						}
						else if(accounts[i].getBalance() - wdM < 0) {
							
							System.out.println("잔액이 부족합니다. 전액 출금 하시겠습니까?");
							System.out.println("Y) 전액출금   아무키) 출금취소");
							String allWd = scan.nextLine();
							
							if(allWd.equalsIgnoreCase("y")) {
								accounts[i].withdraw(accounts[i].getBalance());
								System.out.printf("%d원이 출금이 되었습니다.", accounts[i].getBalance());
								return;
							}
							else {
								System.out.println("출금을 취소합니다. 메뉴로 돌아갑니다.");
								return;
							}
						}//전액출금 else 끝
					}//계좌비교 if 끝
				}//for 끝
			}//try 끝 
			catch (InputMismatchException e) {
				System.out.println("[ 문자입력 오류 ] 숫자를 입력해주세요");
				e.printStackTrace();
			}
		} 
		
		// 전체계좌정보출력
		public void showAllInfo() {
//			for(Account ac : accounts) {
//				//toString을 오버라이딩 했으므로 인스턴스를 그대로 출력
//				ac.showAccInfo();
//			}
			for(int i = 0 ; i < accCnt ; i++) {
				accounts[i].showAccInfo();
			}
			System.out.println("----- 전체계좌정보가 출력됨 -----");
		}  
}
