import java.util.*;

class Solution {
    static class Point {
        int r, c, move;
        Point(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] v;
    static int startR, startC, endR, endC;
    public int solution(String[] board) {
        int answer = -1;
        map = new char[board.length][board[0].length()];
        v = new boolean[board.length][board[0].length()];
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length();j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    startR = i; startC = j;
                } else if(map[i][j] == 'G') {
                    endR = i; endC = j;
                }
            }
        }
        
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(startR, startC, 0));
        v[startR][startC] = true;
        
        int check = 0;
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            
            if(p.r == endR && p.c == endC) {
                answer = p.move;
                break;
            }
            
            for(int k=0;k<4;k++) {
                int t = 1;
                while(true) {
                    int nr = p.r + (dr[k] * t);
                    int nc = p.c + (dc[k] * t);
                    if(!inRange(nr, nc) || map[nr][nc] == 'D') {
                        int stopR = p.r + (dr[k] * (t-1));
                        int stopC = p.c + (dc[k] * (t-1));
                        
                        if(!v[stopR][stopC]) {
                            v[stopR][stopC] = true;
                            Q.offer(new Point(stopR, stopC, p.move + 1));
                        }
                        
                        break;
                    }
                    t++;
                }
            }
        }
        
        
        return answer;
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
}