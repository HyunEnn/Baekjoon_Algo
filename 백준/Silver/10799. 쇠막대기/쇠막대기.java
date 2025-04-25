
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static Stack<Character> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int answer = 0;
        for(int i=0;i<line.length();i++) {
            // '('이 왔을때, 뒤에 ')'가 있는지 확인
            if(line.charAt(i) == '(' && i<line.length()-1) {
                // ')'가 있다면 레이저
                if(line.charAt(i+1) == ')') {
                    // 레이저로 삭제했으니 다음 값을 무시하고 진행해야함
                    i++;
                    answer += stack.size();
                }
                // 없다면 상관없이 stack 에 삽입
                else stack.push(line.charAt(i));
            }
            // ')' 왔을 때 stack 제거
            else {
                // 제거할 때, 남은 막대기 추가
                answer++;
                stack.pop();
            }
        }
        System.out.println(answer);
    }
    // 1. () 는 레이저
    // () 무시, (((()())(())
}
