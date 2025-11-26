
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int blueR, blueC, redR, redC, cnt;
        Point(int blueR, int blueC, int redR, int redC, int cnt) {
            this.blueR = blueR;
            this.blueC = blueC;
            this.redR = redR;
            this.redC = redC;
            this.cnt = cnt;
        }
    }
    static class MoveResult {
        int r, c, dist;
        boolean hole;
        MoveResult(int r, int c, int dist, boolean hole) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.hole = hole;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;
    static int bR, bC, rR, rC;
    static Queue<Point> Q = new ArrayDeque<>();
    static char[][] map;
    static boolean[][][][] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'R') { rR = i; rC = j;}
                else if(map[i][j] == 'B') { bR = i; bC =j; }
            }
        }
        v = new boolean[N][M][N][M];
        Q.offer(new Point(bR, bC, rR, rC, 0));
        v[rR][rC][bR][bC] = true;
        // 상하좌우로 10번 이하로 움직여서 빨간 공만 빼낼수 있는지에 대한 문제
        // 한번 방향을 정해서 기울이면, 끝까지 공을 기울인 지도가 나와야한다
        // 이동시킨 위치 값들을 저장해서, Q에 저장시키면서 값을 찾아야한다
        boolean flag = false;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            if(p.cnt == 10) {
                flag = true;
                break;
            }

            for(int k=0;k<4;k++) {
                MoveResult red = move(p.redR, p.redC, k);
                MoveResult blue = move(p.blueR, p.blueC, k);

                if(blue.hole) continue; // 파란공이 빠졌다면 패스
                if(red.hole) {
                    System.out.println(1);
                    return;
                } // 빨간공만 빠진경우라면, 바로 종료

                // 만약에, 빨간공과 파란공 위치가 동일 선상에 놓이게 된다면 이동거리가 적은것을 그 자리에 두고
                // 이동 한칸 전을 거리가 먼 공을 둔다
                if(blue.r == red.r && blue.c == red.c) {
                    if(blue.dist > red.dist) {
                        blue.r -= dr[k];
                        blue.c -= dc[k];
                    } else {
                        red.r -= dr[k];
                        red.c -= dc[k];
                    }
                }

                // 다 아니라면, 현재 자리를 Q에 추가해서 탐색 진행
                Q.offer(new Point(blue.r, blue.c, red.r, red.c, p.cnt + 1));
            }
        }

        System.out.println(0);
    }

    private static MoveResult move(int r, int c, int dir) {
        int dist = 0;
        while(true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if(map[nr][nc] == '#') break;
            r = nr;
            c = nc;
            dist++;

            if(map[nr][nc] == 'O') {
                return new MoveResult(r, c, dist, true);
            }
        }
        return new MoveResult(r, c, dist, false);
    }


}
