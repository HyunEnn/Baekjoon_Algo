
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int endR, endC;
    static Queue<Point> Q = new ArrayDeque<>();
    static Queue<Point> waters = new ArrayDeque<>();
    static char[][] map;
    static boolean[][] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        v = new boolean[R][C];
        for(int i=0;i<R;i++) {
            String line = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S') {
                    Q.offer(new Point(i, j, 0));
                    v[i][j] = true;
                    map[i][j] = '.';
                }
                else if(map[i][j] == 'D') {
                    endR = i;
                    endC = j;
                }
                else if(map[i][j] == '*') {
                    waters.offer(new Point(i, j));
                }
            }
        }

        int ans = solve();
        System.out.println(ans == -1 ? "KAKTUS" : ans);


    }
    
    static int solve() {
        // 고슴도치가 먼저 이동하고, 그다음 물이 차오르는 형태로 구현
        // 물이랑 고슴도치랑 한번씩 bfs 가 진행되야 한다?
        // 물이 먼저 bfs 돌고, 고슴도치가 돌아간다.
        boolean flag = false;
        while(!Q.isEmpty()) {
            // 현재 들어있는 사이즈 만큼만 순환
            int size = waters.size();
            for(int i=0;i<size;i++) {
                Point w = waters.poll();
                for(int k=0;k<4;k++) {
                    int nr = w.r + dr[k];
                    int nc = w.c + dc[k];
                    // 범위 안에 있고, 평지여야 물을 채움
                    if(inRange(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = '*';
                        waters.offer(new Point(nr, nc));
                    }
                }
            }

            int gSize = Q.size();
            for(int i=0;i<gSize;i++) {
                Point p = Q.poll();
                for(int k=0;k<4;k++) {
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];
                    // 범위 안에 있고, 바닥인 지역 이동을 하고, 만일 굴이 나오면 바로 종료
                    if(inRange(nr, nc) && !v[nr][nc]) {
                        if(map[nr][nc] == '.') {
                            v[nr][nc] = true;
                            Q.offer(new Point(nr, nc, p.cnt + 1));
                        }
                        if(map[nr][nc] == 'D') {
                            return p.cnt + 1;
                        }
                    }
                }
            }
        }

        // 만약에 고슴도치가 이동못하고 빠지면 KAKUTS 출력하고 바로 종료
        return -1;
        // 빠져나왔다면 그 값을 저장하고 종료
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
