
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        // 이친수는 0으로 시작하지 않는다.
        // 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
        // 3 -> 100, 101
        // 마지막 수가 0,1 인지 생각, 마지막이 0이면 앞에 1이든 0이든 상관x, 1이면 앞에 무조건 0
        // dp[i][0] = dp[i-1][0] + dp[i-1][1] , dp[i][1] = dp[i-1][0]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][2];
        dp[1][1] = 1; // 시작은 무조건 1
        for(int i=2;i<=N;i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }
}
