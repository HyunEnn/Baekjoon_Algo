
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] stairs;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N+1];
        dp = new int[N+1][3];
        for(int i=1;i<=N;i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // N == 1 예외 처리
        if (N == 1) {
            System.out.println(stairs[1]);
            return;
        }

        dp[1][1] = stairs[1];
        dp[1][2] = 0;
        dp[2][1] = stairs[2];
        dp[2][2] = dp[1][1] + stairs[2];

        for(int i=3;i<=N;i++) {
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
            dp[i][2] = dp[i-1][1] + stairs[i];
        }

        System.out.println(dp[N][1] > dp[N][2] ? dp[N][1] : dp[N][2]);
    }
}
