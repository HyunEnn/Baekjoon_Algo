
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init();
        for(int i=1;i<N;i++) {
            for(int j=0;j<i;j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i=0;i<N;i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    static void init() {
        dp = new int[N];
        for(int i=0;i<N;i++) {
            dp[i] = 1;
        }
    }
}
