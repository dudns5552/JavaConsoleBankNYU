package bankingv07final;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ObjectInputStream {

	public void readAcc() {

		System.out.println("데이터를 읽는 중 입니다.");
		
		try {
			/*
			인스턴스의 복원(역직렬화)를 위한 스트림을 생성하고
			readObject를 통해 복원한다. */
			java.io.ObjectInputStream in =
				new java.io.ObjectInputStream(
						new FileInputStream(
								"src/bankingv07/AccountInfo.obj"));
			/*
			저장시 Object기반으로 저장되므로 복원시에는 원래의
			자료형으로 다운캐스팅(강제형변환) 해야한다. */
			while(true) {
				try {
					Account	account = (Account)in.readObject();
					AccountManager.Accounts.add(account);
					
				} 
				catch (EOFException e) {
					break; // 파일 끝에 도달하면 루프 종료
				}
				catch (ClassCastException e) {
					System.out.println("클래스 캐스트 오류 뱅킹폴더 확인해보셈");
					e.printStackTrace();
				}
			}//와일 끝
			
			//스트림 소멸
			in.close();
			
			if(AccountManager.Accounts.isEmpty()) {
				System.out.println("읽어올파일이 없습니다.");
			}
			else {
				System.out.println("저장된 파일을 불러옵니다.");
			}
		}//트라이 끝
		catch(ClassNotFoundException e) {
			System.out.println("[예외]클래스 없음");
		}
		catch(FileNotFoundException e) {
			System.out.println("[예외]파일 없음");
		}
		catch (IOException e) {
			System.out.println("[Exception]뭔가 없음");
		}
	}//메인끝
}
