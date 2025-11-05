
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int s, e;
        private Point(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Point p) {
            return this.e - p.e;
        }
    }
    static int N, M;
    static int[] dp;
    static List<Point> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        Arrays.fill(dp, 1);

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Point(first, end));
        }

        Collections.sort(list);

        for(Point p : list) {
            dp[p.e] = Math.max(dp[p.e], dp[p.s] + 1);
        }

        for(int i=1;i<=N;i++) {
            System.out.print(dp[i] + " ");
        }

    }
}
