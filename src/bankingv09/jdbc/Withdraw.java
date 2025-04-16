package bankingv09.jdbc;

import java.sql.SQLException;
import java.sql.Types;

public class Withdraw extends MyConnection {

	public Withdraw() {
		super("education", "1234");
	}
	
	@Override
	public void dbExecute() {
		try {
			csmt = con.prepareCall("{call pcd_bank_wd(?,?,?)}");
			csmt.setString(1, inputValue("출금할 계좌번호"));
			csmt.setString(2, inputValue("출금할 금액"));
			//Prepared 인스턴스를 통해 쿼리문 실행
			csmt.registerOutParameter(3, Types.NUMERIC);
			csmt.execute();
			int rs = csmt.getInt(3);
			switch(rs) {
				case 1:
					System.out.println("출금이 완료되었습니다.");
					break;
				case 2:
					System.out.println("잔액이 부족합니다.");
					break;
			}
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
	}
	
}
