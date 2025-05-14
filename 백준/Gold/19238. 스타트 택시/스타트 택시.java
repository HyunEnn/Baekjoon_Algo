
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int r, c, cnt;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point p) {
            if (this.cnt == p.cnt) {
                if (this.r == p.r)
                    return this.c - p.c;
                return this.r - p.r;
            }
            return this.cnt - p.cnt;
        }
    }


    static int N, M, F;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static HashMap<Integer, Point> hashMap = new HashMap<>();
    static int[][] map;
    static int startR, startC;
    static PriorityQueue<Point> PQ;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        int idx = 2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = idx;
            hashMap.put(idx, new Point(sr, sc)); // 도착지 설정
            idx++;
        }
        int size = M;

        // 1은 벽이니까, 2부터 시작하자.
        // 0. 손님의 위치를 idx 로 설정해서 저장하고, 위치는 hashMap 에 저장하자.
        // 1. 현재의 시작점에서 가장 가까운 손님을 찾는다. -> O
        // 2. 그러면, 현재 연료에서 p.cnt 만큼 빼준다. 만약, 연료가 가는 도중 다 닳으면 -1로 종료, 그리고 start 를 수정 -> O
        // 3. 수정된 start 위치에서 bfs 탐색을 통해 ( 현재 위치 * -1 ) 을 탐색한다. -> O
        // 3-1. 이동한 연료만큼 사용하는데, 만약에 이동거리가 연료보다 크면 -1로 종료, 아니라면 값을 빼주고 거리만큼 *2 해서 더해준다. -> O
        // 4. 손님 수 만큼 계속 탐색하는데, 손님을 모두 태웠으면 종료
        while (size > 0) {
            PQ = new PriorityQueue<>();
            findPerson(0, 0);

            Point p = null;
            if (PQ.isEmpty()) {
                System.out.println(-1);
                return;
            }
            p = PQ.poll();
            if (F - p.cnt < 0) {
                System.out.println(-1);
                return;
            }

            F -= p.cnt;
            startR = p.r;
            startC = p.c;
            int check = map[startR][startC];
            map[startR][startC] = 0;
//            System.out.println("찾기 전 : ");
//            System.out.println("연료 : " + F + " , 시작 위치 : " + startR + " , " + startC);
//            for(int i=0;i<N;i++) {
//                for(int j=0;j<N;j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
            PQ = new PriorityQueue<>();
            findPerson(1, check);
            if(PQ.isEmpty()) {
                System.out.println(-1);
                return;
            }
            Point ap = PQ.poll();
//            System.out.println("도착한 곳의 값 : " + ap.r + " " + ap.c + " " + ap.cnt);
//            System.out.println("현재 연로 : " + F);
//            System.out.println("사용하는 연료 : " + ap.cnt);
            if (F - ap.cnt < 0) {
                System.out.println(-1);
                return;
            }
            F -= ap.cnt;
            F += ap.cnt * 2;
            // 도착지에 도착하고 정보 재갱신
            startR = ap.r;
            startC = ap.c;
            hashMap.remove(check);

//            System.out.println("복구된 연료 : " + F);

//            System.out.println("탐색 후 : ");
//            for(int i=0;i<N;i++) {
//                for(int j=0;j<N;j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
            size--;
        }

        System.out.println(F);
    }


    public static void findPerson(int ch, int st) {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        Q.offer(new Point(startR, startC, 0));
        v[startR][startC] = true;

        while (!Q.isEmpty()) {
            Point p = Q.poll();
            // 0이면, 현재 자리가 사람이 서있는 자리면 PQ 에 대입
            if (ch == 0 && map[p.r][p.c] >= 2) {
                PQ.offer(new Point(p.r, p.c, p.cnt));
            }
                // 1이면, 도착지 찾기
            if (ch == 1) {
                Point s = hashMap.get(st);
                if(s != null && s.r == p.r && s.c == p.c)
                    PQ.offer(new Point(p.r, p.c, p.cnt));
            }
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if (inRange(nr, nc) && map[nr][nc] != 1 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc, p.cnt + 1));
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
