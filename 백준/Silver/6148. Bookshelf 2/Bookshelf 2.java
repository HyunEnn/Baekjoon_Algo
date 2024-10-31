
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, B;
    static int[] heights;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        heights = new int[N];
        for(int i=0;i<N;i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        dfs(0, 0);
        System.out.println(min - B);
    }

    public static void dfs(int idx, int cnt) {
        if(cnt >= B) {
            min = Math.min(min, cnt);
            return;
        }

        for(int i=idx;i<N;i++) {
            dfs(i+1, cnt + heights[i]);
        }
    }
}
