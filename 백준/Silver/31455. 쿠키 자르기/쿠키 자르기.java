
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, N, total, fTotal;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            total = 0;
            fTotal = 0;
            for(int i=0;i<N;i++) {
                String line = br.readLine();
                for(int j=0;j<N;j++) {
                    map[i][j] = line.charAt(j) - '0';
                    fTotal += map[i][j];
                }
            }

            solve(0, 0, N-1, N-1);
            System.out.println(fTotal - total);
        }
    }

    static void solve(int startR, int startC, int endR, int endC) {
        int dir = check(startR, startC, endR, endC); // 먹을 위치
        int len = endR - startR + 1;
        int half = len / 2;

        // 1x1 구간이면 직접 처
        if (len == 2) {
            if (dir == 0) total += map[startR][startC];
            else if (dir == 1) total += map[startR][endC];
            else if (dir == 2) total += map[endR][startC];
            else total += map[endR][endC];
            return;
        }

        // 각 사분면 좌표 계산
        int rMid = startR + half - 1;
        int cMid = startC + half - 1;

        // 먹기
        if (dir == 0) { // 좌상
            for (int i = startR; i <= rMid; i++) {
                for (int j = startC; j <= cMid; j++) {
                    total += map[i][j];
                }
            }
        } else if (dir == 1) { // 우상
            for (int i = startR; i <= rMid; i++) {
                for (int j = cMid + 1; j <= endC; j++) {
                    total += map[i][j];
                }
            }
        } else if (dir == 2) { // 좌하
            for (int i = rMid + 1; i <= endR; i++) {
                for (int j = startC; j <= cMid; j++) {
                    total += map[i][j];
                }
            }
        } else { // 우하
            for (int i = rMid + 1; i <= endR; i++) {
                for (int j = cMid + 1; j <= endC; j++) {
                    total += map[i][j];
                }
            }
        }

        // 나머지 3사분면 재귀
        if (dir != 0) solve(startR, startC, rMid, cMid); // 좌상
        if (dir != 1) solve(startR, cMid + 1, rMid, endC); // 우상
        if (dir != 2) solve(rMid + 1, startC, endR, cMid); // 좌하
        if (dir != 3) solve(rMid + 1, cMid + 1, endR, endC); // 우하
    }

    static int check(int startR, int startC, int endR, int endC) {
        int sum = 0;
        for(int i=startR;i<=endR;i++) {
            for(int j=startC;j<=endC;j++) {
                sum += map[i][j];
            }
        }

        return sum % 4;
    }
}
