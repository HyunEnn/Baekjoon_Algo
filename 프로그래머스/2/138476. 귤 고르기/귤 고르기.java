import java.util.*;

class Solution {
    static class Point {
        int st, cnt;
        Point(int st, int cnt) {
            this.st = st;
            this.cnt = cnt;
        }
    }
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<tangerine.length;i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        
        List<Point> list = new ArrayList<>();
        
        for(int i : map.keySet()) {
            list.add(new Point(i, map.get(i)));
        }
        
        list.sort((a, b) -> b.cnt - a.cnt);
        
        int ch = 0;
        for(Point p : list) {
            ch += p.cnt; answer++;
            if(ch >= k) break;
        }
        return answer;
    }
}