import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[10001][4]; // 1,2,3으로 만들 수 있는 경우의 수
        dp[0][1] = 1;
        dp[0][2] = 1;
        dp[0][3] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;
        dp[2][1] = 1;
        dp[2][2] = 2;
        dp[2][3] = 2;
        for(int i=3;i<10001;i++) {
            dp[i][1] = 1;
            dp[i][2] = dp[i][1] + dp[i-2][2];
            dp[i][3] = dp[i][2] + dp[i-3][3];
        }
        for(int i=0;i<N;i++) {
            int a = Integer.parseInt(br.readLine());
            System.out.println(dp[a][3]);
        }

//        for(int i=0;i<=10;i++) {
//            System.out.println(dp[i][1] + " " + dp[i][2] + " " + dp[i][3]);
//        }
    }
}