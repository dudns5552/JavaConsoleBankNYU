package bankingv07;

/*
Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
*/
public class HighCreditAccount extends Account{
	
	int nomInt;
	String Credit;
	int HCInt = 0;
	
	public HighCreditAccount(String accNum, String accOwner, int balance, int nomInt, String credit) {
		super(accNum, accOwner, balance);
		this.nomInt = nomInt;
		Credit = credit;
		
		if (credit == "A" || credit == "a") {
			HCInt = 7;
		}
		else if (credit == "B" || credit == "b") {
			HCInt = 4;
		}
		else if (credit == "C" || credit == "c") {
			HCInt = 2;
		}
	}

	
	
	public int getNomInt() {
		return nomInt;
	}

	public int getHCInt() {
		return HCInt;
	}

	@Override
	public void depositCal(int dpMoney) {
		setBalance(getBalance() + dpMoney +
				(int)(getBalance() * 
			    		((double)getNomInt() / 100)) +
				(int)((getBalance() * 
			    		((double)getHCInt() / 100)))
				);
	}

	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("기본이자 : " + nomInt);
		System.out.println("신용등급 : " + Credit);
		System.out.println("--------------------------");
	}



	@Override
	public String toString() {
		return "[ 신용신뢰계좌 ] "+ super.toString() +
				" nomInt = " + nomInt + ", Credit = " + Credit + ", HCInt = " + HCInt;
	}

	
	
}
