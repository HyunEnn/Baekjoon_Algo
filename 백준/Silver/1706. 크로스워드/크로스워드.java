
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static Set<String> set = new HashSet<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        findRow();
        findCol();

        List<String> list = new ArrayList<>(set);
        list.sort((a, b) -> a.compareTo(b));
        System.out.println(list.get(0));
    }

    static void findRow() {
        // 가로줄 탐색
        for (int i = 0; i < N; i++) {
            int ch = 0;
            String s = "";
            for (int j = 0; j <= M; j++) {
                // #을 만나면 지금까지 저장된 ch 가 2 이상이면, set 에 저장
                if (j == M || map[i][j] == '#') {
                    if (ch >= 2) set.add(s);
                    ch = 0;
                    s = ""; // 저장된 후에 s와 ch는 초기화
                } else {
                    ch++;
                    s += map[i][j];
                }
            }
        }
    }

    static void findCol() {
        // 세로줄 탐색
        for (int j = 0; j < M; j++) {
            int ch = 0;
            String s = "";
            for (int i = 0; i <= N; i++) {
                // #을 만나면 지금까지 저장된 ch 가 2 이상이면, set 에 저장
                if (i == N || map[i][j] == '#') {
                    if (ch >= 2) set.add(s);
                    ch = 0;
                    s = ""; // 저장된 후에 s와 ch는 초기화
                } else {
                    ch++;
                    s += map[i][j];
                }
            }
        }
    }
}
