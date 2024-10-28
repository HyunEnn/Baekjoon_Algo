import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb.append((int)Math.pow(2, N) - 1).append("\n");
        func(1, 3, N);
        System.out.println(sb.toString());
    }

    public static void func(int a, int b, int n) {
        if(n == 1) {
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }
        func(a, 6-a-b, n-1);
        sb.append(a).append(" ").append(b).append("\n");
        func(6-a-b, b, n-1);
    }
}
