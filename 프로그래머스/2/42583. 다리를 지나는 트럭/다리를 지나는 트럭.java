import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        // 7 4 5 6
        Queue<Integer> Q = new ArrayDeque<>();
        for(int truck : truck_weights) {
            while(true) {
                if(Q.isEmpty()) {
                    Q.offer(truck);
                    sum += truck;
                    answer++;
                    break;
                } else if(Q.size() == bridge_length) {
                    int removeTruck = Q.poll();
                    sum -= removeTruck;
                } else {
                    if(sum + truck <= weight) {
                        Q.offer(truck);
                        sum += truck;
                        answer++;
                        break;
                    } else {
                        Q.offer(0);
                        answer++;
                    }    
                }
                
            }
            
        }
        answer += bridge_length;
        return answer;
    }
}
// 다리를 지난 트럭, 다리를 건너는 트럭 2개의 큐를 만든다.
// 한번의 타임마다, 다리를 건너는 트럭 큐에 값을 넣고, 계속 타이머를 돌린다
// 그리고, 타이머로 값이 빠져나가면 flag = false로 값이 들어와도 된다는 것을 통해
// 값을 넣고 다시 값이 최대치에 달하면 true를 통해 값의 진입을 막는다.