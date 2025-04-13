
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

    static int sangR, sangC, kingR, kingC;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] cr = {-1, -1, 1, 1};
    static int[] cc = {-1, 1, 1, -1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sangR = Integer.parseInt(st.nextToken());
        sangC = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        kingR = Integer.parseInt(st.nextToken());
        kingC = Integer.parseInt(st.nextToken());

        int ans = bfs();
        System.out.println(ans);
    }

    public static int bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[10][9];
        Q.offer(new Point(sangR, sangC, 0));
        v[sangR][sangC] = true; // 현재 상 위치 저장

        while (!Q.isEmpty()) {
            Point p = Q.poll();
            if(p.r == kingR && p.c == kingC)
                return p.cnt;
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(!inRange(nr, nc) || (nr == kingR && nc == kingC)) continue;

                for(int cnt=0;cnt<2;cnt++) {
                    int tr = nr + cr[(k + cnt) % 4];
                    int tc = nc + cc[(k + cnt) % 4];
                    if(inRange(tr, tc) && !(tr == kingR && tc == kingC)) {
                        tr += cr[(k + cnt) % 4];
                        tc += cc[(k + cnt) % 4];

                        if(inRange(tr, tc) && !v[tr][tc]) {
                            v[tr][tc] = true;
                            Q.offer(new Point(tr, tc, p.cnt + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 10 && c >= 0 && c < 9;
    }
}
