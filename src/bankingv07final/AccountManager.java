package bankingv07final;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;


/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다.

 */
public class AccountManager {
	
	static HashSet<Account> Accounts;
	public static Scanner scan = new Scanner(System.in);
	AutoSaver AS = null;

	
	
	public AccountManager(int num) {
		Accounts = new HashSet<>();
	}


	public void makeAccount() {
//		System.out.println("## makeAccount 호출됨 ##");
		
		
		//입력값을 저장할 변수 생성
		String mNum, mOwner;
		int mBalance;
		String mCredit;
		Account MA = null;

		
		try {
			System.out.println("=== 신규계좌개설 ===");
			System.out.println("----- 계좌선택 -----");
			System.out.println("1. 보통계좌");
			System.out.println("2. 신용신뢰계좌");
			System.out.println("3. 특판계좌");
			
			int choice1 = scan.nextInt();
			scan.nextLine();
			
			if(choice1 < 1 || choice1 > 3) {
				System.out.println("잘못된 입력입니다.");
				return;
			}
			
			
			
			
			System.out.print("계좌번호 : "); mNum = scan.nextLine();
			if(mNum.matches(".*[a-zA-Z가-힣.-].*")) {
				System.out.println("계좌번호는 양의 정수만 입력해주세요");
				return;
			}
			
			System.out.print("고객이름 : "); mOwner = scan.nextLine();
			if(!mOwner.matches("[a-zA-Z가-힣ㄱ-ㅎ]+")) {
				System.out.println("이름엔 문자만 입력해주세요.");
				return;
			}
			
			System.out.print("잔고 : "); mBalance = scan.nextInt();
			scan.nextLine();
			if(mBalance < 0) {
				System.out.println("잔고는 양의 정수로 입력해주세요");
				return;
			}
			
			System.out.print("기본이자(0 ~ 10) : ");
			int mInt = scan.nextInt();
			scan.nextLine();
			if(mInt < 0 || 10 < mInt) {
				System.out.println("기본이자는 0~10으로 입력해주세요.");
				return;
			}
			
			
			if( choice1 == 1 ) {
				
				MA = new NormalAccount(mNum, mOwner, mBalance, mInt);
				
			} //일반계좌 끝
			else if( choice1 == 2) {
				
				System.out.print("신용등급(A,B,C등급) : ");
				mCredit = scan.nextLine();
				
				if(mCredit.equalsIgnoreCase("A") || 
						mCredit.equalsIgnoreCase("B") ||
						mCredit.equalsIgnoreCase("C")) {
					MA = new HighCreditAccount(
							mNum, mOwner, mBalance, mInt, mCredit);
				}
				else {
					System.out.println("잘못된 입력입니다.");
					return;
				}
			} //신용신뢰계좌 끝
				
			else if( choice1 == 3 ) {
				
				MA = new SpecialAccount(mNum, mOwner, mBalance, mInt);
			}//특판계좌 끝
			
			if(Accounts.add(MA) == false) {
				System.out.print("중복계좌발견됨. 덮어쓸까요?"
						+ "\n (Y or N)");
				String Over = scan.nextLine();
				
				switch(Over.toUpperCase()) {
				
				case "Y": 
					Accounts.remove(MA);
					Accounts.add(MA);
					System.out.println("새로운 정보로 갱신되었습니다.");
					return;
				case "N":
						System.out.println("덮어쓰기를 취소합니다. 메뉴로 복귀합니다.");
						return;
				default:
					System.out.println("잘못된 입력입니다. 메뉴로 복귀합니다.");
					return;
						
				}//switch 끝
			}//중복계좌발견 끝
			else {
				Accounts.add(MA);
				System.out.println("계좌계설이 완료되었습니다.");
			}
			
		}//try끝
		catch (InputMismatchException e) {
			System.out.println("잘못입력하셨습니까?");
			scan.nextLine();
		}
	}//계좌 개설 끝
	
