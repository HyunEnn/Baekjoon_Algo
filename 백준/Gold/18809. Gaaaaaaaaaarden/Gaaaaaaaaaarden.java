
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

    static int N, M, G, R;
    static boolean[] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static List<Point> possibles = new ArrayList<>();
    static List<Point> greens = new ArrayList<Point>();
    static List<Point> reds = new ArrayList<Point>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken()); // 녹색 지역
        R = Integer.parseInt(st.nextToken()); // 빨강 지역
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0 이면 호수
                if (map[i][j] == 2) {
                    possibles.add(new Point(i, j));
                }
            }
        }

        v = new boolean[possibles.size()];
        // 1. 가능한 녹색과 빨간색 위치 조합으로 선정
        find(0, 0, 0);
        // 2. 선정된 조합으로 각자 위치에서 bfs 탐색을 진행한다

        // 3. 탐색을 통해, 같은 전파 시기에 도달하면 꽃을 피움

        // 4. 피울 수 있는 꽃의 최대 갯수는?
        System.out.println(max);

    }

    public static void find(int g, int r, int idx) {
        if (g == G && r == R) {
            max = Math.max(max, bfs());
            return;
        }

        if(idx >= possibles.size()) {
            return;
        }

        Point p = possibles.get(idx);

        if (g < G) {
            greens.add(new Point(p.r, p.c));
            find(g + 1, r, idx + 1);
            greens.remove(greens.size() - 1);
        }
        if (r < R) {
            reds.add(new Point(p.r, p.c));
            find(g, r + 1, idx + 1);
            reds.remove(reds.size() - 1);
        }

        // 선택하지 않을 경우
        find(g, r, idx + 1);
    }

    public static int bfs() {
        // Q를 동시에 진행하여 탐색을 진행하여야 한다.
        int flower = 0;
        Queue<int[]> Q = new ArrayDeque<>();
        // 하나는 전파 상황, 하나는 진행된 시간 초
        int[][] time = new int[N][M];
        int[][] state = new int[N][M];
        // 초기 세팅
        for(Point p : greens) {
            state[p.r][p.c] = 1;
            time[p.r][p.c] = 0;
            Q.offer(new int[] {p.r, p.c, 1}); // 녹색은 1
        }
        for(Point p : reds) {
            state[p.r][p.c] = 2;
            time[p.r][p.c] = 0;
            Q.offer(new int[] {p.r, p.c, 2}); // 빨강은 2
        }

        // 탐색 시작
        while(!Q.isEmpty()) {
            int[] curr = Q.poll();
            int r = curr[0];
            int c = curr[1];
            int color = curr[2];
            
            // 중간에 꽃으로 변했을 수도 있음
            if(state[r][c] == 3) continue;

            for(int k=0;k<4;k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if(canGo(nr, nc, state)) {
                    // 이동한 자리가 아직 아무것도 없을 때
                    if(state[nr][nc] == 0) {
                        state[nr][nc] = color;
                        time[nr][nc] = time[r][c] + 1;
                        Q.offer(new int[] {nr, nc, color});
                    }
                    // 이미 있으면 꽃이 필 수 있는 지 체크
                    else if(state[nr][nc] != color && time[nr][nc] == time[r][c] + 1){
                        state[nr][nc] = 3;
                        flower++;
                    }
                }
            }
        }

        return flower;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static boolean canGo(int nr, int nc, int[][] state) {
        if(!inRange(nr, nc)) return false;
        // 가는 길이 호수가 아니고, 꽃이 피지 않았어야 함
        if(map[nr][nc] != 0 && state[nr][nc] != 3) return true;
        return false;
    }
}

