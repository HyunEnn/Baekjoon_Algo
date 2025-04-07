import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int next, size;
        Point(int next, int size) {
            this.next = next;
            this.size = size;
        }
    }
    static int start, end;
    static int N, M;
    static List<Point>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }
        int maxWeight = 0;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            maxWeight = Math.max(maxWeight, size);
            list[r].add(new Point(c, size));
            list[c].add(new Point(r, size));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = maxWeight;
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            // 갈 수 있는 섬이 있다.
            if(bfs(mid)) {
                answer = mid;
                left = mid + 1;
            }
            // 갈 수 있는 섬이 없다.
            else right = mid - 1;
        }

        System.out.println(answer);

    }

    public static boolean bfs(int mid) {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];
        for(Point o : list[start]) {
            Q.offer(new Point(o.next, o.size));
        }

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 지나갈수 있는 다리면?
            if(p.size >= mid && !v[p.next]) {
                v[p.next] = true;
                // 도착한 섬이 종료 섬이라면, 체크 후 종료
                if(p.next == end) {
                    return true;
                }
                // 아니라면, 그 위치의 섬에서 다리들을 Q에 추가해준다.
                for(Point o : list[p.next]) {
                    Q.offer(new Point(o.next, o.size));
                }
            }
        }

        return false;
    }
}
