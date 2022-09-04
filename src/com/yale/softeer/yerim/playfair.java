package com.yale.softeer.yerim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class playfair {
	// 상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	// J - 9
	static boolean[] alpha = new boolean[26];
	static char[][] map = new char[5][5];
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String key = br.readLine();
		String message = br.readLine();
		
		int num = 0;
		// 메세지 입력
		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			// 중복 제거!!
			if(!alpha[c-'A']) {
				map[num/5][num%5] = c;
				alpha[c-'A'] = true;
				num++;
			}
		}
		
		// 나머지 문자 입력
		for (int i = 0; i < alpha.length; i++) {
			// j는 걍 넘기자
			if(i == 9) {
				continue;
			}
			// 넣자
			if(!alpha[i]) {
																															map[num/5][num%5] = (char) (i + 'A');
				num++;
			}
		}
		
		
		stack = new Stack<>();
		stack.add(key.charAt(0));
		for (int i = 1; i < key.length(); i++) {
			if(stack.size()%2 == 0) {
				stack.add(key.charAt(i));
			} else {
				if(stack.get(stack.size()-1) == key.charAt(i)) {
					// XX일 경우
					if(stack.get(stack.size()-1) == 'X') {
						stack.add('Q');
						stack.add(key.charAt(i));
					}
					// 같은 글자일 경우
					else {
						stack.add('X');
						stack.add(key.charAt(i));
					}
				} else {
					stack.add(key.charAt(i));
				}
			}
		}
		if(stack.size()%2 != 0) {
			stack.add('X');
		}
		
		for (int i = 0; i < stack.size(); i=i+2) {
			int r1 = 0, r2 = 0, c1 = 0, c2 = 0;
			
			// 좌표 화긴
			for (int m = 0; m < map.length; m++) {
				for (int n = 0; n < map[m].length; n++) {
					if(map[m][n] == stack.get(i)) {
						r1 = m; c1 = n;
					}
					if(map[m][n] == stack.get(i+1)) {
						r2 = m; c2 = n;
					}
				}
			}
			
			if(r1 == r2) { // 같은 행
				sb.append(map[r1][(c1+1)%5]);
				sb.append(map[r1][(c2+1)%5]);				
			}
			else if(c1 == c2) { // 같은 열
				sb.append(map[(r1+1)%5][c1]);
				sb.append(map[(r2+1)%5][c2]);
			} else {
				sb.append(map[r1][c2]);
				sb.append(map[r2][c1]);
			}
		}
		
		System.out.println(sb);
		
//		System.out.println(stack.toString());
	}  

}
