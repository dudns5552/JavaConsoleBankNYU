package bankingv05;

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
	
	
	public AccountManager(int num) {
		Accounts = new HashSet<>();
	}


	
	public static void setAccounts(HashSet<Account> accounts) {
		Accounts = accounts;
	}
	public static HashSet<Account> getAccounts() {
		return Accounts;
	}


	public void makeAccount() {
		
		System.out.println("## makeAccount 호출됨 ##");
		//입력값을 저장할 변수 생성
		String mNum, mOwner;
		int mBalance;
		int mInt;
		String mCredit;
		Account MA = null;

		
		System.out.println("***신규계좌개설***");
		try {
			System.out.println("-----계좌선택-----");
			System.out.println("1. 보통계좌");
			System.out.println("2. 신용신뢰계좌");
			
			int choice1 = scan.nextInt();
			scan.nextLine();
			
			if(choice1 < 1 || choice1 > 2) {
				System.out.println("잘못된 입력입니다.");
				return;
			}
			
			
			System.out.print("계좌번호 : "); mNum = scan.nextLine();
			System.out.print("고객이름 : "); mOwner = scan.nextLine();
			System.out.print("잔고 : "); mBalance = scan.nextInt();
			scan.nextLine();
			
			if( choice1 == 1 ) {
				System.out.println("기본이자%(정수형태로입력) : ");
				mInt = scan.nextInt();
				scan.nextLine();
				
				MA = new NormalAccount(mNum, mOwner, mBalance, mInt);
				
			}
			else if( choice1 == 2) {
				System.out.println("기본이자%(정수형태로입력) : ");
				mInt = scan.nextInt();
				scan.nextLine();
				
				System.out.println("신용등급(A,B,C등급) : ");
				mCredit = scan.nextLine();
				
				MA = new HighCreditAccount(
								mNum, mOwner, mBalance, mInt, mCredit);
				
				
			}
			
			if(Accounts.add(MA) == false) {
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String Over = scan.nextLine();
				
				if(Over.equalsIgnoreCase("Y")) {
					Accounts.remove(MA);
					Accounts.add(MA);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else if(Over.equalsIgnoreCase("N")) {
					System.out.println("덮어쓰기를 취소합니다. 메뉴로 복귀합니다.");
					return;
				}
				else {
					System.out.println("잘못된 입력입니다. 메뉴로 복귀합니다.");
					return;
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("잘못입력하셨습니까?");
		}

		System.out.println("계좌계설이 완료되었습니다.");
	}	
	
	/*
	입금 : 
	음수를 입금 할 수 없다.(if문)
	금액 입력시 문자를 입력할 수 없다.
	입금액은 500원단위로 가능하다. Ex) 1000, 1500원 입금가능, 
	1600원 입금불가.
	*/
	public void depositMoney() {
		
		System.out.println("***입  금***");
		
		boolean isFind = false;
		System.out.println("입금하실 계좌와 금액을 입력하세요.");
		System.out.println("계좌번호 : ");
		String dpAcc = scan.nextLine();
		int dpMoney =0;
		try {
			System.out.println("입금액 : ");
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
			
		Iterator<Account> itr = Accounts.iterator();
		while (itr.hasNext()) {
			Account account = itr.next();
			if(dpAcc.compareTo(account.accNum)== 0 ) {
				if(account instanceof NormalAccount) {
					((NormalAccount)account).setBalance(
						    ((NormalAccount)account).getBalance() +
						    (int)(((NormalAccount)account).getBalance() * 
						    		((double)((NormalAccount)account).getNomInt() / 100)) +
						    dpMoney
						);
					System.out.println("입금이 완료되었습니다.");
					isFind = true;
				}
				else if (account instanceof HighCreditAccount) {
					((HighCreditAccount)account).setBalance(
						    ((HighCreditAccount)account).getBalance() +
						    (int)(((HighCreditAccount)account).getBalance() * 
						    		((double)((HighCreditAccount)account).getNomInt() / 100)) +
						    (int)(((HighCreditAccount)account).getBalance() * 
						    		((double)((HighCreditAccount)account).getHCInt() / 100)) +
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
		
		System.out.println("***출  금***");
		
		boolean isFind = false;
		System.out.println("출금하실 계좌와 금액을 입력하세요.");
		System.out.println("계좌번호 : ");
		String wdAcc = scan.nextLine();
		
		int wdMoney = 0;
		
		try {
			System.out.println("출금액 : ");
			wdMoney = scan.nextInt();
			scan.nextLine();
		}
		catch (InputMismatchException e) {
			System.out.println("출금액은 \"숫자로\" 입력해주세요.");
			return;
		}
		
		if (wdMoney < 0) {
			System.out.println("출금액은 양수로 입력해주세요.");
			return;
		}
		else if(wdMoney % 1000 != 0) {
			System.out.println("출금은 1000원 단위로 가능합니다.");
			return;
		}
		
		Iterator<Account> itr = Accounts.iterator();
		while (itr.hasNext()) {
			
			Account account = itr.next();
			
			if(wdAcc.compareTo(account.getAccNum())== 0 ) {
				
				isFind = true;
				
				if ((account.getBalance() - wdMoney) >= 0) {
					
					account.setBalance(account.getBalance() - wdMoney);
					System.out.println("출금이 완료되었습니다.");
				}
				else {
					System.out.println("잔고가 부족합니다. 전액을 출금할까요?");
					String YN = scan.nextLine();
					if(YN.equalsIgnoreCase("Y")) {
						account.setBalance(account.getBalance() - 
								account.getBalance());
						System.out.println("전액 출금되었습니다.");
					}
					else if (YN.equalsIgnoreCase("N")) {
						System.out.print("출금을 취소합니다. ");
					}
					else {
						System.out.print("잘못된 입력입니다. ");
					}
					System.out.println("메뉴로 돌아갑니다.");
				}
			}
		}
		
		if(isFind == false) {
			System.out.println("##찾는 계좌가 없습니다.##");
		}
	}
	
	public void showAccInfo() {
		System.out.println("## showAllData 호출됨 ##");
		
		
		Iterator itr = Accounts.iterator();
		while( itr.hasNext()){
			Object account = itr.next();
			if(account instanceof NormalAccount) {
				((NormalAccount)account).showAccData();
			}
			else if(account instanceof HighCreditAccount) {
				((HighCreditAccount)account).showAccData();
			}
		}
			System.out.println("##전체계좌정보가 출력되었습니다.");
		
	}
	
	public void deleteAccount() {
//		System.out.println("## deleteIno 호출됨 ##");
		System.out.print("삭제할 계좌번호를 입력하세요.");
		System.out.println("계좌번호 : ");
		String dNum = scan.nextLine();
		//삭제 여부 판단
		int deleteIndex = -1;
		
		Iterator<Account> itr = Accounts.iterator(); //1
		while(itr.hasNext()) { //2
			Account account = itr.next(); //3
			if(dNum.equals(account.getAccNum())) {
				Accounts.remove(account);
				System.out.println("계좌를 삭제하였습니다.");
				deleteIndex = 0;
			}
		}
		if(deleteIndex == -1) {
			System.out.println("## 삭제된 데이터가 없습니다. ##");
		}
	}
	
	
	
	
}





















