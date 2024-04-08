import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int r,c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N,L,R;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Point> list;
    static boolean flag;
    static int cnt;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        printMap();
        cnt = 0;
        list = new ArrayList<>();
        solve();
        System.out.println(cnt);
    }

    /**
     * 1. 우선, 행열에 대해서 인접한 행렬과 값의 차이가 L <= x <= R이면 큐와 리스트에 넣는다.
     * 2. 그러면 탐색할 리스트가 모두 정해지면, 그 리스트들을 토대로 sum 값을 구하고, 개수만큼
     * 나눈 값을, 각 행열의 값으로 처리한다.
     * 3. 위의 작업을 인접한 행렬과 교환할 일이 없을 때까지 진행한다.
     */
    public static void solve() {
        while(true) {
            flag = false;
            v = new boolean[N][N];

            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(!v[i][j])
                        bfs(i, j);
                }
            }
            if(!flag) break;
            else cnt++;
        }
    }

    public static void bfs(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        v[r][c] = true;
        Q.offer(new Point(r, c));
        list.add(new Point(r, c));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if(!v[nr][nc] && L <= Math.abs(map[nr][nc] - map[p.r][p.c])
                            && Math.abs(map[nr][nc] - map[p.r][p.c]) <= R ) {
                        flag = true;
                        v[nr][nc] = true;
                        Q.offer(new Point(nr, nc));
                        list.add(new Point(nr, nc));
                    }
                }
            }
        }
        int sum = 0;
        for(Point p : list) {
            sum += map[p.r][p.c];
        }

        for(Point p : list) {
            map[p.r][p.c] = sum / list.size();
        }

        list.removeAll(list);
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
