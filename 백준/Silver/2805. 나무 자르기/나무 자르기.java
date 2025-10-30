
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        long right = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        long left = 0;
        long ans = 0;
        while(left <= right) {
            long mid = (left + right) / 2;
            if(solve(mid)) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            }
            else right = mid - 1;
        }

        System.out.println(ans);
    }

    private static boolean solve(long mid) {
        long sum = 0;
        for(int i=0;i<N;i++) {
            if(arr[i] >= mid) {
                sum += arr[i] - mid;
            }
        }

        if(sum >= M) return true;
        else return false;
    }
}
