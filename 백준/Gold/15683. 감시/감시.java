
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class CCTV {
        int r, c, dir;

        CCTV(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static class backtrack {
        int r, c, idx;
        backtrack(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }

    static List<CCTV> cctvs = new ArrayList<>();
    static List<backtrack> v = new ArrayList<>();
    static int N, M;
    static int min;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) cctvs.add(new CCTV(i, j, map[i][j])); // cctv 저장
            }
        }

        dfs(0, map);
        System.out.println(min);
    }

    public static void dfs(int idx, int[][] map) {
        if(idx == cctvs.size()) {
            min = Math.min(min, calculateMap(map));
            return;
        }

        CCTV c = cctvs.get(idx);
        int[][] tmp = new int[N][M];
        switch (c.dir) {
            case 1:
                // 1. 지도 복사
                copyMap(map, tmp);
                // 2. 방향값으로 진행한 지도 계산
                checkUp(tmp, c.r, c.c);
                // 3. 다음 분기 진행
                dfs(idx + 1, tmp);


                copyMap(map, tmp);
                checkRight(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkDown(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkLeft(tmp, c.r, c.c);
                dfs(idx + 1, tmp);
                break;
            case 2:
                copyMap(map, tmp);
                checkLeft(tmp, c.r, c.c);
                checkRight(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkUp(tmp, c.r, c.c);
                checkDown(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                break;
            case 3:
                copyMap(map, tmp);
                checkUp(tmp, c.r, c.c);
                checkRight(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkRight(tmp, c.r, c.c);
                checkDown(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkDown(tmp, c.r, c.c);
                checkLeft(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkLeft(tmp, c.r, c.c);
                checkUp(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                break;
            case 4:
                copyMap(map, tmp);
                checkLeft(tmp, c.r, c.c);
                checkUp(tmp, c.r, c.c);
                checkRight(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkUp(tmp, c.r, c.c);
                checkRight(tmp, c.r, c.c);
                checkDown(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkRight(tmp, c.r, c.c);
                checkDown(tmp, c.r, c.c);
                checkLeft(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                copyMap(map, tmp);
                checkDown(tmp, c.r, c.c);
                checkLeft(tmp, c.r, c.c);
                checkUp(tmp, c.r, c.c);
                dfs(idx + 1, tmp);

                break;
            case 5:
                copyMap(map, tmp);
                checkUp(tmp, c.r, c.c);
                checkRight(tmp, c.r, c.c);
                checkDown(tmp, c.r, c.c);
                checkLeft(tmp, c.r, c.c);
                dfs(idx + 1, tmp);
                break;
        }
    }

    // 최소 값을 계산하는 식
    public static int calculateMap(int[][] finalMap) {
        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(finalMap[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    // 왼, 오, 아래, 위로 가는 방향별 함수
    public static void checkLeft(int[][] currMap, int r, int c) {
        for(int j=c-1;j>=0;j--) {
            if(currMap[r][j] == 6) break;
            else if(currMap[r][j] == 0) {
                currMap[r][j] = -1;
            }
        }
    }

    public static void checkRight(int[][] currMap, int r, int c) {
        for(int j=c+1;j<M;j++) {
            if(currMap[r][j] == 6) break;
            else if(currMap[r][j] == 0) {
                currMap[r][j] = -1;
            }
        }
    }

    public static void checkUp(int[][] currMap, int r, int c) {
        for(int i=r-1;i>=0;i--) {
            if(currMap[i][c] == 6) break;
            else if(currMap[i][c] == 0)
                currMap[i][c] = -1;
        }
    }

    public static void checkDown(int[][] currMap, int r, int c) {
        for(int i=r+1;i<N;i++) {
            if(currMap[i][c] == 6) break;
            else if(currMap[i][c] == 0)
                currMap[i][c] = -1;
        }
    }

    public static void copyMap(int[][] map, int[][] tmp) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }

}
