package bankingv07final;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class AutoSaver extends Thread {
	
	public AutoSaver() {
		setDaemon(true);
	}

	@Override
	public void run() {
		while(true) {
			try {
				BufferedWriter out = new BufferedWriter(
						new FileWriter("src/bankingv07final/AutoSaveAccount.txt"));
				
				for(Account acc : AccountManager.Accounts) {
					out.write(acc.toString());
					out.newLine();
				}
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
				System.out.println();
				System.out.println("자동저장 종료");
				return;
			}
		}
	}
	
	
	
	
}
