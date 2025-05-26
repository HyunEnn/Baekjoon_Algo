
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dir, cnt;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Point> list = new ArrayList<>();
    static char[][] map;
    static int[][][] v;
    static int answer;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        v = new int[N][M][4];
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'C') list.add(new Point(i, j));
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                Arrays.fill(v[i][j], Integer.MAX_VALUE);
            }
        }

        answer = Integer.MAX_VALUE;
        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        int startR = list.get(0).r;
        int startC = list.get(0).c;
        int endR = list.get(1).r;
        int endC = list.get(1).c;
        // 나아갈 방향 4개를 모두 Q에 담는다.
        for(int k=0;k<4;k++) {
            v[startR][startC][k] = 0;
            Q.offer(new Point(startR, startC, k, 0));
        }

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 도착 지점에 도착했을 때, 거울 최소 갯수 비교
            if(p.r == endR && p.c == endC) {
                answer = Math.min(answer, p.cnt);
                continue;
            }

            // 현재 위치에서, 방향이 바뀐다 p.cnt + 1을 하는데,
            // 근데 그 자리가 p.cnt 가 최소인게 보장이 되는가?
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(nr < 0||nc < 0||nr >= N||nc >= M) continue;
                if(map[nr][nc] == '*') continue;
                // 현재 자리에서 탐색을 진행할때, 현재 진행 방향과 값이 바뀌게 되면 +1을 진행하는데,
                // 최솟값을 그 자리에 집어넣는다.

                int nextCnt = (p.dir == k) ? p.cnt : p.cnt + 1;
                if(v[nr][nc][k] > nextCnt) {
                    v[nr][nc][k] = nextCnt;
                    Q.offer(new Point(nr, nc, k, nextCnt));
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
