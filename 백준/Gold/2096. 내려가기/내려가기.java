
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] stairs;
    static int[][] dp;
    static int[][] minDp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N][3];
        dp = new int[N][3];
        minDp = new int[N][3];

        for(int i=0;i<N;i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                stairs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫줄은 그냥 채우기
        for(int j=0;j<3;j++) {
            dp[0][j] = stairs[0][j];
            minDp[0][j] = stairs[0][j];
        }

        // 여기가 MAX 값 구하는 곳
        // 2번째 줄부터 0은 위의 0, 1 자리에서 1은 0, 1, 2 2는 2, 1로부터
        for(int i=1;i<N;i++) {
            for(int j=0;j<3;j++) {
                switch(j) {
                    case 0: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j+1]);
                        minDp[i][j] = Math.min(minDp[i-1][j], minDp[i-1][j+1]);
                        break;
                    case 1: dp[i][j] = Math.max(Math.max(dp[i-1][j-1], dp[i-1][j]), dp[i-1][j+1]);
                        minDp[i][j] = Math.min(Math.min(minDp[i-1][j], minDp[i-1][j-1]), minDp[i-1][j+1]);
                        break;
                    case 2: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                        minDp[i][j] = Math.min(minDp[i-1][j], minDp[i-1][j-1]);
                        break;
                }
                dp[i][j] += stairs[i][j];
                minDp[i][j] += stairs[i][j];
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int j=0;j<3;j++) {
            max = Math.max(max, dp[N-1][j]);
            min = Math.min(min, minDp[N-1][j]);
        }

        System.out.println(max + " " + min);
    }
    /**
     * dp 문제
     * 3
     * 1 2 3
     * 4 5 6
     * 4 9 0
     *
     * 1 2 3
     *
     */
}
