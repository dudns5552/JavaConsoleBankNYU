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

	static Scanner scan = new Scanner(System.in);
	
	public static void play() {
		
		int[][] map = {
				{1, 2, 3},
				{4, 0, 5},
				{7, 8, 6}
		};
		
		
		Random rd = new Random();
		
		String[] directions = {"w", "a", "s", "d"};
		
		for (int r = 0; r < 3 ; r++) {
			String rdmove = directions[rd.nextInt(4)];
			
			int x = 0;
			int y = 0;
			
			for (int i = 0; i < 3; i++) {
			    for (int j = 0; j < 3; j++) {
			        if (map[i][j] == 0) {
			            x = i;
			            y = j;
			        }
			    }
			}
			if(rdmove.equalsIgnoreCase("w")) {
				x -= 1;
				if(x < 0) {
					x += 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x+1][y] = temp;
				}
			}
			else if(rdmove.equalsIgnoreCase("a")) {
				y -= 1;
				if(y < 0) {
					y += 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x][y+1] = temp;
				}
			}
			else if(rdmove.equalsIgnoreCase("s")) {
				x += 1;
				if(2 < x) {
					x -= 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x-1][y] = temp;
				}
			}
			else if(rdmove.equalsIgnoreCase("d")) {
				y += 1;
				if(2 < y) {
					y -= 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x][y-1] = temp;
				}
			}
			
			
		}
		
		
		
		boolean end = true;
		while(end == true) {
		
			
			System.out.println("=================");
			for(int i = 0 ; i < 3 ; i++) {
				for(int j = 0; j < 3 ; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("=================");
			
			
			int x = 0;
			int y = 0;
			
			for (int i = 0; i < 3; i++) {
			    for (int j = 0; j < 3; j++) {
			        if (map[i][j] == 0) {
			            x = i;
			            y = j;
			        }
			    }
			}
			
			
			
			
			
			System.out.println("[ 이동 ] w:전  s:후  a:좌  d:우");
			String move = scan.nextLine();
			
			if(move.equalsIgnoreCase("w")) {
				x -= 1;
				if(x < 0) {
					System.out.println("이동 불가");
					x += 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x+1][y] = temp;
				}
			}
			else if(move.equalsIgnoreCase("a")) {
				y -= 1;
				if(y < 0) {
					System.out.println("이동 불가");
					y += 1;
				}
				else {
					int temp = map[x][y];
					map[x][y] = 0;
					map[x][y+1] = temp;
				}
			}
			else if(move.equalsIgnoreCase("s")) {
				x += 1;
				if(2 < x) {
					System.out.println("이동 불가");
				}
				else {
					
					int temp = map[x][y];
					map[x][y] = 0;
					map[x-1][y] = temp;
				}
			}
			else if(move.equalsIgnoreCase("d")) {
				y += 1;
				if(2 < y) {
					System.out.println("이동 불가");
				}
				else {
					
					int temp = map[x][y];
					map[x][y] = 0;
					map[x][y-1] = temp;
				}
			}
			else if(move.equalsIgnoreCase("x")) {
				System.exit(0);
			}
			
			
			
			
			int[][] ending = {
					{1, 2, 3},
					{4, 5, 6},
					{7, 8, 0}
			};
			
			boolean isend = true;
			
			for(int i = 0 ; i < 3 ; i++) {
				for(int j = 0; j < 3 ; j++) {
					if( map[i][j] != ending[i][j] ) {
						isend = false;
					}
				}
			}
			
			if (isend == true) {
				end = false;
			}
			
		}//while 끝
		
		System.out.println("정답입니다. 다시 하시겠습니까? (Y. 재시작 N. 종료)");
		
		String rt = scan.nextLine();
		if(rt.equalsIgnoreCase("y")) {
			play();
		}
		else {
			System.exit(0);
		}
		
	}//play 끝
}
