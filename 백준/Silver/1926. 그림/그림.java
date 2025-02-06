
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
    static int N, M;
    static int[][] map;
    static StringTokenizer st;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int check;
    static int max = 0;
    static Queue<Point> Q = new ArrayDeque<>();
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        check = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    Q.offer(new Point(i, j));
                    bfs();
                    check++;
                }
            }
        }

        System.out.println(check);
        System.out.println(max);
    }

    public static void bfs() {
        int cnt = 1;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 이미 탐색한 자리면 패스
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc] && map[nr][nc] == 1) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr,nc));
                    cnt++;
                }
            }
        }

        max = Math.max(max, cnt);
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
