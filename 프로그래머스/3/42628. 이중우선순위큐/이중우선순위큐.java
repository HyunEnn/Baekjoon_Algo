import java.util.*;

class Solution {
    static PriorityQueue<Integer> Q = new PriorityQueue<>();
    static PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        for(int i=0;i<operations.length;i++) {
            String[] s = operations[i].split(" ");
            if(s[0].equals("I")) { // 삽입
                Q.offer(Integer.parseInt(s[1])); // 최소 힙
                maxQ.offer(Integer.parseInt(s[1])); // 최대 힙
            } else { // 삭제
                if(s[1].equals("-1")) { // 최소값 삭제
                    // 여기서, Q가 비었다면 continue
                    if(Q.isEmpty()) continue;
                    int r = Q.poll(); // 삭제되는 Q의 최소 값
                    maxQ.remove(r); // 최대 힙에서 같은 값은 동기화해서 삭제
                } else if(s[1].equals("1")) { // 최대힙 삭제
                    // 여기서도, maxQ가 비었다면 continue
                    if(maxQ.isEmpty()) continue;
                    int r = maxQ.poll(); 
                    Q.remove(r);
                }
            }
        }
        int max = 0;
        int min = 0;
        if(maxQ.size() == 0) 
            max = 0;
        else 
            max = maxQ.peek();
        if(Q.size() == 0) 
            min = 0;
        else
            min = Q.peek();
        answer[0] = max; answer[1] = min;
        return answer;
    }
}