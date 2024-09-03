import java.util.*;
import java.io.*;

class Solution {
    static PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
    public long solution(int n, int[] works) {
        long answer = 0;
        long sum = 0;
        for(int i=0;i<works.length;i++) {
            PQ.offer(works[i]);
            sum += works[i]; // 클 경우는 논외 처리
        }
        int idx = 0;
        if(n > sum) 
            answer = 0;
        else {
            while(n > 0) {
                int curr = PQ.poll();
                curr--;
                n--;
                PQ.offer(curr);
            }
            
            for(int i : PQ) {
                answer += (int) Math.pow(i, 2);
            }
        }
        
        return answer;
    }
}