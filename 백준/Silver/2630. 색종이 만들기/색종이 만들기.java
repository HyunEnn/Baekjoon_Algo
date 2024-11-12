import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int blue, white;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        blue = 0;
        white = 0;

        // 1. 분할 정복을 진행함 ( (0,0) ~ (8,8)
        // 2. 반갈죽을 내니까 /2 를 진행해서 반갈죽된 구역이 모두 0,1 이면 +1 아니면 다시 반갈죽
        // 3. 시작과 끝이 같아지면 알아서 종료?
        solve(0, 0, N - 1, N - 1);
        StringBuilder sb = new StringBuilder();
        sb.append(white).append("\n").append(blue);
        System.out.println(sb.toString());
    }

    public static void solve(int startR, int startC, int endR, int endC) {

        int checkColor = check(startR, startC, endR, endC);
        if (checkColor == 0) white++;
        else if (checkColor == 1) blue++;
        else {
            // 값이 안나오면 한 번 더 분할
            // 4사분면 형태로 분할한다
            int midR = startR + ((endR - startR) / 2); // 3
            int midC = startC + ((endC - startC) / 2); // 3
            solve(startR, startC, midR, midC); // (0,0) ~ (3,3)
            solve(startR, midC + 1, midR, endC); // (0,4) ~ (3,7)
            solve(midR + 1, startC, endR, midC); // (4,0) ~ (7,3)
            solve(midR + 1, midC + 1, endR , endC);// (4,4) ~ (7,7)

        }
    }

    public static int check(int startR, int startC, int endR, int endC) {
        int ch = map[startR][startC];
        // 기존 값과 나눌 수 없을 때
        for (int i = startR; i <= endR; i++) {
            for (int j = startC; j <= endC; j++) {
                if (map[i][j] != ch) {
                    return -1;
                }
            }
        }

        // 동일 값 처리 -> white = 0, blue = 1
        return map[startR][startC];
    }
}
