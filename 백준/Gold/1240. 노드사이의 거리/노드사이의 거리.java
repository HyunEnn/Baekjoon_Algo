
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
    static int N, M;
    static boolean[] v;
    static int ans;
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

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 무방향 간선 그래프
            list[a].add(new Point(b, c));
            list[b].add(new Point(a, c));
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int curr = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            v = new boolean[N + 1];
            ans = 0;
            dfs(curr, target, 0);
            System.out.println(ans);
        }
    }

    public static void dfs(int idx, int fin, int size) {
        v[idx] = true;
        // basis
        if(idx == fin) {
            ans = size;
            return;
        }
        // inductive
        for(Point p : list[idx]) {
            if(!v[p.next]) {
                dfs(p.next, fin, size + p.size);
            }
        }
    }
}
