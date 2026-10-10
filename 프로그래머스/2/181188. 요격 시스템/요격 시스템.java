import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static List<Point> list = new ArrayList<>();
    public int solution(int[][] targets) {
        int answer = 1;
        for(int i=0;i<targets.length;i++) {
            list.add(new Point(targets[i][0], targets[i][1]));
        }
        list.sort((a, b) -> a.c - b.c); // 오름차순 정렬
        int end = list.get(0).c;
        
        for(int i=0;i<list.size();i++) {
            Point p = list.get(i);
            
            if(p.r >= end) {
                answer++;
                end = p.c;
            }
        }
        return answer;
    }
}
/* 그리디하게, 접근하는 법?
    s 기준 : [1,4] [3,7] [4,5] [4,8] [5,12] [10,14] [11,13]
    e 기준 : [1,4] [4,5] [3,7] [4,8] [5,12] [11,13] [10,14]
    // 그냥 if(p.r >= end) answer++; end = p.c;
    
    후보, {1, 3, 4, 5, 10, 11}
    그러면, 순서대로 가능한 선을 그어놓고 그게 안된다면 거기서 자르고 다음걸로 패스하면 된다?
    어차피, 짜를거기 때문에 1에서 짜르던 3.5에서 짜르던 무방하다?
*/
