
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    static int answer = 0;
    static HashSet<String> set = new HashSet<>();
    static boolean[] v;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0);

        System.out.println(answer);
    }

    public static void dfs(int idx, int sum, int cnt) {
        if(idx == N) {
            if(sum == S && cnt > 0)
                answer++;
            return;
        }

        dfs(idx + 1, sum + arr[idx], cnt + 1);
        dfs(idx + 1, sum, cnt);
    }
}
