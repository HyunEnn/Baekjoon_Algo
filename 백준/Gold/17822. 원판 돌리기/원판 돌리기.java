
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
    static int N, M, T;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static boolean flag;
    static boolean[][] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M]; // 배수 계산 간편화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 회전 시킬 원판( 배수 )
            int b = Integer.parseInt(st.nextToken()); // 회전 방향
            int c = Integer.parseInt(st.nextToken()); // 돌릴 값
            rotate(a, b, c); // 회전
            v = new boolean[N+1][M];
            flag = false;
            for(int y=1;y<=N;y++) {
                for(int z=0;z<M;z++) {
                    if(map[y][z] != 0 && !v[y][z]) remove(y, z);
                }
            }

            // 아무 것도 지워지지 않으면
            if(!flag) {
                int sum = 0;
                int cnt = 0;
                double avg = 0;
                for(int j=1;j<=N;j++) {
                    for(int k=0;k<M;k++) {
                        sum += map[j][k];
                        if(map[j][k] != 0) cnt++;
                    }
                }
                if(cnt == 0) continue;
                avg = (double) sum / cnt;
//                System.out.println("sum : " + sum + " , cnt : " + cnt + " , avg : " + avg);
                for(int j=1;j<=N;j++) {
                    for(int k=0;k<M;k++) {
                        if(map[j][k] > avg && map[j][k] != 0) map[j][k]--;
                        else if(map[j][k] < avg && map[j][k] != 0) map[j][k]++;
                    }
                }
            }
        }

        System.out.println(countMap());
    }

    public static int countMap() {
        int ans = 0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                ans += map[i][j];
            }
        }
        return ans;
    }

    public static void printMap() {
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void remove(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        v[r][c] = true;
        List<Point> list = new ArrayList<>();
        list.add(new Point(r, c));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위를 벗어나게 되는 경우, 반대 방향으로 이동하게 세팅
                // 행은 1~N, 열은 0~M-1 인 점을 고려
                // 열은 처음과 끝으로 이동이 필요하지만, 행은 이동 고려할 필요가 없다.
                if(nr <= 0 || nr > N) continue;
                if(nc == -1) nc = M-1;
                else if(nc == M) nc = 0;

                // 이동한 자리가 기존 값과 같은 list 에 넣어준다.
                if(!v[nr][nc] && (map[p.r][p.c] == map[nr][nc])) {
                    v[nr][nc] = true;
                    list.add(new Point(nr, nc));
                    Q.offer(new Point(nr, nc));
                }
            }
        }

        // 리스트가 2 이상이면, 리스트에 있는 값들을 전부 0 처리 해준다.
        if(list.size() >= 2) {
            flag = true;
            for(Point op : list) {
                map[op.r][op.c] = 0;
            }
        }
    }

    public static void rotate(int a, int b, int c) {
        // 배수만큼 회전 시킴
        c %= M;
        for (int i = a; i <= N; i += a) {
            // 나누어 떨어지면 제자리임
            moveArr(i, b, c);
        }
    }

    public static void moveArr(int a, int b, int k) {
        // 시계 방향
        if (b == 0) {
            for (int i = 0; i < k; i++) {
                int tmp = map[a][M-1];
                for(int j=M-1;j>=1;j--) {
                    map[a][j] = map[a][j-1];
                }
                map[a][0] = tmp;
            }
        } 
        // 반시계 방향
        else {
            for(int i=0;i<k;i++) {
                int tmp = map[a][0];
                for(int j=0;j<M-1;j++) {
                    map[a][j] = map[a][j+1];
                }
                map[a][M-1] = tmp;
            }
        }
    }
    // 각 자리는 좌우를 검사해야하는데, 1이거나 M인 경우 첫자리 혹은 끝자리 확인
    // 원판 기준으로 양옆 검사, 1이거나 N이면 내 바로 옆에 존재하는 값만 확인
    // 배열 돌리기는 % M 으로 돌리는 횟수 최소화

    // 1. 배열 회전 ok
    // 2. 원판에서 인접하면서 수가 같은 것들 제거 ok
    // 3. 그러한 경우가 없을 경우, 적힌 수들의 평균을 구하고, 평균보다 큰 수는 1을 빼고, 작은 수에는 1을 더한다.
}

