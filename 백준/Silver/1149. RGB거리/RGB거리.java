
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int res = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=3;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N+1][4];
        dp[1][1] = map[1][1];
        dp[1][2] = map[1][2];
        dp[1][3] = map[1][3];

        for(int i=2;i<=N;i++) {
            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + map[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + map[i][2];
            dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][3];
        }

        for(int j=1;j<=3;j++) {
            res = Math.min(res, dp[N][j]);
        }
        System.out.println(res);
    }
}
