package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 모의SW역량테스트_5658_보물상자비밀번호 {

	static ArrayList<Character> list;
	static ArrayList<String> res;
	static HashSet<String> ss;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			res = new ArrayList<>();
			ss = new HashSet<>();
			
			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				list.add(str.charAt(i));
			}
			
			save(list);
			o
			for (int i = 0; i < N/4; i++) {
				turn(list);
				save(list);
			}
			
			res.addAll(ss);
			
			Collections.sort(res, Collections.reverseOrder());
			
			System.out.printf("#"+t+" "+Long.parseLong(res.get(K-1), 16)+"\n");
		}
	}

	private static void turn(ArrayList<Character> list) {
		list.add(list.remove(0));
	}

	private static void save(ArrayList<Character> list) {
		int line = list.size() / 4;
		for (int i = 0; i < 4; i++) {
			String s = "";
			for (int j = 0; j < line; j++) {
				s += list.get(line*i + j);
			}
			ss.add(s);
		}
	}

}
