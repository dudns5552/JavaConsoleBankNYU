package bk.schemav03;

/*
계좌정보를 표현한 클래스로 NormalAccount, 
HighCreditAccount의 부모클래스가 된다.
 */
public abstract class Account {

	//계좌번호(String형), 이름(String형), 잔액(int형)
	private String accNum;
	private String name;
	private int balance;
	
	//생성자
	public Account(String accNum, String name, int balance) {
		super();
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}

	//게터/세터
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	
	
	
	//입금 메소드
	public abstract void deposit(int dpM);
	
	//출금 메소드
	public void withdraw(int wdM) {
		balance -= wdM;
	}
	
	
	//계좌정보 출력 메소드
	public void showAccInfo() {
		System.out.println("==========================");
		System.out.println("계좌번호 : " + accNum);
		System.out.println("이름     : " + name);
		System.out.println("잔고     : " + balance);
	}
	
	//toString 오버라이딩
	@Override
	public String toString() {
		return "Account [계좌번호 =" + accNum 
				+ ", 이름 =" + name + 
				", 잔고 =" + balance + "]";
	}
	
	
	
}
