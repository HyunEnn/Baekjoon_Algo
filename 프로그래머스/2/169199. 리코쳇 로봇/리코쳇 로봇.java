import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static char[][] map;
    static int startR, startC, endR, endC;
    static boolean[][] v;
    public int solution(String[] board) {
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
        int answer = -1;
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 나온 자리가 G 라면, 종료
            if(p.r == endR && p.c == endC) {
                return p.cnt;
            }
            
            // 방향을 정해두고, 갈수있는 곳까지 진행
            for(int k=0;k<4;k++) {
                int t = 1;
                while(true) {
                    int nr = p.r + dr[k] * t;
                    int nc = p.c + dc[k] * t;
                    // 범위를 벗어나거나, 벽을 만났으면 스톱
                    if(!inRange(nr, nc) || map[nr][nc] == 'D') {
                        int stopR = p.r + dr[k] * (t-1);
                        int stopC = p.c + dc[k] * (t-1);
                        // 멈춘 자리가 방문한 곳이 아닌 경우, 체크 후 Q에 삽입
                        if(!v[stopR][stopC]) {
                            v[stopR][stopC] = true;
                            Q.offer(new Point(stopR, stopC, p.cnt + 1));
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