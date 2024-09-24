
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static boolean[] v;
    static int[] sel;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sel = new int[M];
        v = new boolean[N+1];
        sb = new StringBuilder();
        dfs(0, 1);
        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int curr) {
        if(idx == M) {
            for(int i=0;i<sel.length;i++) {
                sb.append(sel[i]).append(" ");
            } sb.append("\n");
            return;
        }

        for(int i=curr;i<=N;i++) {
            if(!v[i]) {
                v[i] = true;
                sel[idx] = i;
                dfs(idx+1, i);
                v[i] = false;
            }
        }
    }
}
