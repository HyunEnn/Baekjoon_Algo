
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[11];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for(int i=4;i<=10;i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i=0;i<N;i++) {
            int a = Integer.parseInt(br.readLine());
            System.out.println(dp[a]);
        }
    }
}
