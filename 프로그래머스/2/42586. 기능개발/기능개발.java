import java.util.*;
import java.io.*;

class Solution {
    static class Pair {
        int val, inc;
        Pair(int val, int inc) {
            this.val = val;
            this.inc = inc;
        }
    }
    static LinkedList<Pair> list = new LinkedList<>();
    static List<Integer> answer = new ArrayList<>();
    public List<Integer> solution(int[] progresses, int[] speeds) {
        
        for(int i=0;i<progresses.length;i++) {
            list.add(new Pair(progresses[i], speeds[i]));
        }
        
        while(!list.isEmpty()) {
            for(int i=0;i<list.size();i++) {
                Pair p = list.get(i);
                list.set(i, new Pair(p.val + p.inc, p.inc));
            }
            
            int cnt = 0;
            while(!list.isEmpty() && list.get(0).val >= 100) {
                list.poll();
                cnt++;
            }
            
            if(cnt > 0)
                answer.add(cnt);
        }

        return answer;
    }
}