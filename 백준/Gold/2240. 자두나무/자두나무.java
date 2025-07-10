
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int T, W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        dp = new int[T + 1][W + 1];
        int curr = 1;
        for (int t = 1; t <= T; t++) {
            int tree = Integer.parseInt(br.readLine());
            for (int w = 0; w <= W; w++) {
                if (w == 0) {
                    if (tree == 1) dp[t][w] = dp[t - 1][w] + 1;
                    else dp[t][w] = dp[t - 1][w];
                } else {
                    if (w % 2 == 0) {
                        if (tree == 1) dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w] + 1);
                        else dp[t][w] = Math.max(dp[t - 1][w - 1], dp[t - 1][w]);
                    } else {
                        if (tree == 1) dp[t][w] = Math.max(dp[t - 1][w], dp[t - 1][w - 1]);
                        else dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1] + 1);
                    }
                }
            }
        }


        int ans = 0;
        for (int w = 0; w <= W; w++) {
            ans = Math.max(ans, dp[T][w]);
        }
        System.out.println(ans);

    }
}
