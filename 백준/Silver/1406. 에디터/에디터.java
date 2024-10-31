
import java.util.*;
import java.io.*;

public class Main {
    static String s;
    static int M;
    static Stack<Character> left = new Stack<>();
    static Stack<Character> right = new Stack<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        for(int i=0;i<s.length();i++) {
            left.push(s.charAt(i));
        }
        M = Integer.parseInt(br.readLine());
        // 영어 소문자 만을 기록한다.

        // 커서 시작은 문장 맨 뒤에서 시작한다.
        // L 이면 커서 왼쪽으로 한칸 옮기고, D 이면 오른쪽 이동
        // B 이면 왼쪽 한개를 삭제하고 맨 앞이면 기존 그대로 진행
        // P 이면 문자를 왼쪽에 추가
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            if(a.equals("L") && !left.isEmpty()) {
                right.push(left.pop());
            } else if(a.equals("D") && !right.isEmpty()) {
                left.push(right.pop());
            } else if(a.equals("B") && !left.isEmpty()) {
                left.pop();
            } else if(a.equals("P")) {
                char c = st.nextToken().charAt(0);
                left.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!left.isEmpty()) {
            right.push(left.pop());
        }
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.println(sb.toString());
    }
}
