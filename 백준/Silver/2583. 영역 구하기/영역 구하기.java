
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int M, N, K;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        v = new boolean[M][N];
        for(int k=0;k<K;k++) {
            st = new StringTokenizer(br.readLine());
            int sc = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            for(int r=sr;r<=er;r++) {
                for(int c=sc;c<=ec;c++) {
                    map[r][c] = 1;
                    v[r][c] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 0 && !v[i][j]) {
                    list.add(bfs(i, j));
                }
            }
        }
        list.sort((a, b) -> a - b);
        System.out.println(list.size());
        for(int i : list) {
            System.out.print(i + " ");
        }
        // (2,0) 부터 (3,3) 으로 생각
    }

    public static int bfs(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        v[r][c] = true;
        int cnt = 1;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && map[nr][nc] == 0 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }
}
