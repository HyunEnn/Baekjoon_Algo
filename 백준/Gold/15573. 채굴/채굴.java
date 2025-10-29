
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point> {
        int r, c, curr;
        Point(int r, int c, int curr) {
            this.r = r;
            this.c = c;
            this.curr = curr;
        }

        @Override
        public int compareTo(Point p) {
            return this.curr - p.curr;
        }
    }
    static int N, M, K;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {0, -1, 0, 1}; // 우, 상, 좌, 하
    static int[] dc = {1, 0, -1, 0};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Point> PQ = new PriorityQueue<>();

        // 바닥이랑 맞닿아 있지 않은 경우
        for(int i=0;i<N-1;i++) {
            for(int j=0;j<M;j++) {
                // 주변 4개중 1칸은 공기와 맞닿아 있고, 아직 PQ에 넣지 않은 광산을 넣음
                if(map[i][j] != 0 && !v[i][j]) {
                    boolean flag = false;
                    for(int k=0;k<4;k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        // 범위 밖이랑 연결되어 있다면
                        if(!inRange(nr, nc)) {
                            flag = true;
                            break;
                        }
                        // 연결되어 있지 않은데, 탐색하는 4방 중 하나라도 0이라면
                        else {
                            if(map[nr][nc] == 0) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    // flag 처리로 삽입된게 확인되면 PQ 에 삽입
                    if(flag) {
                        v[i][j] = true;
                        PQ.offer(new Point(i,j, map[i][j]));
                    }
                }
            }
        }
        // 바닥이랑 맞닿아 있는 경우
        for(int j=0;j<M;j++) {
            boolean flag = false;
            for(int k=0;k<3;k++) {
                int nr = N-1 + dr[k];
                int nc = j + dc[k];
                if(!inRange(nr, nc)) {
                    flag = true;
                    break;
                }
                else {
                    if(map[nr][nc] == 0) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) {
                v[N-1][j] = true;
                PQ.offer(new Point(N-1, j, map[N-1][j]));
            }
        }

        // 여기서부터, PQ 를 탐색
        int ans = 0;
        int cnt = 0;
        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            cnt++;
            ans = Math.max(ans, p.curr);
            map[p.r][p.c] = 0;
            if(cnt == K) {
                System.out.println(ans);
                break;
            }

            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위 안에 있어야하고, 공기가 아니면서 아직 방문하지 않은 광산
                if(inRange(nr, nc) && !v[nr][nc] && map[nr][nc] != 0) {
                    v[nr][nc] = true;
                    PQ.offer(new Point(nr, nc, map[nr][nc]));
                }
            }
        }
        /**
         * 1. 공기와 맞닿아 있는 블럭들 PQ 에 저장하고 방문배열 체크
         * 2. PQ 에서 앞부분을 뽑으면서, 현재 자리는 빈 자리로 바꾸고, PQ에 가능한 자리 저장
         * 3. 이 행위를 p.cnt == K가 될때까지 반복 실행
         */
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
