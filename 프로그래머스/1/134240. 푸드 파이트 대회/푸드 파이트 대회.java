import java.util.*;

class Solution {
    static Queue<Integer> QA = new ArrayDeque<>();
    static Deque<Integer> QB = new ArrayDeque<>();
    public String solution(int[] food) {
        String answer = "";
        for(int i=1;i<food.length;i++) {
            int idx = food[i] / 2;
            for(int j=0;j<idx;j++) {
                QA.offer(i); QB.offerFirst(i);
            }
        }
        
//         for(int i : QB) {
//             System.out.print(i);
//         }
        
//         System.out.println();
        
        StringBuilder sb = new StringBuilder();
        while(!QA.isEmpty()) {
            sb.append(QA.poll()+"");
        } sb.append("0");
        while(!QB.isEmpty()) {
            sb.append(QB.poll()+"");
        }
        // System.out.println(sb.toString());
        answer = sb.toString();
        return answer;
    }
}