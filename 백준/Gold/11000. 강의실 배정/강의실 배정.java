
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if(this.y == p.y) return this.x - p.x;
            return this.y - p.y;
        }
    }
    static int N;
    static List<Point> list;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y));
        }

        list.sort((a, b) -> {
            if(a.x == b.x)
                return a.y - b.y;
            return a.x - b.x;
        });

        PriorityQueue<Point> PQ = new PriorityQueue<>();

        int ans = 0;

        for(Point p : list) {
            while(!PQ.isEmpty() && PQ.peek().y <= p.x) {
                PQ.poll();
            }

            PQ.offer(p);
//            System.out.println("현재 PQ는? ");
//            for(Point np : PQ) {
//                System.out.println(np.x + " " + np.y + " ");
//            }
            ans = Math.max(ans, PQ.size());
        }

        System.out.println(ans);
    }
}
