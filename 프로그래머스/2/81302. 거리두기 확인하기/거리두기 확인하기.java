import java.util.*;

class Solution {
    static boolean[][] v;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, -2, 0, 2, 0};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0, 2, 0, -2};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for(int i=0;i<places.length;i++) {
            char[][] map = new char[5][5];
            v = new boolean[5][5];
            // 맵 생성
            for(int j=0;j<5;j++) {
                for(int k=0;k<5;k++) {
                    map[j][k] = places[i][j].charAt(k);
                }
            }
            // 한 칸씩 탐색하면서 진행
            for(int j=0;j<5;j++) {
                boolean flag = false;
                for(int k=0;k<5;k++) {
                    // 현재 탐색안한 P 인 경우 진행
                    if(map[j][k] == 'P' && !v[j][k]) {
                        if(!solve(j, k, map)) { 
                            answer[i] = 0;
                            System.out.println("언제? " + j + " " + k);
                            flag = true; break;
                        }
                    }
                }
                if(flag) break;
            }
            
        }
        return answer;
    }
    
    public static boolean solve(int r, int c, char[][] map) {
        // 우선 현재 위치 true 진행 + 4방 탐색 진행
        v[r][c] = true;
        for(int k=0;k<8;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            // 우선 범위 안
            if(inRange(nr, nc)) {
                // 상하좌우가 P라면 false
                if(k == 0 || k == 2 || k == 4 || k == 6) {
                    if(map[nr][nc] == 'P') return false;
                }
                // 각 대각선 별 탐색조건
                if(k == 1 && map[nr][nc] == 'P') {
                    if(map[nr][nc-1] == 'O' || map[nr+1][nc] == 'O') return false;
                }
                if(k == 3 && map[nr][nc] == 'P') {
                    if(map[nr-1][nc] == 'O' || map[nr][nc-1] == 'O') return false;
                }
                if(k == 5 && map[nr][nc] == 'P') {
                    if(map[nr-1][nc] == 'O' || map[nr][nc+1] == 'O') return false;
                }
                if(k == 7 && map[nr][nc] == 'P') {
                    if(map[nr+1][nc] == 'O' || map[nr][nc+1] == 'O') return false;
                }
                
                if(map[nr][nc] == 'P') v[nr][nc] = true;
            }
        }
        // 상하좌우 2칸씩 나가는 맨해튼 2도 고려
        for(int k=8;k<12;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(inRange(nr, nc)) {
                // 우선 P면, 가운데 길이 X가 아닐때 false
                if(k==8) { 
                    if(map[nr][nc] == 'P' && map[nr+1][nc] != 'X') return false;
                } else if(k == 9) {
                    if(map[nr][nc] == 'P' && map[nr][nc-1] != 'X') return false;
                } else if(k == 10) {
                    if(map[nr][nc] == 'P' && map[nr-1][nc] != 'X') return false;
                } else {
                    if(map[nr][nc] == 'P' && map[nr][nc+1] != 'X') return false;
                }
            }
        }
        return true;
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}