
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
    static int N, M;
    static List<Point>[] list;
    static boolean[] v;
    static char[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N + 1];
        arr = new char[N + 1];
        list = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Point(b,c));
            list[b].add(new Point(a,c));
        }

        PriorityQueue<Point> PQ = new PriorityQueue<>();
        long ans = 0;
        int cnt = 0;
        PQ.offer(new Point(1, 0));

        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            if(v[p.v]) continue;
            v[p.v] = true;
            ans += p.w;
            cnt++;
            for(Point np : list[p.v]) {
                // 이미 방문했거나, 같은 성별이라면 무시
                if(v[np.v] || arr[p.v] == arr[np.v]) continue;

                PQ.offer(np);
            }
        }

        if(cnt == N) System.out.println(ans);
        else System.out.println(-1);
    }
}
