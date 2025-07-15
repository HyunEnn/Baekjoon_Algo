
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        if(N >= 2) dp[2] = dp[1] + arr[2];

        for(int i=3;i<=N;i++) {
            // 현재 잔을 선택안할 경우,
            int zero = dp[i-1];
            // 전전 잔과 현재 잔을 마시는 경우,
            int first = dp[i-2] + arr[i];
            // 이전 앞잔과 현재 잔을 마실 경우,
            int second = dp[i-3] + arr[i-1] + arr[i];

            dp[i] = Math.max(zero, Math.max(first, second));
        }

        int ans = 0;

        for(int i=1;i<=N;i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
