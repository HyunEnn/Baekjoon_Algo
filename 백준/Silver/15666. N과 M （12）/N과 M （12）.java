
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static Set<String> set = new HashSet<>();
    static int[] sel;
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sel = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sb = new StringBuilder();
        dfs(0, 0);
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int curr) {
        if(idx == M) {
            StringBuilder tempSb = new StringBuilder();
            for(int i=0;i<sel.length;i++) {
                tempSb.append(sel[i]).append(" ");
            }
            String result = tempSb.toString();

            if(!set.contains(result)) {
                set.add(result);
                sb.append(result).append("\n");
            }
            return;
        }

        for(int i=curr;i<N;i++) {
            sel[idx] = arr[i];
            dfs(idx + 1, i);
        }
    }
}
