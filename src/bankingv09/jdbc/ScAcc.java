package bankingv09.jdbc;

import java.sql.SQLException;

public class ScAcc extends MyConnection {

	public ScAcc() {
		super("education", "1234");
	}
	
	@Override
	public void dbExecute() {
		try {
			/*
			Prepared 인터페이스를 통해 인파라미터를 설정하면
			문자인 경우 자동으로 싱글쿼테이션을 추가하게 되므로
			|| 연산자를 추가해서 쿼리문을 작성해야한다. */
			String sql = "SELECT idx, accnum, name, balance, inter "
					+ "FROM banking "
					+ "where accnum = ? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, inputValue("검색할 계좌번호"));
			rs = psmt.executeQuery();
			while(rs.next()) {
				String idx = rs.getString(1);
				String accnum = rs.getString(2);
				String name = rs.getString(3);
				String balance = rs.getString(4);
				String inter = rs.getString(5);
				
				System.out.printf("%s %s %s %s %s\n",
						idx, accnum, name, balance, inter);
			}
			
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
	}	
}
