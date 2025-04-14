package bankingv02;

/*
계좌정보를 표현한 클래스로 NormalAccount, 
HighCreditAccount의 부모클래스가 된다.
 */
public class Account {
	
	private String accNum;
	private String accOwner;
	private int balance;
	
	
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


	public void showAccData() {
		System.out.println("***계좌정보출력***");
		System.out.println("-------------");
		System.out.println("계좌번호 : "+ accNum);
		System.out.println("고객이름 : "+ accOwner);
		System.out.println("잔고 : "+ balance);
	}
	
}
