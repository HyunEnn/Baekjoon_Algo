
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] sel;
    static int[] arr;
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sel = new int[M];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb.toString());
    }

    static void dfs(int idx) {
        if(idx == M) {
            for(int i=0;i<sel.length;i++) {
                sb.append(sel[i]).append(" ");
            } sb.append("\n");
            return;
        }

        for(int i=0;i<N;i++) {
            sel[idx] = arr[i];
            dfs(idx + 1);
        }
    }
}
