import java.util.*;

class Solution {
    static boolean[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static HashSet<String> set = new HashSet<>();
    public int solution(String dirs) {
        map = new boolean[11][11];
        // 시작은 6,6 에서 (0,0)이므로
        int startR = 5, startC = 5;
        map[startR][startC] = true;
        int answer = 0;
        for(int i=0;i<dirs.length();i++) {
            char c = dirs.charAt(i);
            int nr = 0, nc = 0;
            if(c == 'U') {
                nr = startR + dr[0];
                nc = startC + dc[0];
            } else if(c == 'R') {
                nr = startR + dr[1];
                nc = startC + dc[1];
            } else if(c == 'D') {
                nr = startR + dr[2];
                nc = startC + dc[2];
            } else {
                nr = startR + dr[3];
                nc = startC + dc[3];
            }
            
            if(!inRange(nr, nc)) continue;
            
            String path1 = startR + "," + startC + "->" + nr + "," + nc;
            String path2 = nr + "," + nc + "->" + startR + "," + startC;
            set.add(path1);
            set.add(path2);
            startR = nr;
            startC = nc;
        }
        
        return set.size() / 2;
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
}