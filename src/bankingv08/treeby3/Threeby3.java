package bankingv08.treeby3;

import java.util.Random;
import java.util.Scanner;

/*
게임의 제약
1. 벽을 넘어가면 안된다.
2. X가 이동시 해당 방향의 값이 X좌표의 값으로 이동한다. 
3. 이동키 wasd
4. X는 기본좌표값이 있다.
*/
public class Threeby3 {

	Scanner scan = new Scanner(System.in);
	
	public void play() {
		
		int[][] map = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};
		
		
		int i = 3;
		int j = 3;
		
		
		
		
		
		boolean end = true;
		while(end == true) {
		
			
			System.out.println("=================");
			for()
			System.out.print();
			System.out.println("=================");
			
			
			
			System.out.println("[ 이동 ] w:전  s:후  a:좌  d:우");
			String move = scan.nextLine();
			
			if(move.equalsIgnoreCase("w")) {
				i -= 1;
				if(i < 0) {
					System.out.println("이동 불가");
					return;
				}
				int temp = map[i][j];
				map[i][j] = 0;
				map[i+1][j] = temp;
			}
			else if(move.equalsIgnoreCase("a")) {
				j -= 1;
				if(j < 0) {
					System.out.println("이동 불가");
					return;
				}
				int temp = map[i][j];
				map[i][j] = 0;
				map[i][j+1] = temp;
			}
			else if(move.equalsIgnoreCase("s")) {
				i += 1;
				if(3 < i) {
					System.out.println("이동 불가");
					return;
				}
				int temp = map[i][j];
				map[i][j] = 0;
				map[i-1][j] = temp;
			}
			else if(move.equalsIgnoreCase("d")) {
				j += 1;
				if(3 < i) {
					System.out.println("이동 불가");
					return;
				}
				int temp = map[i][j];
				map[i][j] = 0;
				map[i][j-1] = temp;
			}
			
			
			
			
			int[][] ending = {
					{1, 2, 3},
					{4, 5, 6},
					{7, 8, 0}
			};
			if(map == ending) {
				end = false;
			}
			
		}//while 끝
		
		System.out.println("정답입니다. 다시 하시겠습니까? (Y. 재시작 N. 종료)");
		
	}//play 끝

	
	
	
	
	
	
	
	
	
}
