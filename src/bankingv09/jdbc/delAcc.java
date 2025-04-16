package bankingv09.jdbc;

import java.sql.SQLException;
import java.sql.Types;

public class delAcc extends MyConnection {

	public delAcc() {
		super("education", "1234");
	}
	
	
	@Override
	public void dbExecute() {
		try {
			csmt = con.prepareCall("{call pcd_bank_del(?,?)}");
			csmt.setString(1, inputValue("삭제할 계좌번호"));
			csmt.registerOutParameter(2, Types.NUMERIC);
			csmt.execute();
			int rs = csmt.getInt(2);
			switch(rs) {
				case 1:
					System.out.println("삭제되었습니다.");
					break;
				case 0:
					System.out.println("찾는계좌가 없습니다..");
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
