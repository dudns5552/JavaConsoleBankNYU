package bankingv03;

/*
NormalAccount 
	보통예금계좌 > 최소한의 이자를 지급하는 자율 입출금식 계좌
	Account의 자식클래스로 보통예금계좌를 의미한다.
	생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
 */
public class NormalAccount extends Account{

	int nomInt;

	public NormalAccount(String accNum, String accOwner, 
			int balance, int nomInt) {
		
		super(accNum, accOwner, balance);
		this.nomInt = nomInt;
	}

	
	public int getNomInt() {
		return nomInt;
	}

	@Override
		public void showAccData() {
		
			super.showAccData();
			System.out.println("기본이자 : " + nomInt);
			System.out.println("--------------------------");
		}
	
}
