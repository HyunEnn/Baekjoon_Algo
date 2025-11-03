
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, prefix, postfix;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        prefix = new int[N];
        postfix = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합
        prefix[0] = arr[0];
        for(int i=1;i<N;i++) {
            prefix[i] = arr[i] + prefix[i-1];
        }

        postfix[N-1] = arr[N-1];
        for(int i=N-2;i>=0;i--) {
            postfix[i] = arr[i] + postfix[i+1];
        }

        /**
         * 1. 벌 -- 벌 -- 꿀
         * 2. 꿀 -- 벌 -- 벌
         * 3. 벌 -- 꿀 -- 벌
         */

        int ans = 0;
        int fBee = prefix[N-1] - prefix[0];
        for(int i=1;i<N;i++) {
            ans = Math.max(ans, fBee - arr[i] + (prefix[N-1] - prefix[i]));
        }

        int sBee = postfix[0] - arr[N-1];
        for(int i=1;i<N-1;i++) {
            ans = Math.max(ans, sBee - arr[i] + (postfix[0] - postfix[i]));
        }

        for(int i=1;i<N;i++) {
            ans = Math.max(ans, prefix[i] - arr[0] + postfix[i] - arr[N-1]);
        }


        System.out.println(ans);
    }
}
