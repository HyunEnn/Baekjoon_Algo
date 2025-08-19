import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] pos;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pos = new int[N+1];
        dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            pos[Integer.parseInt(st.nextToken())] = i;
        }

        int max = 1;
        dp[1] = 1;
        for(int i=2;i<=N;i++) {
            if(pos[i] > pos[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else dp[i] = 1;
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }

    /**
     * 5 2 4 1 3 -> [4,
     */
}
