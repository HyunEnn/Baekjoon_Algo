import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] ans;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(int[][] land) {
        ans = new int[land[0].length];
        int answer = 0;
        v = new boolean[land.length][land[0].length];
        for(int i=0;i<land.length;i++) {
            for(int j=0;j<land[0].length;j++) {
                if(land[i][j] == 1 && !v[i][j]) {
                    BFS(i, j, land);
                }
            }
        }
        
        for(int i=0;i<ans.length;i++) {
            answer = Math.max(answer, ans[i]);
        }
        return answer;
    }
    
    private static void BFS(int r, int c, int[][] land) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        v[r][c] = true;
        boolean[] ch = new boolean[land[0].length];
        int num = 1;
        ch[c] = true;
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc] && land[nr][nc] == 1) {
                    Q.offer(new Point(nr, nc));
                    v[nr][nc] = true;
                    ch[nc] = true;
                    num++;
                }
            }
        }
        
        for(int i=0;i<ch.length;i++) {
            if(ch[i]) ans[i] += num;
        }
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < v.length && c >= 0 && c < v[0].length;
    }
}