
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int st, end, cost;
        Point(int st, int end, int cost) {
            this.st = st;
            this.end = end;
            this.cost = cost;
        }
    }
    static List<Point>[] list;
    static int N, D;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D + 1];
        list = new ArrayList[D + 1];
        for(int i=0;i<=D;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(e <= D) list[s].add(new Point(s, e, cost));
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0;i<=D;i++) {
            if(i > 0)
                dp[i] = Math.min(dp[i], dp[i-1] + 1);

            for(Point p : list[i]) {
                dp[p.end] = Math.min(dp[p.end], dp[i] + p.cost);
            }
        }

        System.out.println(dp[D]);
    }

}
