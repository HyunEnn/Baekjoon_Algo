
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] pos;
    static int res;
    static boolean[] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int t=0;t<N;t++) {
            res = Integer.MIN_VALUE;
            // 선수 등록
            pos = new int[11][11];
            v = new boolean[11];
            for(int i=0;i<11;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<11;j++) {
                    pos[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            dfs(0, 0);
            System.out.println(res);
        }
    }

    public static void dfs(int idx, int sum) {
        if(idx == 11) {
            res = Math.max(res, sum);
            return;
        }

        for(int i=0;i<11;i++) {
            if(!v[i] && pos[idx][i] != 0) {
                v[i] = true;
                dfs(idx + 1, sum + pos[idx][i]);
                v[i] = false;
            }
        }
    }
}
