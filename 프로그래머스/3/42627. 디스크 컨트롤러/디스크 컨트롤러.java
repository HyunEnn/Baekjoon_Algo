import java.util.*;
import java.io.*;

class Solution {
    static class Point implements Comparable<Point>{
        int st, time;
        Point(int st, int time) {
            this.st = st;
            this.time = time;
        }
        
        @Override
        public int compareTo(Point p) {
            return this.time - p.time;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0;
        int time = 0;
        int idx = 0;
        // 작업들을 요청 시간에 따라 정렬함
        Arrays.sort(jobs, Comparator.comparingInt(a->a[0]));
        
        // 우선순위 큐에서는 걸리는 시간 만큼 정렬
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        
        // 우선순위에 큐를 넣는 순서를 고려하여서 진행
        while(count < jobs.length) {
            // 현재 시간까지 가능한 작업을 우선순위 큐에 넣음
            while(idx < jobs.length && jobs[idx][0] <= time) {
                PQ.offer(new Point(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            // 큐에서 시간 처리를 통해 진행
            if(!PQ.isEmpty()) {
                Point p = PQ.poll();
                int wait = time - p.st;
                answer += wait + p.time;
                time += p.time;
                count++;
            } else {
                time = jobs[idx][0];
            }
        }
        
        return answer / jobs.length;
    }
    // 0->3 까지 진행하고, 
}