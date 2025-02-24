
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] map;
    static int N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.hasMoreTokens())
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0]; // dp 시작 지점

        // 점화식 -> dp[i][j] = dp[i-1][j], dp[i-1][j-1] 중 큰 값과 더함
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 왼쪽 끝일 때
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                }
                // 오른쪽 끝일 때
                else if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                }
                // 나머지 경우
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + map[i][j];
                }
            }
        }


        int answer = 0;
        for (int j = 0; j < N; j++) {
            answer = Math.max(answer, dp[N-1][j]);
        }
        System.out.println(answer);
    }
}
