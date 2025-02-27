
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int t=0;t<N;t++) {
            int m = Integer.parseInt(br.readLine());
            long[] dp = new long[m+1];
            dp[0] = 0;
            if(m >= 1) dp[1] = 1;
            if(m >= 2) dp[2] = 1;
            for(int i=3;i<=m;i++) {
                dp[i] = dp[i-2] + dp[i-3];
            }

            System.out.println(dp[m]);
        }
    }
}
