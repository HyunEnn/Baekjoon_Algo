
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Long answer;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(pow(a, b, c));
    }

    public static long pow(int a, int b, int m) {
        if(b == 1) return a % m;
        long val = pow(a, b/2, m);
        val = val * val % m;
        if(b % 2 == 0)
            return val;
        return val * a % m;
    }
}
