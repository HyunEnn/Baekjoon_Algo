
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] kits;
    static boolean[] v;
    static int max = 0;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        kits = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        recursive(0, 500);
        System.out.println(max);
    }

    public static void recursive(int idx, int weight) {
        if(idx == N ) {
            max++;
            return;
        }

        if(weight < 500)
            return;

        for(int i=0;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                recursive(idx + 1, weight + kits[i] - K);
                v[i] = false;
            }
        }
    }
}
