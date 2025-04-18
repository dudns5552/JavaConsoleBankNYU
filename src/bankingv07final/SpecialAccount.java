package bankingv07final;

public class SpecialAccount extends NormalAccount{

	int dpCnt = 0;

	public SpecialAccount(String accNum, String accOwner, int balance, int nomInt) {
		super(accNum, accOwner, balance, nomInt);
	}
	
	@Override
	public void depositCal(int dpMoney) {
		++dpCnt; 
		/*
		입금을 카운트하기 위한 선위증가 변수
		후위증가로 할경우 입금한뒤 카운트가 증가하게 되어
		짝수입금마다 축하금지원을 하여야 하는데
		제대로 인식이 되질 않게된다.
		 */
		
		if(dpCnt % 2 == 0) {
			dpMoney += 500;
		}
		
		//노말계좌와 달라진건 축하금 뿐이므로 부모클래스 입금메서드 호출
		super.depositCal(dpMoney);
	}
	
	
	
	
	@Override
	public void showAccData() {
		System.out.println("====== 특판계좌 ======");
		System.out.println("계좌번호 : "+ accNum);
		System.out.println("고객이름 : "+ accOwner);
		System.out.println("잔    고 : "+ balance);
		System.out.println("기본이자 : "+ nomInt);
		System.out.println("입금회수 : " + dpCnt);
	}
	
	@Override
	public String toString() {
		return super.toString() + "입금회수 : " + dpCnt;
	}
	
	
	
}
