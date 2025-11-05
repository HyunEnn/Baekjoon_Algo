
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int N, T;
    static List<Integer>[] list;
    static boolean[] v;
    static int ans = Integer.MAX_VALUE;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        list = new ArrayList[200001];
        for(int i=0;i<200001;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[y].add(x);
        }
        v = new boolean[N];
        bfs();
        System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
    }

    private static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(0, 0, 0)); // (0, 0) 시작

        while(!Q.isEmpty()) {
            Point p = Q.poll();
//            System.out.println(p.r + " " + p.c + " " + p.cnt);
            if(p.c == T) {
                ans = p.cnt;
                return;
            }

            int start = Math.max(p.c - 2, 0);
            int end = Math.min(p.c + 2, 1_000_000);

            for(int i=start;i<=end;i++) {
                for(int j=0;j<list[i].size();j++) {
                    int curr = list[i].get(j);
                    if(Math.abs(curr - p.r) <= 2) {
                        Q.offer(new Point(curr, i, p.cnt + 1));
                        list[i].remove(j);
                        j--;
                    }
                }
            }
        }
    }
}
