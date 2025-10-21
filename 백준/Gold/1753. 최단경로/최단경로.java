
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int v, w;
        Point(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            return this.w - p.w;
        }
    }
    static int V, E;
    static int[] dist;
    static boolean[] v;
    static List<Point>[] list;
    static StringTokenizer st;
    static int start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        for(int i=1;i<=V;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Point(v, w));
        }

        PriorityQueue<Point> PQ = new PriorityQueue<>();

        dist = new int[V + 1];
        v = new boolean[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PQ.offer(new Point(start, 0));
        dist[start] = 0;

        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            if(v[p.v]) continue;
            v[p.v] = true;

            for(Point np : list[p.v]) {
                if(dist[np.v] > dist[p.v] + np.w) {
                    dist[np.v] = dist[p.v] + np.w;
                    PQ.offer(new Point(np.v, dist[np.v]));
                }
            }
        }

        for(int i=1;i<=V;i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}
