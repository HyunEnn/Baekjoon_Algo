
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int r, c, w;
        Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            if(this.w == p.w) return this.r - p.r;
            return this.w - p.w;
        }
    }
    static int N;
    static List<Point> list;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        Arrays.fill(arr, -1);
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int w = Integer.parseInt(st.nextToken());
                if(w == 0) continue;
                list.add(new Point(i, j, w));
            }
        }

        Collections.sort(list);

        long ans = 0;
        for(Point p : list) {
            if(uni(p.r, p.c)) {
                ans += p.w;
            }
        }

        System.out.println(ans);
    }

    private static int find(int x) {
        if(arr[x] == -1) return x;
        return arr[x] = find(arr[x]);
    }

    private static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        arr[u] = v;
        return true;
    }
}
