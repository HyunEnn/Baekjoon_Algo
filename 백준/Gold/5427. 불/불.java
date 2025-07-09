
import java.util.*;

public class Main {
    static int w, h;
    static char[][] map;
    static int[][] fireTime;
    static int[][] sangkeunTime;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int tc = 0; tc < t; tc++){
            w = sc.nextInt();
            h = sc.nextInt();
            map = new char[h][w];
            fireTime = new int[h][w];  // 불이 도착하는 시간 저장
            sangkeunTime = new int[h][w];  // 상근이가 도착하는 시간 저장

            Queue<int[]> fQ = new ArrayDeque<>();
            Queue<int[]> sQ = new ArrayDeque<>();

            for (int i = 0; i < h; i++) {
                String row = sc.next();
                for (int j = 0; j < w; j++) {
                    map[i][j] = row.charAt(j);
                    fireTime[i][j] = -1;  // 불 방문 안함(-1)으로 초기화
                    sangkeunTime[i][j] = -1;  // 상근 방문 안함(-1)으로 초기화

                    // 불 시작점 저장 및 시간 배열 0초로 갱신
                    if (map[i][j] == '*') {
                        fQ.add(new int[]{i, j});
                        fireTime[i][j] = 0;
                    }
                    // 상근이 시작점 저장 및 시간 배열 0초로 갱신
                    else if (map[i][j] == '@') {
                        sQ.add(new int[]{i, j});
                        sangkeunTime[i][j] = 0;
                    }
                }
            }

            // bfs로 각 칸에 대해 불이 퍼지는 시간 기록
            while (!fQ.isEmpty()) {
                int[] cur = fQ.poll();
                int r = cur[0], c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    // map 범위 내, 벽이 아니고, 불이 방문한적 없으면 방문체크
                    if(nr < h && nr >= 0 && nc < w && nc >= 0 && map[nr][nc] != '#' && fireTime[nr][nc] == -1){
                        fireTime[nr][nc] = fireTime[r][c] + 1;
                        fQ.add(new int[]{nr, nc});
                    }
                }
            }

            // bfs로 상근이 이동 (불보다 빨리 도착할 수 있는 곳만 이동)
            boolean check = false;  // 상근이 탈출 여부
            int answer = -1;

            while (!sQ.isEmpty()) {
                int[] cur = sQ.poll();
                int r = cur[0], c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    // map의 범위를 벗어나면 탈출
                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
                        answer = sangkeunTime[r][c] + 1; // 이전 시간+1
                        check = true;
                        break;
                    }

                    // 벽x, 상근 방문x, (불 방문x || 불보다 먼저 도착)
                    if (map[nr][nc] != '#' && sangkeunTime[nr][nc] == -1 &&
                            (fireTime[nr][nc] == -1 || sangkeunTime[r][c]+1 < fireTime[nr][nc])){
                        sangkeunTime[nr][nc] = sangkeunTime[r][c] + 1; // 상근이 도착 시간 갱신
                        sQ.add(new int[]{nr, nc});
                    }
                }
                // 탈출하면 break
                if (check) break;
            }

            if (check) System.out.println(answer);
            else System.out.println("IMPOSSIBLE");
        }
    }
}
