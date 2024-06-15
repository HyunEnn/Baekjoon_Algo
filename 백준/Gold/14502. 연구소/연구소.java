
import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] v;
    static int max = Integer.MIN_VALUE;
    static int N, M;
    static int[][] map;
    static int[][] copyMap;
    static List<Pair> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    list.add(new Pair(i, j));
            }
        }

        wall(0);
        System.out.println(max);
    }

    public static void wall(int cnt) {

        if(cnt == 3) {
            max = Math.max(max, bfs());
            return;
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    wall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static int bfs() {

        copy();
        Queue<Pair> Q = new ArrayDeque<>();
        for(Pair p : list) {
            Q.offer(p);
        }
        while(!Q.isEmpty()) {
            Pair p = Q.poll();
            for(int i=0;i<4;i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if((nr < 0 || nr > N-1 || nc < 0 || nc > M-1) || copyMap[nr][nc] != 0)
                    continue;
                copyMap[nr][nc] = 2;
                Q.offer(new Pair(nr, nc));
            }
        }

        return countMap();
    }

    public static int countMap() {

        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(copyMap[i][j] == 0)
                    cnt++;
            }
        }

        return cnt;
    }

    public static void copy() {

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }
    /**
     * 1. 우선 벽을 3개 세워야 하기 때문에 순열 말고 조합으로 진행한다.
     * 2. 3개의 벽이 세워졌을 때, bfs/dfs 탐색을 통해서 안전 영역의 최대값을 찾는다.
     */
}
