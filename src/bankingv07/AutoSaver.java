package bankingv07;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/*
6단계 : 쓰레드를 통해 계좌정보 자동저장하기

	쓰레드를 이용해서 계좌정보가 텍스트파일로 자동저장되도록 구현하자.
	
1. 메인메뉴에 ‘저장옵션’ 을 추가한다. 
2. ‘저장옵션’의 서브메뉴로 1.자동저장On, 2.자동저장Off 를 추가한다. 
3. 1번을 선택시 쓰레드가 시작된다. ⇒ start() 메서드로 시작할 수 있다.
4. 2번을 선택시 쓰레드가 중지된다. ⇒ interrupt() 메서드로 종료할 수 있다. 이때 예외가 
	발생하게되므로 예외처리를 해야된다. 즉, 예외가 발생하면서 
	쓰레드가 Dead상태로 들어간다.
5. 만약 이미 자동저장이 실행중인데 다시 1번을 선택하면 ‘이미 
	자동저장이 실행중입니다’ 라고 경고메세지를 띄워준다. ⇒ isAlive() 
	메서드로 확인할 수 있다.
6. 자동저장은 5초에 한번씩 이루어진다.
7. 해당 쓰레드는 프로그램 종료시 같이 종료되는 데몬쓰레드로 생성한다. 

	저장될파일명 : AutoSaveAccount.txt
	쓰레드로 정의할 클래스명 : AutoSaver.java

 */
public class AutoSaver extends Thread {
	
	@Override
	public void run() {
		/*
		무한 루프로 구성되어있고 별도의 탈출조건을 만들지 않는다.
		3초에 한번씩 block상태로 돌아간다. */
		while(true) {
			try {
				/*
				문자열을 저장하기 위한 출력스트림 생성. 문자열의 입력은
				버퍼에 따라 성능의 차이가 크기때문에 버퍼 필터 스트림을
				추가로 연결해서 인스턴스를 생성한다. */
				BufferedWriter out = new BufferedWriter(
						new FileWriter("src/bankingv07/AutoSaveAccount.txt"));
				/*
				write를 통해 문자열을 저장하고, 줄바꿈은 newLine을
				통해 처리한다. OS별로 개행문자가 다르기 때문에 이 메서드를
				통해 줄바꿈 처리를 해야한다. */
				
				for(Account acc : AccountManager.Accounts) {
					out.write(acc.toString());
				}
				
				
//				Iterator<Account> itr = AccountManager.Accounts.iterator();
//				while(itr.hasNext()) {
//					Account account = itr.next();
//					if(account instanceof NormalAccount) {
//						out.write(((NormalAccount)account).toString());
//						out.newLine();
//					}
//					else if(account instanceof HighCreditAccount) {
//						out.write(((HighCreditAccount)account).toString());
//						out.newLine();
//					}
//				}
				out.close();
				sleep(5000);
				System.out.println("(자동저장) 계좌정보를 텍스트로 저장되었습니다.");
			}
			catch (FileNotFoundException e) {
				System.out.println("파일없음");
			}
			catch (IOException e) {
				System.out.println("IO오류");
			}
			catch(InterruptedException e) {
				System.out.println("자동저장 종료");
				break;
			}
		}
	}
	
	
	
	
}
