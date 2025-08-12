import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, K, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            Arrays.fill(arr, -1);
            K = Integer.parseInt(br.readLine());
            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                uni(u, v); // 일단 친구관계 맺음
            }

            M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if(check(u, v)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }

            System.out.println("Scenario " + t + ":");
            System.out.println(sb.toString());
        }
    }

    public static int find(int x) {
        if(arr[x] < 0) return x;
        return arr[x] = find(arr[x]);
    }

    public static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        arr[u] = v;
        return true;
    }

    public static boolean check(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return true;
        return false;
    }
}

/**
 * 테케는 2개
 * 1번 테케
 * 유저 수는 3명
 * 고정된 친구 관계는 1개 -> (0, 1) 은 친구
 * 이제 친구 관게인지 확인할 쌍의 갯수 2개 -> (0,1) 과 (1,2)
 */
