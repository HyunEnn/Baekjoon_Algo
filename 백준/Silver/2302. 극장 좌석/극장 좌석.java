
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        int ans = 1;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<M;i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        dp[0] = 1; dp[1] = 1;

        for(int i=2;i<=N;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        int curr = 1;
        for(int i : list) {
            ans *= dp[i - curr];
            curr = i + 1;
        }

//        for(int i=1;i<=N;i++) {
//            System.out.print(dp[i] + " ");
//        }

//        if(curr != 0) System.out.println(ans * dp[N - curr]);
//        else System.out.println(ans);
        System.out.println(ans * dp[N - curr + 1]);
    }
    // VIP 자리는 고정 장벽처럼 사용?
    // 1 2 3 4 5 6 7 8 9 -> (4, 7) 고정
    // 모든 dp 를 1로 채움 Arrays.fill(dp, 0)
    // 그리고 VIP 자리를 dp -1로 설정
    // dp[i] = dp[i-1] + dp[i-2] -> 2 = 1+1 , 3 = 2+1
    // 그리고 if(dp[i] == -1) -> continue 하고, dp[i-1]이 -1이라면 현재 dp[i] = 1, dp[i-1] = 1로 설정
}
