
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int res = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                res = Math.max(res, find(i, j));
            }
        }

        System.out.println(res);
    }

    private static int find(int r, int c) {
        // 행 기준 탐색
        int cnt = 0;
        int ans = 1;
        for(int j=c+1;j<M;j++) {
            // 같은 값이 있다면, 현재 그 거리를 저장하고 밑에 값들과 비교
            if(map[r][c] == map[r][j]) {
                cnt = j - c;
                // 아래, 대각선까지 거리가 모두 같으면서 같은 값을 가지고 있다면 정사각형
                if(r + cnt < N && c + cnt < M) {
                    if(map[r][c] == map[r+cnt][c] && map[r][c] == map[r+cnt][c+cnt]) {
                        ans = (cnt+1) * (cnt+1);
                    }
                }
            }
        }
        return ans;
    }
}
