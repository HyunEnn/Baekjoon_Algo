
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N, M;
    static int[] sel;
    static int[] arr;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb;
    static boolean[] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sel = new int[M];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder();
        Arrays.sort(arr);
        for(int i=0;i<N;i++) {
            v = new boolean[N];
            v[i] = true;
            sel[0] = arr[i];
            dfs(1);
        }
        System.out.println(sb.toString());
    }

    static void dfs(int k) {
        if(k == M) {
            StringBuilder tempSb = new StringBuilder();
            for(int i=0;i<sel.length;i++) {
                tempSb.append(sel[i] + " ");
            }
            String result = tempSb.toString();

            if(!set.contains(result)) {
                set.add(result);
                sb.append(result).append('\n');
            }
            return;
        }

        for(int i=0;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                sel[k] = arr[i];
                dfs(k + 1);
                v[i] = false;
            }
        }
    }
}
