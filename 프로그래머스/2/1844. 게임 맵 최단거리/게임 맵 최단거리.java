import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static boolean[][] v;
    public int solution(int[][] maps) {
        // System.out.println(maps.length);
        // System.out.println(maps[0].length);
        v = new boolean[maps.length][maps[0].length];
        int answer = 0;
        bfs(0, 0, maps);
        if(maps[maps.length-1][maps[0].length-1] <= 1) {
            return -1;
        } else {
            return maps[maps.length-1][maps[0].length-1];   
        }
    }
    public static void bfs(int r, int c, int[][] maps) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        v[r][c] = true;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(nr < 0 || nr > maps.length-1 || nc < 0 || nc > maps[0].length-1) 
                    continue;
                if(maps[nr][nc] == 1 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    maps[nr][nc] = maps[p.r][p.c] + 1; 
                }
            }
        }
    }
}