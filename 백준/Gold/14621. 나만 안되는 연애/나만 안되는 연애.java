
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int u, v, w;
        Point(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            if(this.w == p.w) return this.u - p.u;
            return this.w - p.w;
        }
    }
    static int N, M;
    static List<Point> list;
    static char[] arr;
    static int[] depth;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        list = new ArrayList<>();
        depth = new int[N + 1];
        Arrays.fill(depth, -1);

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Point(a, b, c));
        }

        Collections.sort(list);
        long ans = 0;
        int cnt = 0;

        for(Point p : list) {
            int start = p.u;
            int end = p.v;
            if((arr[start] != arr[end]) && uni(start, end)) {
                cnt++;
                ans += p.w;
            }
        }

        if(cnt == N-1) System.out.println(ans);
        else System.out.println(-1);
    }

    private static int find(int x) {
        if(depth[x] == -1) return x;
        return depth[x] = find(depth[x]);
    }

    private static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);

        if(u == v) return false;
        depth[v] = u;
        return true;
    }
    /**
     * 1. 같은 성별끼리의 루트끼리는 연결되면 안된다
     * 2. 최소 스패닝 트리 - Prim
     *  -
     */
}
