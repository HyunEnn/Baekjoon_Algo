
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        Arrays.fill(arr, -1);
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // union 을 진행하고, true 이고 order 가 1이라면 sb에 추가
            if(order == 0) {
                uni(u, v);
            }
            else {
                if(check(u, v)) sb.append("NO").append('\n');
                else sb.append("YES").append('\n');
            }
        }

        System.out.println(sb.toString());
    }

    public static int find(int x) {
        if(arr[x] < 0) return x;
        return arr[x] = find(arr[x]); // 최적화
    }

    public static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false; // 둘이 같은 부모니까 못합침
        arr[v] = u;
        return true;
    }

    public static boolean check(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        return true;
    }
}
