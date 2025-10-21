
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
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        for(int i : arr) {
            right = Math.max(right, i);
        }

        while(left < right) {
            int mid = (left + right) / 2;
            if(solve(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
        /**
         * 1. 탐색 범위 설정
         * 2. 파라메트릭 이분탐색
         */
    }

    public static boolean solve(int mid) {
        int cnt = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if(max - min > mid) {
                cnt++;
                max = Integer.MIN_VALUE; min = Integer.MAX_VALUE;
                i--;
            }
        }
        if(cnt > M) return false;
        else return true;
    }
}
