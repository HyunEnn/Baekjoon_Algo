
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

    static int N;
    static int[] water;
    static List<Point>[] list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        water = new int[N];
        for (int i = 0; i < N; i++) {
            water[i] = Integer.parseInt(br.readLine());
        }
        list = new List[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a != 0) list[i].add(new Point(j, a));
            }
        }

        PriorityQueue<Point> PQ = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) return a.next - b.next;
            return a.val - b.val;
        });
        boolean[] v = new boolean[N];
        for (int i = 0; i < N; i++) PQ.offer(new Point(i, water[i]));

        int ans = 0;
        while (!PQ.isEmpty()) {
            // 0 위치의 우물부터 시작함. 단, 현재 자리는 미방문으로 시작
            Point p = PQ.poll();
            if (v[p.next]) continue;

            for (Point np : list[p.next]) {
                if (!v[np.next]) PQ.offer(np);
            }
            ans += p.val;
            v[p.next] = true;
        }
        System.out.println(ans);
    }

}
// 제자리 물을 퍼는건 dist[]에 저장하고, 나머지는 간선리스트에 저장해서 프림 방식으로
// 우선순위 큐를 통해 접근한다.
