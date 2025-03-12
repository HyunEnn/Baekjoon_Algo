
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int vertex, cost;
        Point(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point p) {
            return cost - p.cost;
        }
    }
    static int N, M, X;
    static List<Point>[] graph;
    static List<Point>[] newGraph;
    static int[] X_dist;
    static int[] All_dist;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        X_dist = new int[N + 1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[vertex].add(new Point(node, cost));
        }

        Arrays.fill(X_dist, Integer.MAX_VALUE);

        dijkstra(X_dist, graph);

        // 그래프 뒤집기
        newGraph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            newGraph[i] = new ArrayList<>();
        }

        for(int i=1;i<=N;i++) {
            for(Point p : graph[i]) {
                newGraph[p.vertex].add(new Point(i, p.cost));
            }
        }
        All_dist = new int[N+1];
        Arrays.fill(All_dist, Integer.MAX_VALUE);

        dijkstra(All_dist, newGraph);

        int max = 0;
        for(int i=1;i<=N;i++) {
            max = Math.max(max, X_dist[i] + All_dist[i]);
        }

        System.out.println(max);
    }

    public static void dijkstra(int[] dist, List<Point>[] graph) {
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        PQ.offer(new Point(X, 0));
        dist[X] = 0;

        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            int curr = p.vertex;
            int cost = p.cost;

            if(dist[curr] < cost) continue;

            for(Point np : graph[curr]) {
                int newCost = np.cost + cost;
                if(newCost < dist[np.vertex]) {
                    dist[np.vertex] = newCost;
                    PQ.offer(new Point(np.vertex, newCost));
                }
            }
        }
    }
}
