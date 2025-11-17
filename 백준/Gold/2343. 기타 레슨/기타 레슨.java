
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
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        for(int i=0;i<N;i++) {
            left = Math.max(left, arr[i]);
            right += arr[i];
        }
        
        int ans = Integer.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isSolved(mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else left = mid + 1;
        }

        System.out.println(ans);
    }

    private static boolean isSolved(int mid) {
//        System.out.println("mid : " + mid);
        int curr = 0;
        int cnt = 0;
        for(int i=0;i<N-1;i++) {
            if(curr + arr[i] <= mid) {
                curr += arr[i];
            } else {
                cnt++;
//                System.out.println("진행된 인덱스 : " + i + " , 현재 cnt : " + cnt + " , curr : " + curr);
                curr = arr[i];
            }
        }

        if(curr + arr[N-1] <= mid) cnt++;
        else cnt += 2;

        return cnt <= M;
    }
}
