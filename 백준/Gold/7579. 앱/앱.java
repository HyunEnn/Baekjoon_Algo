
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] memo;
    static int[] values;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memo = new int[N + 1];
        values = new int[N + 1];
        int[] dp = new int[100001]; // 최대 가치
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            memo[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=1;i<=N;i++) {
            values[i] = Integer.parseInt(st.nextToken());
            sum += values[i];
        }

        for(int i=1;i<=N;i++) {
            for(int j=sum;j>=values[i];j--) {
                dp[j] = Math.max(dp[j], dp[j-values[i]] + memo[i]);
            }
//            for(int j=0;j<=sum;j++) {
//                System.out.print(dp[j] + " ");
//            }
//            System.out.println();
        }

        for(int i=0;i<=sum;i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
    /**
     * 그니까, 앱을 비활성화할때 가능한 것중에 다시 키는 비용을 최소화할 수 있는 방법을 찾아야한다?
     * 5 60
     * 30 10 20 35 40
     * 3 0 3 5 4
     * 
     * ex) 5 40 이라고 하면, 40 4가 바로 탈출해야함
     */
}
