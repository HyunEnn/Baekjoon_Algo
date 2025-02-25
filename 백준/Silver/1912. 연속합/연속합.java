
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] dp;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 연속된 수를 기준으로 최대 값을 찾아야 함
        // 점화식 : 이전에 더해진 값 + 현재 값 vs 현재 값
        // dp[i] = dp[i-1] + arr[i], arr[i]
        dp[1] = arr[1];
        for(int i=2;i<=N;i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
        }

        int answer = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
