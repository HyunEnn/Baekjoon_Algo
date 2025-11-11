
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int s, w;
        Point(int s, int w) {
            this.s = s;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            return Integer.compare(w, p.w);
        }
    }
    static int N, M, R, ans;
    static int[] dist, arr;
    static List<Point>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        ans = Integer.MIN_VALUE;
        /*
        N = 지역 개수, M = 수색 범위, R = 길의 개수
         */
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Point(b, c));
            list[b].add(new Point(a, c));
        }

        // 매 지역에서 시작되는 거리 계산을 통해 최대값 갱신
        for(int i=1;i<=N;i++) {
            dist = new int[N + 1];
            Arrays.fill(dist, -1);
            PriorityQueue<Point> PQ = new PriorityQueue<>();
            PQ.offer(new Point(i, 0)); // 시작 지점과 값을 가지고 간다.
            dist[i] = 0;

            while(!PQ.isEmpty()) {
                Point p = PQ.poll();
                for(Point np : list[p.s]) {
                    // "기존 거리 + 이동할 위치 거리 <= 수색 범위" 라면 PQ 추가
                    if(p.w + np.w <= M) {
                        // 이동할 위치 = max(이동할 위치, 이전 위치 + 간선 거리)
                        dist[np.s] = Math.max(dist[np.s], dist[p.s] + np.w);
                        PQ.offer(new Point(np.s, p.w + np.w));
                    }
                }
            }

            // 이동 가능한 좌표가 추려지면, 그 좌표들이 모두 예은이가 수색할 수 있는 지역이므로 다 합친다.
            int sum = 0;
            for(int x=1;x<=N;x++) {
                if(dist[x] != -1) sum += arr[x];
            }

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
