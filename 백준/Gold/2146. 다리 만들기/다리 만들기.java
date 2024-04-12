import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int r, c, count;
        Point(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static int N;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int answer;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N][N];
        map = new int[N][N];
        answer = Integer.MAX_VALUE;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int idx = 1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1) {
                    idx++;
                    checkIsland(idx, i, j);
                }
            }
        }

//        printMap();

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] > 1){
                    int island = map[i][j];
                    int bridge = FindBridge(i, j, island);
                    if(bridge != 0)
                        answer = Math.min(answer, bridge);
                }

            }
        }
        System.out.println(answer);
    }

    public static int FindBridge(int r, int c, int island) {
        Queue<Point> Q = new ArrayDeque<>();
        int cnt = 0;
        Q.offer(new Point(r, c, 0));
        v = new boolean[N][N];
        v[r][c] = true;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1)
                    continue;
                if(map[nr][nc] != 0 && map[nr][nc] != island) {
                    return p.count;
                }
                if(map[nr][nc] == 0 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc, p.count + 1));
                }
            }
            cnt++;
        }
        return 0;
    }

    public static void checkIsland(int idx, int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c, 0));
        map[r][c] = idx;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if(map[nr][nc] == 1) {
                        map[nr][nc] = idx;
                        Q.offer(new Point(nr, nc, 0));
                    }
                }
            }
        }
    }

    public static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}