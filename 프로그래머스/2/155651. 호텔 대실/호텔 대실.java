import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public int solution(String[][] book_time) {
        List<Point> list = new ArrayList<>();
        for(int i=0;i<book_time.length;i++) {
            String[] s = book_time[i][0].split(":");
            String[] e = book_time[i][1].split(":");
            
            int st = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            int end = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
            
            // System.out.println(st + " " + end);
            list.add(new Point(st, end));
        }
        
        list.sort((a, b) -> {
            if(a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
            
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        for(Point p : list) {
            int start = p.r; int end = p.c + 10;
            // 비었으면, 최초 삽입
            if(PQ.isEmpty()) PQ.offer(end);
            else {
                int first = PQ.peek();
                if(first <= start) {
                    PQ.poll(); 
                }
                PQ.offer(end);
            }
        }
        int answer = PQ.size();
        return answer;
    }
}