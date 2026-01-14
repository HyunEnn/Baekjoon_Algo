
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    static int[] dp2;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        dp2 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 누적합을 진행하면서 가는데, 현재 값을 더하는 것과 기존 값을 그대로 가져가는 것중 택일로 dp에 저장
         */
        dp[0] = arr[0];
        dp2[0] = Math.max(arr[0], 0);
        for(int i=1;i<N;i++) {
            // 기존에 더해온 값들보다, 현재 값이 더 크다면 초기화
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            //
            dp2[i] = Math.max(dp[i-1], dp2[i-1] + arr[i]);
        }

        int ans = Integer.MIN_VALUE;
        for(int i=1;i<N;i++) {
            ans = Math.max(ans, dp[i]);
            ans = Math.max(ans, dp2[i]);
        }
        System.out.println(N == 1 ? dp[0] : ans);
    }
}
