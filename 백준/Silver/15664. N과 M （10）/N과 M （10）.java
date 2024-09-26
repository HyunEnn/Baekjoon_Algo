
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] sel;
    static boolean[] v;
    static Set<String> set = new HashSet<>();
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
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sb = new StringBuilder();
        Arrays.sort(arr);
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
            if(!v[i]) {
                v[i] = true;
                sel[idx] = arr[i];
                dfs(idx + 1, i);
                v[i] = false;
            }
        }
    }
}
