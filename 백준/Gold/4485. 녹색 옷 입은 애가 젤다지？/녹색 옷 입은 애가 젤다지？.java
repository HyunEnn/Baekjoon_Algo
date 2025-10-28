
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point p) {
            return this.cnt - p.cnt;
        }
    }
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        while((N = Integer.parseInt(br.readLine())) != 0) {
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            PriorityQueue<Point> PQ = new PriorityQueue<>();
            dist[0][0] = map[0][0];
            PQ.offer(new Point(0, 0, map[0][0]));

            while(!PQ.isEmpty()) {
                Point p = PQ.poll();
                for(int k=0;k<4;k++) {
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];
                    if(inRange(nr, nc)) {
                        int curr = p.cnt + map[nr][nc];
                        if(curr < dist[nr][nc]) {
                            dist[nr][nc] = curr;
                            PQ.offer(new Point(nr, nc, curr));
                        }
                    }
                }
            }

            System.out.println("Problem " + idx++ + ": " + dist[N-1][N-1]);
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
