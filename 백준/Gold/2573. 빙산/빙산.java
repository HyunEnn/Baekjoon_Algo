
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
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Point> glaciers = new ArrayList<>();
    static List<Point> newGlaciers = new ArrayList<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0)
                    glaciers.add(new Point(i, j)); // 빙산 추가
            }
        }

        // 초기 값이 빙산이 분리되어 있는 지 체크
        if (!checkDiv()) {
            System.out.println("여기서 걸림?");
            System.out.println(0);
            System.exit(0);
        }

        // 빙산을 녹이는 과정을 진행 , 종료는 빙산이 전부 다 녹았거나, 2개 이상으로 나눠지는 경우
        // 2개 이상으로 나눠지는 게 아닌 빙산이 다 녹은 상태로 종료되면 분리되지 않은 것임
        int idx = 1;
        while(true) {
            simulate();
            if(glaciers.size() == 0) {
                idx = 0;
                break;
            }
            if(!checkDiv()) {
                break;
            }
            idx++;
        }

        System.out.println(idx);
    }

    public static boolean checkDiv() {
        // glaciers 의 0번 인덱스를 통해 depth 탐색
        Point p = glaciers.get(0);
        v = new boolean[N][M];

        // 탐색을 통해 나온 값이 glacier 와 같으면 빙하는 하나로 이루어져 있음.
        return bfs(p);
    }

    public static boolean bfs(Point p) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(p.r, p.c));
        v[p.r][p.c] = true;
        int cnt = 1;

        while(!Q.isEmpty()) {
            Point cur = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = cur.r + dr[k];
                int nc = cur.c + dc[k];
                if(inRange(nr, nc) && map[nr][nc] > 0 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    cnt++;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
        return cnt == glaciers.size();
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static void simulate() {
        // tmpMap 을 통해서 계산된 값을 다시 map 으로 이동
        int[][] tmpMap = new int[N][M];
        // glaciers 리스트들의 위치를 통해, 4방 탐색으로 주변에 0의 갯수만큼 -를 시킨다.
        for (int i = 0; i < glaciers.size(); i++) {
            Point p = glaciers.get(i);
            int curr = map[p.r][p.c]; // 현재 빙하 값
            int water = 0; // 주변 물의 갯수
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if (inRange(nr, nc) && map[nr][nc] == 0)
                    water++;
            }

            curr -= water;
            if (curr > 0) {
                tmpMap[p.r][p.c] = curr; // 0 이상일 경우에만 지도에 표시
                // 그리고, 만약에 현재 위치의 빙하가 녹지 않았다면 newGlaciers 에 추가
                newGlaciers.add(new Point(p.r, p.c));
            }
        }

        // 지도와 빙하들을 다시 갱신해줌
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }
        glaciers = new ArrayList<>();
        for (Point p : newGlaciers) {
            glaciers.add(new Point(p.r, p.c));
        }
        newGlaciers.clear();
    }
}
