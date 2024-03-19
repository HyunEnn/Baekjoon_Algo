import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i=0;i<prices.length;i++) {
            while(true) {
                if(stack.isEmpty()){
                    stack.push(i);
                    break;
                }
                else if(prices[stack.peek()] > prices[i]) {
                    answer[stack.peek()] = i - stack.peek();
                    stack.pop();
                } else {
                    stack.push(i);
                    // res = 0;
                    break;
                }
            }
        }
        while(!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1; // 5 - 4 - 1
            stack.pop();
        }
        return answer;
    }
}