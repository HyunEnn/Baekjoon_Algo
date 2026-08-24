import java.util.*;
class Solution {
    
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i<=order.length; i++){
            // 만약 현재 값이 기존 값과 같다면 +1
            if(order[answer] == i) {
              answer++;
              continue;
            }
            // 스택이 비었다면, 값을 넣고
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            // 스택이 비어있지 않다면, peek 의 값을 확인한다.
            while(!stack.isEmpty() && stack.peek() == order[answer]){
                stack.pop();
                answer++;
            }
            stack.push(i);
        }

        while(!stack.isEmpty() && stack.peek() == order[answer]){
          stack.pop();
          answer++;
        }
        return answer;
    }
}