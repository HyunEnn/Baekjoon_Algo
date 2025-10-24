
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int wh, num;

        Point(int wh, int num) {
            this.wh = wh;
            this.num = num;
        }

        @Override
        public int compareTo(Point o) {
            if (this.num == o.num) return this.wh - o.wh;
            return o.num - this.num;
        }
    }

    static int N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            PQ.offer(new Point(i, B[i]));
        }

        int cnt = 0, ans = 0;
        while (!PQ.isEmpty()) {
            Point p = PQ.poll();
//          System.out.println("현재 뽑은값: "  + p.num);
            // PQ의 현재 뽑은 값에 대해서 존재하면
            ans += A[cnt] * p.num;
            cnt++;
//          System.out.println("현재 ans : " + ans);
        }


        System.out.println(ans);
    }
}
