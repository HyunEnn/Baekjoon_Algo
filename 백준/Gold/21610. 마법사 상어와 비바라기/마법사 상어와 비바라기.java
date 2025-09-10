
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
    static int N, M;
    static int[][] map;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] chr = {-1, -1, 1, 1}; // 대각선 탐색 11시부터 시계방향으로
    static int[] chc = {-1, 1, 1, -1};
    static boolean[][] v;
    static List<Point> clouds;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();

        clouds.add(new Point(N-1, 0));
        clouds.add(new Point(N-1, 1));
        clouds.add(new Point(N-2, 0));
        clouds.add(new Point(N-2, 1));

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1; // 방향
            int s = Integer.parseInt(st.nextToken()); // 이동
            moveCloud(d, s % N);
//            printMap();
        }

        System.out.println(countMap());

    }

    public static void moveCloud(int d, int s) {
        List<Point> tmp = new ArrayList<>();
        v = new boolean[N][N];
        for(Point p : clouds) {
            int nr = (p.r + (dr[d] * s) + N) % N;
            int nc = (p.c + (dc[d] * s) + N) % N;
            // 이동된 자리로 구름 이동
            tmp.add(new Point(nr, nc));
            v[nr][nc] = true; // 현재 구름 위치는 다시 체크되면 안됨
        }

        clouds = tmp;
        cloudRain();
        initCloud();
    }

    public static int countMap() {
        int sum = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

    public static void initCloud() {
        clouds = new ArrayList<>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] >= 2 && !v[i][j]) {
                    clouds.add(new Point(i, j));
                    map[i][j] -= 2;
                }
            }
        }
    }

    public static void cloudRain() {

        for(Point p : clouds) map[p.r][p.c]++;

        for(Point p : clouds) {
            // 대각선 4방향 기준 탐색해서, 값이 0이 아니라면, cnt 만큼 증가
            int cnt = 0;
            for(int k=0;k<4;k++) {
                int nr = p.r + chr[k];
                int nc = p.c + chc[k];
                if(inRange(nr, nc) && map[nr][nc] > 0) cnt++;
            }
            map[p.r][p.c] += cnt;
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void printMap(){
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *  비구름은 (N,1) (N,2) (N-1,1) (N-1,2) 라고 문제에서 제시
     * 현재 내 풀이에선 (N-1,0) (N-1,1) (N-2,0) (N-2,1) 로 수정
     * 방향은 9시부터 시계방향으로 0부터 진행된다.
     * 1. 모든 구름은 d 방향으로 s 칸 이동한다.
     * 2. 각 구름에서 비가 내리면, 칸의 바구니가 +1 된다.
     * 3. 기존 구름들 증발
     * 4. 물이 증가한 칸 물복사 버그를 실행하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 바구니의 물의 양이 증가한다
     * ex) 현재 자리가 증가할 수 있는 최대는 4
     * 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸은 구름이 생성되며, 물의 양이 2 줄어든다.(단, 이전에 구름이 생겼던 칸은 제거)
     * 고로, 구름의 갯수는 제한되지않고 자유자재로 변한다. list 로 설정
     *
     * 5 4
     * 0 0 1 0 2
     * 2 3 2 1 0
     * 4 3 2 9 0
     * 1 0 2 9 0
     * 8 8 2 1 0
     * 1 3
     * 3 4
     * 8 1
     * 4 8
     */


}
