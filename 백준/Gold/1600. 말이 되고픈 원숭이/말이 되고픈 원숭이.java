
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, horse, cnt;
        Point(int r, int c, int horse, int cnt) {
            this.r = r;
            this.c = c;
            this.horse = horse;
            this.cnt = cnt;
        }
    }
    static int K, H, W;
    static int[][] map;
    static StringTokenizer st;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] horseMove = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for(int i=0;i<H;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulate();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void simulate() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][][] v = new boolean[H][W][K+1];
        Q.offer(new Point(0, 0, K, 0));
        v[0][0][K] = true;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 도착 위치 확인
            if(p.r == H-1 && p.c == W-1) {
                ans = Math.min(ans, p.cnt);
                break;
            }
            // 단순 사방탐색
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc][p.horse] && map[nr][nc] != 1) {
                    v[nr][nc][p.horse] = true;
                    Q.offer(new Point(nr, nc, p.horse, p.cnt + 1));
                }
            }
            // 말 이동 탐색
            if(p.horse > 0) {
                for(int k=0;k<8;k++) {
                    int nr = p.r + horseMove[k][0];
                    int nc = p.c + horseMove[k][1];
                    // 범위를 벗어나지 않고, 벽인 자리 이동 X
                    if(inRange(nr, nc) && !v[nr][nc][p.horse - 1] && map[nr][nc] != 1) {
                        v[nr][nc][p.horse - 1] = true;
                        Q.offer(new Point(nr, nc, p.horse - 1, p.cnt + 1));
                    }
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }
}
