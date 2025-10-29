
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static List<Point>[] list;
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Point(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for(int i=1;i<=N;i++) {
            Collections.sort(list[i]);
        }

        int[] dist = new int[N + 1];
        List<Integer>[] adj = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        dist[start] = 0;
        PQ.offer(start);

        while(!PQ.isEmpty()) {
            int curr = PQ.poll();
            // 현재, 있는 값들을 탐색하면서 dist 거리 비교를 통해 최소값을 탐색한다.
            for(Point np : list[curr]) {
                if(dist[np.v] > dist[curr] + np.w) {
                    dist[np.v] = dist[curr] + np.w;
                    adj[np.v] = new ArrayList<>(adj[curr]);
                    adj[np.v].add(curr);
                    PQ.offer(np.v);
                }
            }
        }

        adj[end].add(end);

        System.out.println(dist[end]);
//        for(int i=1;i<=N;i++) {
//            for(int x : adj[i]) System.out.print(x + " ");
//            System.out.println();
//        }
        System.out.println(adj[end].size());
        for(int x : adj[end]) {
            System.out.print(x + " ");
        }
    }
}
