package com.yale.swea.yerim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class swea_5656 {
 
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, W, H, result;
    static int[] sel;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Point> queue;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            sel = new int[N];
            queue = new LinkedList<>();
            result = Integer.MAX_VALUE;
             
            for (int r = 0; r < map.length; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < map[r].length; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
             
            // 0-W 까지 다 돌려봐
            perm(0);
             
            System.out.printf("#%d %d\n", t, result);
        }
    }
 
    private static void perm(int i) {
        if(i == N) {
            int[][] cpy_map = new int[H][W];
            // 맵 복사
            copy(cpy_map);
             
            // sel 순서대로 진행
            for (int s = 0; s < sel.length; s++) {
                visit = new boolean[H][W];
                // 던지기
                shot(cpy_map, sel[s]);
                // 떨어트리기
                down(cpy_map);
            }
            
            result = Math.min(result, count(cpy_map));
            return;
        }
         
        for (int j = 0; j < W; j++) {
            sel[i] = j;
            perm(i+1);
        }
    }
 
    private static int count(int[][] cpy_map) {
        int cnt = 0;
        for (int r = 0; r < cpy_map.length; r++) {
            for (int c = 0; c < cpy_map[r].length; c++) {
                if(cpy_map[r][c] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
 
       private static void down(int[][] cpy_map) {
              Stack<Integer> s = new Stack<>();
              for (int c = 0; c < W; c++) {
                 for (int r = 0; r < H; r++) {
                    if(cpy_map[r][c] != 0) {
                       s.add(cpy_map[r][c]);
                    }
                 }
                  
                 for (int r = H-1; r >= 0; r--) {
                    if(!s.isEmpty()) {
                       cpy_map[r][c] = s.pop();
                    } else {
                       cpy_map[r][c] = 0;
                    }
                 }
              }
           }
 
    private static void shot(int[][] cpy_map, int s) {
        // 부딪히는 부분
        for (int i = 0; i < H; i++) {
            if(cpy_map[i][s] != 0) {
                queue.offer(new Point(i, s, cpy_map[i][s]));
                break;
            }
        }
         
        while(!queue.isEmpty()) {
            Point p = queue.poll();
             
            for (int d = 0; d < dr.length; d++) {
                for (int k = 0; k < p.cnt; k++) {
                    int nr = p.r + dr[d] * k;
                    int nc = p.c + dc[d] * k;
                    if(nr>=0 && nr<H && nc>=0 && nc<W && !visit[nr][nc] && cpy_map[nr][nc] != 0) {
                        visit[nr][nc] = true;
                        queue.offer(new Point(nr, nc, cpy_map[nr][nc]));
                        cpy_map[nr][nc] = 0;
                    }
                }
            }
        }
    }
 
    private static void copy(int[][] cpy_map) {
        for (int r = 0; r < cpy_map.length; r++) {
            for (int c = 0; c < cpy_map[r].length; c++) {
                cpy_map[r][c] = map[r][c];
            }
        }
    }
     
    static class Point{
        int r, c, cnt;
 
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}