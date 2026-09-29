import java.util.*;

class Solution {
    static class Point {
        int r, c, time;
        Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for(int i=0;i<places.length;i++) {
            map = new char[5][5];
            for(int j=0;j<5;j++) {
                for(int k=0;k<places[i][j].length();k++) {
                    map[j][k] = places[i][j].charAt(k);
                }
            }
            // 맵 생성 후에, P를 기준으로 time은 2로 BFS 진행
            for(int j=0;j<5;j++) {
                boolean flag = false;
                for(int k=0;k<5;k++) {
                    if(map[j][k] == 'P') {
                        int res = BFS(j, k);
                        if(res == 0) {
                            System.out.println("막히는 곳: " + j + " " + k);
                            answer[i] = res;
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag) break;
            }
        }
        return answer;
    }
    
    public static int BFS(int r, int c) {
        boolean[][] v = new boolean[5][5];
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c, 0));
        v[r][c] = true;
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            
            if(p.time == 3) continue;
            
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위 안에 있고, 
                if(inRange(nr, nc)) {
                    // 'O' 이면서, 방문하지 않은 자리는 Q에 추가한다.
                    if(map[nr][nc] == 'O' && !v[nr][nc]) {
                        v[nr][nc] = true;
                        Q.offer(new Point(nr, nc, p.time + 1));
                    }
                    // 만일, 'P' 라면 성립 X
                    else if(map[nr][nc] == 'P' && !v[nr][nc] && p.time < 2) {
                        // System.out.println(nr + " " + nc);
                        return 0;
                    }
                }
            }
        }
        
        return 1;
        
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}