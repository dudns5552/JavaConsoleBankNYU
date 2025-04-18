package bankingv07final;

public class HighCreditAccount extends Account{
	
	int nomInt;
	String Credit;
	
	public HighCreditAccount(String accNum, String accOwner, int balance, int nomInt, String credit) {
		super(accNum, accOwner, balance);
		this.nomInt = nomInt;
		Credit = credit;
	}

	//신용등급에 따른 신용이자 계산 함수
	public int HCInt(String Credit) {
		int HCInt = 0;
		if(Credit.equalsIgnoreCase("a")) {
			HCInt = ICustomDefine.A;
		}
		else if(Credit.equalsIgnoreCase("b")) {
			HCInt = ICustomDefine.B;
		}
		else if(Credit.equalsIgnoreCase("c")) {
			HCInt = ICustomDefine.C;
		}
		return HCInt;
		
	}
	
	@Override
	public void depositCal(int dpMoney) {
		setBalance(getBalance() + dpMoney +	//입금액 계산
				(int)(getBalance() * 
			    		((double)nomInt / 100)) +	//일반이자 계산
				(int)((getBalance() * 
			    		((double)(HCInt(Credit)) / 100))) //신용이자 계산
				);
	}

	@Override
	public void showAccData() {
		System.out.println("==== 신용신뢰계좌 ====");
		super.showAccData();
		System.out.println("기본이자 : " + nomInt);
		System.out.println("신용등급 : " + Credit);
	}



	@Override
	public String toString() {
		return "[ 신용신뢰계좌 ] "+ super.toString() +
				" nomInt = " + nomInt + ", Credit = " + Credit;
	}

	
	
}
