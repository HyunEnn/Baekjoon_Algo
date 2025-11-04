
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int ans = 0;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int idx) {
        // basis
        if(idx == N * M) {
            ans = Math.max(ans, calc());
            return;
        }

        // inductive
        int r = idx / M;
        int c = idx % M;

        // 가로
        v[r][c] = false;
        dfs(idx + 1);

        // 세로
        v[r][c] = true;
        dfs(idx + 1);
    }

    public static int calc() {
        int sum = 0;
        // 가로 계산
        for(int i=0;i<N;i++) {
            int curr = 0;
            for(int j=0;j<M;j++) {
                if(!v[i][j]) curr = curr * 10 + map[i][j];
                else { sum += curr; curr = 0; }
            }
            sum += curr;
        }

        // 세로 계산
        for(int j=0;j<M;j++) {
            int curr = 0;
            for(int i=0;i<N;i++) {
                if(v[i][j]) curr = curr * 10 + map[i][j];
                else { sum += curr; curr = 0; }
            }
            sum += curr;
        }

        return sum;
    }
}
