package bankingv07final;

public class NormalAccount extends Account{

	int nomInt;

	public NormalAccount(String accNum, String accOwner, 
			int balance, int nomInt) {
		
		super(accNum, accOwner, balance);
		this.nomInt = nomInt;
	}

	
	@Override
	public void depositCal(int dpMoney) {
		setBalance(getBalance() + dpMoney +
				(int)(getBalance() * 
			    		((double)nomInt / 100)));
	}
	
	@Override
		public void showAccData() {
			System.out.println("====== 기본계좌 ======");
			super.showAccData();
			System.out.println("기본이자 : " + nomInt);
		}


	@Override
	public String toString() {
		return "[ 보통계좌 ]"+ super.toString() + " nomInt = " + nomInt;
	}
	
	
}
