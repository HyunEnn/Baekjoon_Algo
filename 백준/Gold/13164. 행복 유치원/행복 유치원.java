
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr, b;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        b = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N-1;i++) {
            b[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(b);

        long ans = 0;
        for(int i=0;i<b.length-(K-1);i++) {
            ans += b[i];
        }

        System.out.println(ans);

    }
    /**
     * 5 3
     * 1 3 5 6 10
     */
}
