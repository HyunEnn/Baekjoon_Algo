
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, 1);

        for(int i=1;i<N;i++) {
            for(int j=0;j<i;j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int answer = 0;
        for(int i=0;i<N;i++) {
            answer = Math.max(dp[i], answer);
        }

        System.out.println(N - answer);
    }
}
