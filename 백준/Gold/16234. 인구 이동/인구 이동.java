
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
    static List<Point> list;
    static int N, L, R;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] v;

    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while(true) {
            boolean flag = false;
            v = new boolean[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(v[i][j]) continue;

                    list = new ArrayList<>();
                    v[i][j] = true;
                    list.add(new Point(i, j));

                    findDFS(i, j);
                    // 리스트가 안비었으면, 계산 처리
                    if(list.size() >= 2) {
//                        System.out.println(list.size());
                        calculate();
                        flag = true;
                    }
                }
            }

            if(!flag) break;
            else ans++;
        }

        System.out.println(ans);
    }

    private static void findDFS(int r, int c) {
        // inductive
        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            // 격자 안에 있고, 최소 최대 거리 안에 있는 값이면 연합 처리해야함
            if(inRange(nr, nc)) {
                // 이미 연합에 해당한곳이면 패스
                if(v[nr][nc]) continue;
                int curr = Math.abs(map[r][c] - map[nr][nc]);

                if(curr >= L && curr <= R) {
                    v[nr][nc] = true;
                    list.add(new Point(nr, nc));
                    findDFS(nr, nc);
                }
            }
        }
    }

    private static void calculate() {
        int num = 0, cnt = 0;
        for(Point p : list) {
            num += map[p.r][p.c];
            cnt++;
        }

        int newNum = num / cnt;
        for(Point p : list) {
            map[p.r][p.c] = newNum;
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
