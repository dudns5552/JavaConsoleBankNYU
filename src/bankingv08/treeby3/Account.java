package bankingv08.treeby3;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/*
계좌정보를 표현한 클래스로 NormalAccount, 
HighCreditAccount의 부모클래스가 된다.
 */
abstract public  class Account implements Serializable{
	
	static Scanner scan = new Scanner(System.in);
	
	String accNum;
	String accOwner;
	int balance;

	public Account(String accNum, String accOwner, int balance) {
		super();
		this.accNum = accNum;
		this.accOwner = accOwner;
		this.balance = balance;
	}

	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccOwner() {
		return accOwner;
	}
	public void setAccOwner(String accOwner) {
		this.accOwner = accOwner;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public abstract void depositCal(int dpMoney);

	public void showAccData() {
		System.out.println("***계좌정보출력***");
		System.out.println("-------------");
		System.out.println("계좌번호 : "+ accNum);
		System.out.println("고객이름 : "+ accOwner);
		System.out.println("잔고 : "+ balance);
	}
	
	
	@Override
	public int hashCode() {
		int returnCode2 = Objects.hash
				(getAccNum());
		return returnCode2;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Account account= (Account)obj;
		/*
		age는 int형이므로 비교연산자를 통해 비교한다. subject는
		String이므로 equals()를 통해 비교해야 한다.
		즉, 우리가 정한 기준에 따라 멤버변수간의 값이 동일한지 비교하도록
		오버라이딩 하면된다. */
		if( account.getAccNum().equals(this.getAccNum())) {
			/*
			모든내용이 일치하면 true를 반환한다. 
			그러면 set에는 추가되지 않는다. */
				return true;
			}
		else {
			//내용이 다르다면 add에 성공하게된다.
			return false;
		}
	}

	@Override
	public String toString() {
		return "계좌번호 = " + accNum + ", 소유주 = " + accOwner + ", 잔고 = " + balance;
	}
	
}
