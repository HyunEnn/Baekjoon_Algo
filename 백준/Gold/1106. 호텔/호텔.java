
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int c, p;
        Point(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
    static int C, N;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[C+101];
        PriorityQueue<Point> PQ = new PriorityQueue<>(
                (a, b) -> {
                    if(a.c == b.c) return a.p - b.p;
                    return a.c - b.c;
                }
        );

        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            for(int j=0;j<dp.length-person;j++) {
                // 현재 자리에서 더할 수 없는 자리면 무시
                if(dp[j] == -1) continue;
                // 빈 자리면, 채워넣기
                if(dp[j + person] == -1) dp[j + person] = dp[j] + cost;
                // 이미 값이 존재한다면, 최소값으로 갱신
                else {
                    dp[j + person] = Math.min(dp[j + person], dp[j] + cost);
                }
            }

//            for(int k=0;k<dp.length;k++) {
//                System.out.print(dp[k] + " ");
//            }
//
//            System.out.println();
        }

        int ans = Integer.MAX_VALUE;
        for(int i=C;i<dp.length;i++) {
            if(dp[i] != -1) ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
    /**
     * 12 2
     * 3 5
     * 1 1
     *
     * 1 2 3 4 5 6 7 8 9 10 11 12
     *
     * 가장 작은 비용을 사용하는 것들부터 dp에 저장하고, 그다음 순으로 dp에 누적합을 적용하는 식으로 진행?
     * 1 2 3 4 5 6 7 8 9
     */
}
