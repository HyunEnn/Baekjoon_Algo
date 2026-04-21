import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        long sum = 0;
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<works.length;i++) {
            PQ.offer(works[i]);
            sum += works[i];
        }
        
        if(n > sum) return 0;
        
        while(n > 0 && !PQ.isEmpty()) {
            int p = PQ.poll();
            p--;
            n--;
            if(p == 0) continue;
            else PQ.offer(p);
            
        }
        
        for(int i : PQ) {
            System.out.print(i + " ");
        }
        
        while(!PQ.isEmpty()) {
            answer += Math.pow(PQ.poll(), 2);
        }
        return answer;
    }
}