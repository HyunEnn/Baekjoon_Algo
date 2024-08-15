import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] c = s.toCharArray();
        int cnt = 0;
        while(cnt < c.length) {
            char tmp = c[0];
            for(int i=0;i<c.length-1;i++) {
                c[i] = c[i+1];
            } // 왼쪽으로 한칸 씩 이동
            c[c.length-1] = tmp; // 마지막 값 처리
            if(checkTrue(c))
                answer++;
            cnt++;
        }
        return answer;
    }
    
    public static boolean checkTrue(char[] c) {
        Stack<Character> stack = new Stack<>();
        stack.push(c[0]);
        for(int i=1;i<c.length;i++) {
            if(c[i] == '(' || c[i] == '{' || c[i] == '[')
                stack.push(c[i]);
            else {
                if(stack.isEmpty())
                    return false;
                if(c[i] == ')') {
                    if(stack.peek() == '(')
                        stack.pop();
                    else
                        return false;
                } else if(c[i] == '}') {
                    if(stack.peek() == '{')
                        stack.pop();
                    else
                        return false;
                } else if(c[i] == ']') {
                    if(stack.peek() == '[')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        
        if(stack.isEmpty())
            return true;
        else 
            return false;
    }
}