
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC;
    static int ans = 0;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        startR = N / 2; startC = N / 2;
        // 왼쪽
        int dir = 3;
        int nr = startR + dr[dir];
        int nc = startC + dc[dir];
        int curr = map[nr][nc]; // 흩뿌려질 모래
        int ur = nr + dr[dir]; int uc = nc + dc[dir]; // 여기가 a 자리
        spread(nr, nc, ur, uc, dir, curr);
//        printMap();
        // 아래
        dir = 2;
        nr = startR + dr[dir];
        nc = startC + dc[dir];
        curr = map[nr][nc];
        ur = nr + dr[dir]; uc = nc + dc[dir];
        spread(nr, nc, ur, uc, dir, curr);
//        printMap();
        int ch = 2;
        dir = 1;
        while (startR != 0 || startC != 0) {
            boolean flag = false;
            for (int d = 0; d < 2; d++) {
                int nDir = (dir - d + 4) % 4;
                for (int rt = 0; rt < ch; rt++) {
                    nr = startR + dr[nDir];
                    nc = startC + dc[nDir];
                    curr = map[nr][nc];
                    ur = nr + dr[nDir];
                    uc = nc + dc[nDir];
                    spread(nr, nc, ur, uc, nDir, curr);
                    if (nr == 0 && nc == 0) {
                        flag = true;
                        break;
                    }
//                    System.out.println(startR + " " + startC + " " + ch);
                }
                if(flag) break;
                if (d == 1) dir = (nDir - 1 + 4) % 4;
            }
            if (flag) break;
            ch++;
        }

        System.out.println(ans);
    }

    public static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void spread(int nr, int nc, int ur, int uc, int dir, int curr) {
        int remain = curr;
        // 1. 5% 자리
        int fr = ur + dr[dir];
        int fc = uc + dc[dir];
        int first = curr * 5 / 100;
        if(inRange(fr, fc)) map[fr][fc] += first;
        else ans += first; // 범위 밖이면 격자 밖으로 소멸된 모래
        remain -= first;
        
        // 2. 10% 자리
        int sr1 = ur + dr[(dir + 1) % 4];
        int sc1 = uc + dc[(dir + 1) % 4];
        int sr2 = ur + dr[(dir - 1 + 4) % 4];
        int sc2 = uc + dc[(dir - 1 + 4) % 4];
        int second = curr * 10 / 100;
        if(inRange(sr1, sc1)) map[sr1][sc1] += second;
        else ans += second;
        remain -= second;

        if(inRange(sr2, sc2)) map[sr2][sc2] += second;
        else ans += second;
        remain -= second;

        // 3. 7% 자리
        int tr1 = nr + dr[(dir + 1) % 4];
        int tc1 = nc + dc[(dir + 1) % 4];
        int tr2 = nr + dr[(dir - 1 + 4) % 4];
        int tc2 = nc + dc[(dir - 1 + 4) % 4];
        int third = curr * 7 / 100 ;
        if(inRange(tr1, tc1)) map[tr1][tc1] += third;
        else ans += third;
        remain -= third;

        if(inRange(tr2, tc2)) map[tr2][tc2] += third;
        else ans += third;
        remain -= third;
        
        // 4. 2% 자리
        int ffr1 = nr + (2 * dr[(dir + 1) % 4]);
        int ffc1 = nc + (2 * dc[(dir + 1) % 4]);
        int ffr2 = nr + (2 * dr[(dir - 1 + 4) % 4]);
        int ffc2 = nc + (2 * dc[(dir - 1 + 4) % 4]);
        int fourth = curr * 2 / 100;
        if(inRange(ffr1, ffc1)) map[ffr1][ffc1] += fourth;
        else ans += fourth;
        remain -= fourth;

        if(inRange(ffr2, ffc2)) map[ffr2][ffc2] += fourth;
        else ans += fourth;
        remain -= fourth;
        
        // 5. 1% 자리
        int lastR1 = startR + dr[(dir + 1) % 4];
        int lastC1 = startC + dc[(dir + 1) % 4];
        int lastR2 = startR + dr[(dir - 1 + 4) % 4];
        int lastC2 = startC + dc[(dir - 1 + 4) % 4];
        int fifth = curr / 100;
        if(inRange(lastR1, lastC1)) map[lastR1][lastC1] += fifth;
        else ans += fifth;
        remain -= fifth;
        if(inRange(lastR2, lastC2)) map[lastR2][lastC2] += fifth;
        else ans += fifth;
        remain -= fifth;

        if(inRange(ur, uc)) map[ur][uc] += remain;
        else ans += remain;
        map[nr][nc] = 0;
        startR = nr; startC = nc;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
