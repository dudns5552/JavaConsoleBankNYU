package bankingv07;

/*
'특판계좌' 상품으로 입금시마다 기본이자를 지급하는것은 NormalAccount와 동일하지만 
짝수번째 입금에는 500원씩 축하금을 별도로 지급한다.
 
절차는 다음과 같다.
	계좌계설(입금으로 간주하지 않는다.)
	입금1회차(홀수번째이므로 NormalAccount와 동일하다.)
		잔고 + (잔고 * 기본이자) + 입금액 
	입금2회차(짝수번째는 축하금이 지급된다.)
		잔고 + (잔고 * 기본이자) + 입금액 + 500원 
		
따라서 계좌정보를 출력할때 현재 몇회차 입금이 되었는지가 디스플레이 되어야 한다. 
 */
public class SpecialAccount extends NormalAccount{

	int dpCnt = 0;

	public SpecialAccount(String accNum, String accOwner, int balance, int nomInt) {
		super(accNum, accOwner, balance, nomInt);
	}
	
	@Override
		public void depositCal(int dpMoney) {
			++dpCnt;
			if(dpCnt % 2 == 0) {
				dpMoney += 500;
			}
			super.depositCal(dpMoney);
		}
	
	
	
	
	@Override
	public void showAccData() {
		super.showAccData();
		System.out.println("입금회수 : " + dpCnt);
	}
	
	@Override
	public String toString() {
		return super.toString() + "입금회수 : " + dpCnt;
	}
	
	
	
}
