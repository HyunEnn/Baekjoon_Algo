
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] map;
    static boolean[][] v;
    static int cnt;
    static String[][] answer;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        answer = new String[N][M];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int game = 0;
        while (game < R) {
            // 공격수
            st = new StringTokenizer(br.readLine());
            int ar = Integer.parseInt(st.nextToken()) - 1;
            int ac = Integer.parseInt(st.nextToken()) - 1;
            String dir = st.nextToken();
            attack(ar, ac, dir);
            // 수비수
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            defense(sr, sc);
            game++;
        }

        System.out.println(cnt);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(v[i][j]) {
                    answer[i][j] = "F";
                }
                else {
                    answer[i][j] = "S";
                }
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void attack(int r, int c, String dir) {
//        System.out.println(dir);
        // 공격에 성공하는 경우만 제시
        if (!v[r][c]) {
            int move = map[r][c];
            switch (dir) {
                case "N":
                    while (move > 0) {
                        // 범위를 벗어나면 그냥 종료
                        if (!inRange(r, c)) break;
                        // 방문 하지 않은 곳
                        if (!v[r][c]) {
                            v[r][c] = true;
                            move = Math.max(move - 1, map[r][c] - 1);
                            r--;
                            cnt++;
                        } else { // 방문한 곳은 그냥 지나간다
                            r--;
                            move--;
                        }
                    }
                    break;
                case "E":
                    while (move > 0) {
                        if (!inRange(r, c)) break;
                        // 범위 안에 있으면서 방문 하지 않은 곳
                        if (!v[r][c]) {
                            v[r][c] = true;
                            move = Math.max(move - 1, map[r][c] - 1);
                            c++;
                            cnt++;
                        } else {
                            c++;
                            move--;
                        }
                    }
                    break;
                case "S":
                    while (move > 0) {
                        if (!inRange(r, c)) break;
                        // 범위 안에 있으면서 방문 하지 않은 곳
                        if (!v[r][c]) {
                            v[r][c] = true;
                            move = Math.max(move - 1, map[r][c] - 1);
                            r++;
                            cnt++;
                        } else {
                            r++;
                            move--;
                        }
                    }
                    break;
                case "W":
                    while (move > 0) {
                        if (!inRange(r, c)) break;
                        // 범위 안에 있으면서 방문 하지 않은 곳
                        if (!v[r][c]) {
                            v[r][c] = true;
                            move = Math.max(move - 1, map[r][c] - 1);
                            c--;
                            cnt++;
                        } else {
                            c--;
                            move--;
                        }
                    }
                    break;
            }
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void defense(int r, int c) {
        // 선택한 위치가 true 이면, 다시 false 로 되돌린다
        if (v[r][c])
            v[r][c] = false;
    }
}
