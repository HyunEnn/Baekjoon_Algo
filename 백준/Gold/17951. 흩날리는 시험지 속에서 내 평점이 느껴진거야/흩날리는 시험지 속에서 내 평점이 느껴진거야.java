
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static StringTokenizer st;
    static int[] arr;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = Integer.MAX_VALUE, right = 0;
        for(int i=0;i<arr.length;i++) {
            right += arr[i];
            left = Math.min(left, arr[i]);
        }

        while(left < right) {
            int mid = (left + right + 1) / 2;
            if(solve(mid)) {
                left = mid;
            }
            else right = mid - 1;
        }

        System.out.println(left);
    }



    private static boolean solve(int target) {
        int sum = 0, cnt = 0;
        for(int i=0;i<arr.length;i++) {
            sum += arr[i];
            if(sum >= target) {
                cnt++;
                sum = 0;
            }
        }

        return cnt >= K;
    }
}
