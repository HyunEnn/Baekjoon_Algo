
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int[][] arr;
        int[][] dp;
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[3][N + 1];
            dp = new int[3][N + 1];
            for (int i = 1; i <= 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[1][1] = arr[1][1];
            dp[2][1] = arr[2][1];
            if(N >= 2) {
                dp[1][2] = dp[2][1] + arr[1][2];
                dp[2][2] = dp[1][1] + arr[2][2];
            }

            for (int j = 3; j <= N; j++) {
                dp[1][j] = Math.max(dp[2][j - 1], dp[2][j - 2]) + arr[1][j];
                dp[2][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + arr[2][j];
            }

            System.out.println(Math.max(dp[1][N], dp[2][N]));
        }
        /**
         * 50 10 100 20 40
         * 30 50 70 10 60
         *
         * 10 30 10 50 100 20 40
         * 20 40 30 50 60 20 80
         *
         * 50 40 200 130 250
         * 30 100 110 210 260
         *
         */
    }
}
