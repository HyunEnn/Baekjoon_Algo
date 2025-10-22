
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
    static int N;
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0}; 
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Deque<Point> Q = new ArrayDeque<>();
        int[][] v = new int[N][N];
        for(int i=0;i<N;i++) Arrays.fill(v[i], Integer.MAX_VALUE);
        Q.offer(new Point(0, 0, 0));
        v[0][0] = 0;

        while(!Q.isEmpty()) {
            Point p = Q.pollFirst();
            if(p.r == N-1 && p.c == N-1) return v[p.r][p.c];

            int black = 0;

            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc)) {
                    // black 일 때, 현재 값에서 1을 추가한 값이 기존 값보다 작을 수 있다면 교체
                    if(map[nr][nc] == 0 && p.cnt + 1 < v[nr][nc]) {
                        v[nr][nc] = p.cnt + 1;
                        Q.offerLast(new Point(nr, nc, p.cnt + 1));
                    }
                    else if(map[nr][nc] == 1 && p.cnt < v[nr][nc] ) {
                        v[nr][nc] = p.cnt;
                        Q.offerFirst(new Point(nr, nc, p.cnt));
                    }
                }
            }
        }

        return 0;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    /**
     * 1. 0-1 bfs 조건
     * 1-1.
     * 2. 다익스트라 조건
     * 2-1. 그래프에 값을 다 넣는다
     * 2-2. PQ 에서 현재 값들을 우선순위로 정렬한다.
     * 2-3.
     */
}
