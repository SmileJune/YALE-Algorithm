package com.yale.swea.yerim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_1953 {
	   static int[][] dr = {{}, {-1, 1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
	   static int[][] dc = {{}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
	   static int N, M, R, C, L, result;
	   static int[][] map;
	   static boolean[][] visit;

	   public static void main(String[] args) throws NumberFormatException, IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st;
	      int T = Integer.parseInt(br.readLine());
	      for (int t = 1; t <= T; t++) {
	         st = new StringTokenizer(br.readLine());
	         N = Integer.parseInt(st.nextToken());
	         M = Integer.parseInt(st.nextToken());
	         R = Integer.parseInt(st.nextToken());
	         C = Integer.parseInt(st.nextToken());
	         L = Integer.parseInt(st.nextToken());
	         map = new int[N][M];
	         visit = new boolean[N][M];
	         result = 0;
	         
	         for (int r = 0; r < map.length; r++) {
	            st = new StringTokenizer(br.readLine());
	            for (int c = 0; c < map[r].length; c++) {
	               map[r][c] = Integer.parseInt(st.nextToken());
	            }
	         }
	         
	         bfs(new Point(R, C, 1));
	         
	         System.out.printf("#%d %d\n", t, result);
	      }
	   }
	   private static void bfs(Point point) {
	      Queue<Point> queue = new LinkedList<>();
	      queue.offer(point);
	      visit[point.row][point.col] = true;
	      
	      while(!queue.isEmpty()) {
	         Point p = queue.poll();
	         result++;
	         
	         if(p.cnt == L) {
	            continue;
	         }
	         
	         int dir = map[p.row][p.col];
	         for (int d = 0; d < dr[dir].length; d++) {
	            int nr = p.row + dr[dir][d];
	            int nc = p.col + dc[dir][d];
	            // 범위 벗어나는지, 방문했던건지, 0 아닌지
	            if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc] && map[nr][nc] != 0) {
	               //옆에 칸과 터널 방향이 연결되어있는지 체크
	               if(check(dr[dir][d], dc[dir][d], map[nr][nc])) {
	                  visit[nr][nc] = true;
	                  queue.offer(new Point(nr, nc, p.cnt+1));
	               }
	            }
	         }
	      }      
	   }

	   private static boolean check(int r, int c, int s) {
	      for (int i = 0; i < dr[s].length; i++) {
	         if(dr[s][i] + r == 0 && dc[s][i] + c == 0)
	            return true;
	      }
	      return false;
	   }

	   static class Point{
	      int row, col, cnt;

	      public Point(int row, int col, int cnt) {
	         this.row = row;
	         this.col = col;
	         this.cnt = cnt;
	      }
	   }

}
