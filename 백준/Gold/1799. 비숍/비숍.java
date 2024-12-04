
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
    static int N;
    static int whiteMax = Integer.MIN_VALUE;
    static int blackMax = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {-1, 1, 1, -1}; // 좌상대각, 좌하대각, 우하대각, 우상대각
    static int[] dc = {1, 1, -1, -1};
    static boolean flag;
    static List<Point> White = new ArrayList<>();
    static List<Point> Black = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int check = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    if((i + j) % 2 == 0) Black.add(new Point(i, j));
                    else White.add(new Point(i, j));
                }
            }
        }

        v = new boolean[N][N];
        // 가장 많은 개수부터 체크, 1개는 무조건 가능하니 2개까지 검사
        find(0, 0, White, true);
        find(0, 0, Black, false);
        System.out.println(whiteMax + blackMax);
    }

    public static void find(int idx, int count, List<Point> cells, boolean white) {
        // 모든 칸을 탐색 완료
        if (idx == cells.size()) {
            if(white) whiteMax = Math.max(whiteMax, count);
            else blackMax = Math.max(blackMax, count);
            return;
        }

        Point p = cells.get(idx);
        boolean canPlace = true;

        // 현재 위치에서 4방 대각선 탐색
        for (int k = 0; k < 4; k++) {
            int nr = p.r + dr[k];
            int nc = p.c + dc[k];
            while (inRange(nr, nc)) {
                if (v[nr][nc]) {
                    canPlace = false;
                    break;
                }
                nr += dr[k];
                nc += dc[k];
            }
            if (!canPlace) break;
        }

        // 현재 위치에 비숍을 놓을 수 있다면 놓고 탐색
        if (canPlace) {
            v[p.r][p.c] = true;
            find(idx + 1, count + 1, cells, white);
            v[p.r][p.c] = false; // 백트래킹
        }

        // 현재 위치에 비숍을 놓지 않고 탐색
        find(idx + 1, count, cells, white);


    }

    public static boolean calculate() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] tempMap = new boolean[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                tempMap[i][j] = v[i][j];
                if(tempMap[i][j]) Q.offer(new Point(i, j));
            }
        }
        // 큐를 돌면서, 현재 값 기준에서 r이 0 혹은 N-1, c가 0 혹은 N-1 까지 대각선 전부 탐색
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            int idx = 1;
            for(int k=0;k<4;k++) {
                while(true) {
                    int nr = p.r + (dr[k] * idx);
                    int nc = p.c + (dc[k] * idx);

                    // 범위를 벗어나면 멈추고, 탐색 자리가 true 이면 바로 false 로 종료
                    if(!inRange(nr, nc)) break;
                    if(tempMap[nr][nc]) return false;

                    // 지속 탐색
                    idx++;
                }
                // 탐색 depth 초기화
                idx = 1;
            }
        }

        return true;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
