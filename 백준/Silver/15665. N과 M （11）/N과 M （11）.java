
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] sel;
    static StringBuilder sb;
    static Set<String> set = new HashSet<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sel = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sb = new StringBuilder();
        dfs(0);
        System.out.println(sb.toString());
    }

    static void dfs(int idx) {
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

        for(int i=0;i<N;i++) {
            sel[idx] = arr[i];
            dfs(idx + 1);
        }
    }
}
