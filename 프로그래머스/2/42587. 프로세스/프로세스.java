import java.io.*;
import java.util.*;

class Solution {
    static class Point {
        int priority = 0;
        int idx = 0;
        Point(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Point> Q = new ArrayDeque<>();
        for(int i=0;i<priorities.length;i++) {
            Q.offer(new Point(priorities[i], i)); // Q에 우선순위 값, 위치 삽입
        }
        
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            boolean flag = false;
            for(Point o : Q) {
                if(o.priority > p.priority) {
                    flag = true;
                } // true가 되면 큰 값이 있음
            }
            
            if(flag) {
                Q.offer(new Point(p.priority, p.idx));
            } else {
                if(p.idx == location) {
                    return answer + 1;
                } else {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}