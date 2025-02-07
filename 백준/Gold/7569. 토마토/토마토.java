
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, d, day;
        Point(int r, int c, int d, int day) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.day = day;
        }
    }
    static int N, M, H;
    static boolean[][][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] dh = {-1, 1};
    static int[][][] map;
    static Queue<Point> Q = new ArrayDeque<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        v = new boolean[N][M][H];
        // 맨 아랫줄부터 윗 줄로 입력
        for(int t=0;t<H;t++) {
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++) {
                    map[i][j][t] = Integer.parseInt(st.nextToken());
                    // 현재 차원을 기반으로 토마토가 있으면 Q에 삽입
                    if(map[i][j][t] == 1) {
                        Q.offer(new Point(i, j, t, 0));
                        v[i][j][t] = true; // 현재 위치 저장
                    }
                }
            }
        }

        // 시작 전에 모든 토마토가 익었다면 0
        int ch = 0;
        for(int t=0;t<H;t++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j][t] == 1) ch++;
                }
            }
        }

        if(ch == N * M * H) System.out.println(0);
        else System.out.println(bfs());
    }

    public static int bfs() {
        // 들어있는 토마토들 동시 확산
        int cnt = 0;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 현재 차원에서 4방 탐색과 위 아래 차원의 현 위치
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 익지 않은 토마토가 있어야 전파 가능
                if(inRange(nr, nc, p.d) && !v[nr][nc][p.d] && map[nr][nc][p.d] == 0) {
                    v[nr][nc][p.d] = true;
                    Q.offer(new Point(nr, nc, p.d, p.day + 1));
                }
            }
            for(int k=0;k<2;k++) {
                int nd = p.d + dh[k];
                if(inRange(p.r, p.c, nd) && !v[p.r][p.c][nd] && map[p.r][p.c][nd] == 0) {
                    v[p.r][p.c][nd] = true;
                    Q.offer(new Point(p.r, p.c, nd, p.day + 1));
                }
            }

            cnt = p.day;
        }

        // 여기서 전파가 안된 부분이 있다면 -1
        boolean flag = false;
        for(int t=0;t<H;t++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(!v[i][j][t] && map[i][j][t] == 0) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        if(flag) return -1;
        return cnt;
    }

    public static boolean inRange(int r, int c, int d) {
        return r >= 0 && r < N && c >= 0 && c < M && d >= 0 && d < H;
    }
}