	/*
	입금 : 
	음수를 입금 할 수 없다.(if문)
	금액 입력시 문자를 입력할 수 없다.
	입금액은 500원단위로 가능하다. Ex) 1000, 1500원 입금가능, 
	1600원 입금불가.
	*/
	public void depositMoney() {
		
		System.out.println("===== 입  금 =====");
		
		boolean isFind = false;
		System.out.println("입금하실 계좌와 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String dpAcc = scan.nextLine();
		int dpMoney =0;
		try {
			System.out.print("입금액 : ");
			dpMoney = scan.nextInt();
			scan.nextLine();
		}
		catch (InputMismatchException e) {
			System.out.println("입금액은 숫자로 입력해주세요.");
			return;
		}
		
		if(0 > dpMoney) {
			System.out.println("음수는 입금이 불가합니다.");
			return;
		}
		else if (dpMoney % 500 != 0) {
			System.out.println("입금은 500원 단위로 가능합니다.");
			return;
		}
		
		
		for(Account ac : Accounts) {
			
			if(dpAcc.compareTo(ac.accNum) == 0) {
				ac.depositCal(dpMoney);	
				System.out.println("입금이 완료되었습니다.");
				isFind = true;
			}
		}
		
		if(isFind == false) {
			System.out.println("##찾는 계좌가 없습니다.##");
			return;
		}
	}//입금 끝

	/*
	출금 : 
	음수를 출금 할 수 없다.(if문)
	잔고보다 많은 금액을 출금요청할 경우 아래와 같이 처리한다.
	잔고가 부족합니다. 금액전체를 출금할까요?
	YES : 금액전체 출금처리
	NO : 출금요청취소
	출금은 1000원 단위로만 출금이 가능하다. Ex)2000원 출금가능, 1100원을 출금불가
	 */
	public void withdrawMoney() {
		
		System.out.println("===== 출  금 =====");
		
		boolean isFind = false;
		System.out.println("출금하실 계좌와 금액을 입력하세요.");
		System.out.print("계좌번호 : "); String wdAcc = scan.nextLine();
		
		
		try {
			System.out.print("출금액 : "); int wdMoney = scan.nextInt();
			scan.nextLine();
			
			if (wdMoney < 0) { 
				System.out.println("출금액은 양수로 입력해주세요.");
				return;
			}
			else if(wdMoney % 1000 != 0) {
				System.out.println("출금은 1000원 단위로 가능합니다.");
				return;
			}
			
			//계좌검색
			for(Account acc : Accounts) {
				
				if(wdAcc.compareTo(acc.getAccNum())== 0 ) {
	//				System.out.println("계좌찾음");
					isFind = true;
					
					if ((acc.getBalance() - wdMoney) >= 0) {
	//					System.out.println("출금 가능");
						
						acc.setBalance(acc.getBalance() - wdMoney);
						System.out.println("출금이 완료되었습니다.");
					}
					else if((acc.getBalance() - wdMoney) < 0){
	//					System.out.println("출금 불가능");
						
						System.out.println("잔고가 부족합니다. 전액을 출금할까요?");
						System.out.println("Y) 전액출금    아무키) 출금취소");
						String YN = scan.nextLine();
						if(YN.equalsIgnoreCase("Y")) {
							acc.setBalance(0);
							System.out.println("전액 출금되었습니다.");
						}
						else {
							System.out.print("출금을 취소합니다. ");
							System.out.println("메뉴로 돌아갑니다.");
							return;
						}
					}//출금 if 끝
				}//계좌검색 if 끝
			}//for문 끝
		
			if(isFind == false) {
				System.out.println("##찾는 계좌가 없습니다.##");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("출금액은 \"숫자로\" 입력해주세요.");
			return;
		}
	}//withdraw 끝
	
	public void showAccInfo() {
		for(Account acc : Accounts) {
			acc.showAccData();
		}
		System.out.println("== 전체계좌정보가 출력되었습니다. ==");
		System.out.println();
		
	}//계좌출력 끝
	
	
	//삭제
	public void deleteAccount() {
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.print("계좌번호 : ");
		String dNum = scan.nextLine();
		//삭제 여부 판단
		int deleteIndex = -1;
		try {
			for(Account ac : Accounts)
			if(dNum.equals(ac.getAccNum())) {
				Accounts.remove(ac);
				System.out.println("계좌를 삭제하였습니다.");
				deleteIndex = 0;
				break;
			}
			if(deleteIndex == -1) {
				System.out.println("삭제된 데이터가 없습니다.");
			}
		}
		catch (ConcurrentModificationException e) {
			System.out.println("삭제중 예외발생?");
		}
		catch (Exception e) {
			System.out.println("예외 발생");
			e.printStackTrace();
		}
	}
	
	void asSubMenu() {
		
		try {
		System.out.println("----------저장옵션---------");
		System.out.println("1. 자동저장 on   2. 자동저장 off");
		int op = scan.nextInt();
		scan.nextLine();
		
		
		
			if(op == 1) {
				if(AS == null) {
					AS = new AutoSaver();
				}
				if(AS.isAlive()) {
					System.out.println("경고 : 이미 자동저장이 실행중입니다.");
					return;
				}
				else {
					AS.start();
					System.out.println("자동저장이 시작됩니다.");
				}
			}
			else if(op == 2) {
				AS.interrupt();
				AS = null;
			}
			else {
				System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다.");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("저장옵션은 1또는 2를 입력해주세요.");
			return;
		}
		catch (Exception e) {}
	}
	
	
}
