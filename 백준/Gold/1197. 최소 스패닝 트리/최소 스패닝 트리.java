
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int next, val;
        Point(int next, int val) {
            this.next = next;
            this.val = val;
        }
    }
    static int V, E;
    static List<Point>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new List[V + 1];
        for(int i=1;i<=V;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            list[start].add(new Point(end, val));
            list[end].add(new Point(start, val));
        }

        PriorityQueue<Point> PQ = new PriorityQueue<>(
                (a, b) -> {
                    if(a.val == b.val) return a.next - b.next;
                    return Integer.compare(a.val, b.val);
                }
        );
        boolean[] v = new boolean[V + 1];
        int ans = 0;
        for(Point p : list[1]) {
            PQ.offer(new Point(p.next, p.val));
        }
        v[1] = true;

        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            if(v[p.next]) continue;
            for(Point np : list[p.next]) {
                if(!v[np.next]) PQ.offer(new Point(np.next, np.val));
            }
            v[p.next] = true;
            ans += p.val;
        }

        System.out.println(ans);
    }
    /**
     * 1. 임의의 정점을 선택해 최소 신장 트리에 추가, 해당 정점고 연결된 모든 간선을 우선순위 큐에 추가
     * 2. 우선순위 큐에서 비용이 가장 작은 간선 추가
     * 3. 만약, 해당 간선이 최소 신장 트리에 포함된 두 정점이라면 무시한다.
     * 해당 간선이 최소 신장에 포함된 정점 u와 아닌 정점 v라면, 간선을 연결하고 해당 정점에서 이동가능한 모든 간선들을
     * 우선순위 큐에 저장한다.
     * 4. 최소 신장 트리에 V-1개의 간선이 추가될 때까지 2,3번 과정을 반복한다.
     */
}
