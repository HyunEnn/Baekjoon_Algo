import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static boolean[] v;
    static List<Point> list = new ArrayList<>();
    static int answer;
    public int solution(int n) {
        answer = 0;
        v = new boolean[n];
        dfs(0, n);
        return answer;
    }
    
    public static void dfs(int idx, int n) {
        // basis
        if(idx == n) {
            answer++;
            return;
        }
        
        // inductive
        for(int i=0;i<n;i++) {
            if(v[i]) continue;
            // 놓을수 있는지 체크
            if(possible(idx, i)) {
                v[i] = true;
                list.add(new Point(idx, i));
                dfs(idx + 1, n);
                v[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static boolean possible(int r, int c) {
        for(Point p : list) {
            int diff = r - p.r;
            if(p.c - diff == c || p.c + diff == c) return false;
        }
        return true;
    }
}