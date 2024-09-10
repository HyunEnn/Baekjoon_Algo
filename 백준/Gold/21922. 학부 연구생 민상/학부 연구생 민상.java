
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static class Air {
        int r, c, dir;
        Air(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int cnt;
    static Queue<Air> Q = new ArrayDeque<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M][4];
        cnt = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    for(int k=0;k<4;k++) {
                        Q.add(new Air(i, j, k));
                    }
                }
            }
        }

        bfs();

        System.out.println(cnt);

    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void bfs() {
        while(!Q.isEmpty()) {
            Air a = Q.poll();
            if(!inRange(a.r, a.c) || v[a.r][a.c][a.dir]) {
                continue;
            }
            v[a.r][a.c][a.dir] = true;
            int dir = a.dir;

            if(map[a.r][a.c] == 0) {
                Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
            } else if(map[a.r][a.c] == 1) {
                if(dir == 0 || dir == 2) {
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                }
            } else if(map[a.r][a.c] == 2) {
                if(dir == 1 || dir == 3) {
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                }
            } else if(map[a.r][a.c] == 3) {
                if(dir == 0 || dir == 2) {
                    dir++;
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                } else {
                    dir--;
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                }
            } else {
                if(dir == 1 || dir == 3) {
                    dir = (dir + 1) % 4;
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                } else {
                    dir = (dir + 3) % 4;
                    Q.offer(new Air(a.r + dr[dir], a.c + dc[dir], dir));
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(isVisit(i, j))
                    cnt++;
            }
        }
    }

    static boolean isVisit(int r, int c) {
        for(int i=0;i<4;i++) {
            if(v[r][c][i])
                return true;
        }
        return false;
    }

    static void dfs(int dir, int r, int c) {
        if(!inRange(r, c)) // 범위를 벗어나면 패스
            return;
        if(!v[r][c][dir]) { // 탐색을 안했으면 추가하고, 했으면 추가 없이 진행
            v[r][c][dir] = true; // 현재 위치 저장
            cnt++; // 탐색한 위치 추가
        }

//        System.out.println("탐색한 현재 위치 = r : " +  r + ", c = " + c);
        if(map[r][c] == 0) {
            dfs(dir, r + dr[dir], c + dc[dir]); // 현재 방향 그대로 다음 위치 탐색
        } else if(map[r][c] == 1) {
            // 좌우 에서 접근하는 거 아니면 무시하고 진행?
            if(dir == 0 || dir == 2) {
                dfs(dir, r + dr[dir], c + dc[dir]);
            }
        } else if(map[r][c] == 2) {
            // 상하 접근을 제외하고 그냥 진행
            if(dir == 1 || dir == 3) {
                dfs(dir, r + dr[dir], c + dc[dir]);
            }
        } else if(map[r][c] == 3) {
            if(dir == 0 || dir == 2) {
                dir++;
                dfs(dir, r + dr[dir], c + dc[dir]);
            } else {
                dir--;
                dfs(dir, r + dr[dir], c + dc[dir]);
            }
        } else {
            if(dir == 1 || dir == 3) {
                dir = (dir + 1) % 4;
                dfs(dir, r + dr[dir], c + dc[dir]);
            } else {
                dir = (dir + 3) % 4;
                dfs(dir, r + dr[dir], c + dc[dir]);
            }
        }
    }
}
