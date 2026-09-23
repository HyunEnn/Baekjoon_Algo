import java.util.*;

class Solution {
    static char[][] map;
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC, endR, endC;
    static boolean[][] v;
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
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            if(p.r == endR && p.c == endC) {
                answer = p.cnt;
                break;
            }
            
            for(int k=0;k<4;k++) {
                int t = 1;
                while(true) {
                    int nr = p.r + dr[k] * t;
                    int nc = p.c + dc[k] * t;
                    // 범위를 벗어나거나, 벽에 부딪힌경우 종료
                    if(!inRange(nr, nc) || map[nr][nc] == 'D') {
                        int stopR = p.r + dr[k] * (t-1);
                        int stopC = p.c + dc[k] * (t-1);

                        // 현재 멈춘 자리가, 방문했는지?
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
    
    private static boolean inRange(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
}

/*
    R, G의 위치 저장
    벽에 부딪히거나 범위를 벗어나게 되면 그 전 자리에 멈추고 방문 처리
    멈춘 자리가 G 인 경우에 움직인 횟수 리턴
    G에 도착하지 못한 경우 -1
*/