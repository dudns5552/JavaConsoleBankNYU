package bk.schemav02;

/*
Account의 자식클래스로 보통예금계좌를 의미한다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
 */
public class NormalAccount extends Account{
	int norInt;

	public NormalAccount(String accNum, String name, int balance, int norInt) {
		super(accNum, name, balance);
		this.norInt = norInt;
	}
	
	
	@Override
	public void deposit(int dpM) {
		
		int gb = getBalance();
		double inter = (double)norInt/100;
		
		setBalance
		(
			gb + (int)(gb * inter)+ dpM
				
		);
		
		
		
	}
	
	
}
