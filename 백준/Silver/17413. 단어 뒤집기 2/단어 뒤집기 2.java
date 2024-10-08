
import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static List<String> list = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        String s = "";
        for(int i=0;i<line.length();i++) {
            // 괄호로 시작된 값이면 여기서 처리
            if(flag && line.charAt(i) != '>') {
                s += line.charAt(i);
                continue;
            } else if(flag && line.charAt(i) == '>') {
                flag = false;
                s += line.charAt(i);
                sb.append(s);
                s = "";
                continue;
            }
            // 괄호 값이 나오면 저장된 s 가 있다면 sb 로 보내고 시작
            if(line.charAt(i) == '<') {
                if(!s.isEmpty()) {
                    s = reverseString(s);
                    sb.append(s);
                    s = "";
                }
                s += line.charAt(i);
                flag = true;
                continue;
            }

            // 괄호가 아닌 단어나 숫자이면 s에 계속 저장
            if(line.charAt(i) != ' ') {
                s += line.charAt(i);
            }
            // 빈칸을 만나면
            else if(line.charAt(i) == ' '){
                s =reverseString(s);
                sb.append(s).append(" ");
                s = "";
                continue;
            }

            // 끝 값에 대한 처리
            if(i == line.length()-1 && i != ' ') {
                s = reverseString(s);
                sb.append(s);
                s = "";
            }
        }

        System.out.println(sb.toString());
    }

    static String reverseString(String s) {
        char[] c = s.toCharArray();
        String reverse = "";
        for(int j=c.length-1;j>=0;j--) {
            reverse += c[j];
        }
        return reverse;
    }
}
