
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        for(int i=N-1;i>=0;i--) {
            if(arr[i] <= K) {
                ans += K / arr[i];
                K %= arr[i];
            }
        }

        System.out.println(ans);
    }
}
