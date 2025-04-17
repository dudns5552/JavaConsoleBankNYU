package bankingv07;

import java.io.FileOutputStream;
import java.io.IOException;


public class ObjectOutputStream {
	
	public void saveAcc() {
		
		System.out.println("정보저장을 시도합니다.");
		
		try {
			//인스턴스를 파일로 저장하기 위해 출력스트림 생성
			java.io.ObjectOutputStream out =
					new java.io.ObjectOutputStream(
							new FileOutputStream(
									"src/bankingv07/AccountInfo.obj"));
			
			for (Account acc : AccountManager.Accounts) {
				out.writeObject(acc);
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("[Exception]뭔가 없음");
		}
		catch (Exception e) {
			System.out.println("[예외] 뭔가 오류");
		}
	}
}
