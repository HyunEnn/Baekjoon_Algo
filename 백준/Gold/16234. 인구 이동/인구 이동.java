
import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, L, R;
    static int cnt;
    static int[][] map;
    static List<Point> list;
    static boolean[][] v;
    static boolean flag;
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

    private static void solve() {
        /**
         * 0. 완탐 조건, 각 행, 열 별로 나라이기 때문에,
         * 방문 배열을 초기화하면서 각 나라간의 인구 차이를 확인하면서 진행
         *  움직인 나라가 없으면, 종료
         * 1. 각 국가의 인구 차이가 L <= x <= R 인지 확인
         * 2. 인구 차이가 범위 내 이면 인구 이동
         * 3. 각 인구 칸을 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
         * 4. 출력은 인구 이동의 날짜를 출력
         * 완탐 -> BFS
         */
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
        Q.offer(new Point(r, c));
        v[r][c] = true;
        list.add(new Point(r, c));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if(!v[nr][nc] && L <= Math.abs(map[nr][nc] - map[p.r][p.c])
                        && Math.abs(map[nr][nc] - map[p.r][p.c]) <= R) {
                        flag = true;
                        v[nr][nc] = true;
                        list.add(new Point(nr, nc));
                        Q.offer(new Point(nr, nc));
                    }
                }
            }
        }

        int sum = 0;
        // list에 들어있는 좌표 값을 기준으로 연합 국경선의 전체 합 계산
        for(Point p : list) {
            sum += map[p.r][p.c];
        }

        // 전체 합의 계산을 개수만큼 나눠서 각 값에 저장
        for (Point p : list) {
            map[p.r][p.c] = sum / list.size();
        }
        list.removeAll(list);
    }

    private static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
