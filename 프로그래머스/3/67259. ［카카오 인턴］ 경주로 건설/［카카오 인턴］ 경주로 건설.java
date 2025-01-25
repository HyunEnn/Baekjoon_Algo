import java.util.*;

class Solution {
    static class Point {
        int r, c, dir, val;
        Point(int r, int c, int dir, int val) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.val = val;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int[][][] map;
    public int solution(int[][] board) {
        map = new int[board.length][board[0].length][4];
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                for(int k=0;k<4;k++) {
                    map[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        bfs(board);
       
        int answer = Integer.MAX_VALUE;
        for(int k=0;k<4;k++) {
            // System.out.println(map[board.length-1][board[0].length-1][k]);   
            answer = Math.min(answer, map[board.length-1][board[0].length-1][k]);
        }
        
        return answer;
    }
    
    public static void bfs(int[][] board) {
        Queue<Point> Q = new ArrayDeque<>();
        for(int i=0;i<4;i++) {
            map[0][0][i] = 0;
        }
        Q.offer(new Point(0,0,-1,0));
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위를 벗어나면 무시
                if(nr < 0 || nr > board.length-1 || nc < 0 || nc > board[0].length-1) continue;
                // 벽에 부딪히면 무시
                if(board[nr][nc] == 1) continue;
                // 기존 값보다 작으면 +100, +600을 할지 결정해야하는데 기존 p의 dir과 다르면?
                if(p.dir == -1) {
                    map[nr][nc][k] = 100;
                    Q.offer(new Point(nr, nc, k, 100));
                }
                else if(p.dir == k) {
                    if(map[nr][nc][k] > map[p.r][p.c][p.dir] + 100) {
                        map[nr][nc][k] = map[p.r][p.c][p.dir] + 100;
                        Q.offer(new Point(nr, nc, k, 100));
                    }
                }
                else {
                    if(map[nr][nc][k] > map[p.r][p.c][p.dir] + 600) {
                        map[nr][nc][k] = map[p.r][p.c][p.dir] + 600;
                        Q.offer(new Point(nr, nc, k, 600));
                    }
                }     
            }
        }
    }
}

// 직선도로는 이동할때마다 +100, 코너 구간이면 +500을 추가
// 현재 자리를 다시 돌아올 수도 있기에 3차원 배열로, 가능한 방향에 대한 3차원을 추가함
