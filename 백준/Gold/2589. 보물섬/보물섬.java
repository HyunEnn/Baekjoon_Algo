
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, W;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[L][W];
        for(int i=0;i<L;i++) {
            String line = br.readLine();
            for(int j=0;j<W;j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int ans = 0;

        for(int i=0;i<L;i++) {
            for(int j=0;j<W;j++) {
                if(map[i][j] == 'L')
                    ans = Math.max(ans, bfs(i, j));
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int r, int c) {
        int res = 0;
        boolean[][] v = new boolean[L][W];
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c, 0));
        v[r][c] = true;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            res = Math.max(res, p.cnt);
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(nr < 0 || nc < 0 || nr >= L || nc >= W) continue;
                if(map[nr][nc] == 'W' || v[nr][nc]) continue;

                v[nr][nc] = true;
                Q.offer(new Point(nr, nc, p.cnt + 1));
            }
        }

        return res;
    }

    // 최단거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 있다.
    // BFS 탐색으로 갈수있는 가장 먼 곳이 보물 위치?
    // 그러면, 각 격자별로 BFS 를 돌면서 가장 큰 값이 나오는 자리가 보물 자리
    // 최대 50 * 50 -> O(3500^2) = 12,250,000
}
