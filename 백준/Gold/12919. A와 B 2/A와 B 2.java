
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Main {
    static String a, b;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();

        dfs(new StringBuilder(b));

        if(flag) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(StringBuilder sb) {
        if(flag) return;
        // 범위를 벗어나면, return
        if(sb.length() < a.length()) return;
        // 값이 같으면
        if(sb.toString().equals(a)) {
            flag = true;
            return;
        }

        if(sb.charAt(sb.length() - 1) == 'A') {
            sb.deleteCharAt(sb.length() - 1);
            dfs(sb);
            sb.append('A');
        }

        if(sb.charAt(0) == 'B') {
            sb.deleteCharAt(0);
            sb.reverse();
            dfs(sb);
            sb.reverse();
            sb.insert(0, 'B');
        }
    }
    //문자열의 뒤에 A를 추가한다.
    //문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
}
