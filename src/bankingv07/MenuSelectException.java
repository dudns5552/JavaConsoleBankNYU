package bankingv07;

/*
메뉴선택 : 
메뉴선택할때 문자를 입력할 수 없다.
지정된 정수 이외의 숫자를 입력한 경우(개발자정의 예외처리)
 */
public class MenuSelectException extends Exception {

	public MenuSelectException() {
		super("메뉴 입력 예외발생됨");
		System.out.println("메뉴는 1~5사이 정수를 입력하세요");
	}
	
	
}
