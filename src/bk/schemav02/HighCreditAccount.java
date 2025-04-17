package bk.schemav02;

/*
 Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 
 높은 이율의 계좌이다. 생성자를 통해서 이율정보(이자비율의정보)를 
 초기화 할수있도록 정의한다.
 */
public class HighCreditAccount extends Account{
	int norInt;
	String credit;
	int HCInt;
	
	public HighCreditAccount(String accNum, String name, int balance, int norInt, String credit, int hCInt) {
		super(accNum, name, balance);
		this.norInt = norInt;
		this.credit = credit;
		HCInt = hCInt;
	}
	
	
}
