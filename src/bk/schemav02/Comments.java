package bk.schemav02;

public class Comments {

	
	/*
	프로젝트 설명서
	
	
	
	
	
 	1단계	프로그램의 골격 만들기
		A. show menu 정의
		B. 각 클래스 생성 및 설명 작성
		C. 계좌 개설 구현
			1. 계좌의 멤버 변수 정의
			2. 계좌 저장할 배열 정의
			3. 계좌의 변수를 입력하는 로직 작성 
			4. 계좌 개설(인스턴스 생성) 및 배열에 저장하는 로직 작성
		D. 전체정보출력 구현
			1. 계좌 정보를 출력하는 메서드 작성
			2. 배열의 길이 만큼 반복하는 for문 작성
			3. 배열에 저장된 인스턴스를 인출하고 해당 인스턴스의 
				정보출력 메서드를 호출하는 로직 작성 
		E. 입금
			1. 입금메소드를 작성
			2. 계좌번호와 입금액을 입력하는 로직 작성
			3. 배열에 입력값과 같은 계좌번호가 저장되어 있는지 
				비교하는 로직 작성
			4. 있다면 해당 인스턴스를 불러와 인스턴스의 입금메소드를
				호출하는 로직 작성
		F. 출금
			1. 입금메소드 복붙
			2. +를 -로 교체
		G. 종료
	*/
	
	
	
	
	
	
	
	/*
	2단계	컨트롤클래스 생성과 상속
		A. 매니저 클래스로 로직 옮기기
		B. 일반계좌클래스 생성
			1. 멤버변수 이자를 추가
			2. 이자를 적용하여 입금 메서드 오버라이딩
			3. 정보출력 메서드 오버라이딩
		C. 고신용계좌클래스 생성
			1. 멤버변수 이자, 신용등급, 신용이자를 추가
			2. 이자를 적용하여 입금 메서드 오버라이딩
			3. 정보출력 메서드 오버라이딩
		D. 메니저클래스의 계좌개설 재정의
			1. 계좌 개설 호출시 계좌선택을 하도록 수정
			2. 해당 계좌에 맞는 멤버변수를 입력하도록 if문 작성
				2.1. 고신용계좌 개설시 신용등급을 입력받고 그 값에 따른
					신용이자가 설정되도록 if문 작성
	 */
	
	
	
	
	
	
	
	/*
	3단계	추상클래스와 예외처리
		A. account클래스를 추상클래스로 변환
		B. 예외처리
			1. 메뉴선택
				1.1. 문자입력 예외처리
				1.2. 지정된숫자 이외 숫자를 입력한경우 예외처리
					b-1.2.1 menuSelectExeption 클래스 정의
			2. 입금
				2.1. 음수입력 예외처리(if문)
				2.2. 문자입력 예외처리
				2.3. 입금단위(500원) 예외처리
			3. 출금
				3.1. 음수입력 예외처리(if문)
				3.2. 잔고보다 많은 금액을 인출할경우
					b-3.2.1. 전액출금처리
					b-3.2.2. 출금요청취소
				3.3. 출금단위(1000원) 예외처리
			4. 계좌개설
				4.1. 잔고
					4.1.1. 음수입력 예외처리(if문)
					4.1.2. 문자입력 예외처리
				4.2. 일반이자 및 고신용이자
					4.2.1. 지정된숫자(0~10)를 벗어나는 입력 예외처리(if문)
					4.2.2. 문자입력 예외처리
				4.3. 신용등급
					4.3.1. 지정된문자(A~C)를 벗어나는 입력 예외처리(if문)
					4.3.2. 숫자 입력 예외처리(if문)
				4.4. 이름
					4.4.1. 문자외 입력 예외처리
				4.5. 계좌번호
					4.5.1. 숫자외 입력 예외처리
	 */
	
	
	
	
	
	/*
	4단계	컬렉션으로 변경하기
		A. 정보의 저장 방식 교체
			1. 배열을 HashSet<E>컬렉션으로 교체
		B. 계좌개설
			1. 계좌 개설시 중복계좌 처리(for문 사용X)
				1.1. 계좌 중복을 확인하기
					1.1.1. equals() 메서드 오버라이딩
					1.1.2. hashCode() 메서드 오버라이딩
				1.2. 덮어쓰기
					1.2.1. 입력된 계좌에 해당하는 인스턴스 삭제
					1.2.2. 입력된 값의 인스턴스 생성 및 저장
				1.3. 계좌개설 취소
		C. 계좌정보삭제
			1. 계좌번호 입력
			2. 컬렉션에 계좌번호를 넣고 조회
				일치하는 정보를 가진 계좌가 있다면 삭제
			3. 
			4. 없으면 없다고 출력
	*/
	
	
	
	
	
	/*
	5단계	IO를 통해 직렬화 하기
		A. Output
			1. OutPut 인스턴스 생성
				1.1. for-each문 작성
					1.1.1. 계좌인스턴스 생성 : 컬렉션
				1.2. writeObject 메소드를 인스턴스 값으로 호출
			2. 종료키 입력시 호출되도록 case문 수정
		B. Input
			1. Input 인스턴스 생성
				1.1. Object형으로 저장된 데이터를 강제형변환
				1.1.1. 형변환된 데이터를 컬렉션에 저장
				1.1.2. 더이상 읽을 데이터가 없으면 예외처리로
					빠져나오도록 루프문 작성
			2. 시작시 자동으로 불러오도록 main클래스 수정
	 */
	
	
	
	/*
	6단계	쓰레드를 통해 계좌정보 자동저장하기
		A. 메인메뉴
			1. 저장옵션 추가
			2. 저장옵션 메서드 호출
		B. 저장옵션 메서드 (메니저 클래스)
			1. 저장옵션 1과 2를 구현
				1.1. 1입력시
					1.1.1. 인스턴스.isAlive()로 확인
						1.1.1.1. 최초실행시
							1.1.1.1.1. 인스턴스 생성
							1.1.1.1.2. 데몬스레드 선언
							1.1.1.1.3. start메서드 호출 
						1.1.1.2. 자동저장이 실행중일 경우
							1.1.1.2.1. 경고 문구 출력문 작성
				1.2. 2입력시
					1.2.1. interrupt메서드 호출
					1.2.2. interrupt예외처리 및
						break 처리
		C. run 메서드 (AutoScaver 클래스)
			1. Output인스턴스 생성
				1.1. for-each문 작성
					1.1.1. 계좌인스턴스 생성 : 컬렉션
					1.1.2. write 메서드를 계좌의 toString 값으로 호출
					1.1.3. 5초의 간격 설정
	 */
	
	
	
	
	
	
	
	/*
	7단계 특판계좌 (Special Account)
		A. 특판계좌 클래스 생성
			1. 일반계좌 클래스 상속
			2. 입금회차 카운트하는 멤버변수 추가
			3. 입금
				3.1.입금시 입금회차가 선위증가 하도록 설정
				3.2. 2회차 입금시 +500원 되도록 if문 작성
			4. 계좌정보출력
				4.1. 계좌 정보 출력시 입금회차 변수가 출력되도록 오버라이딩
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
