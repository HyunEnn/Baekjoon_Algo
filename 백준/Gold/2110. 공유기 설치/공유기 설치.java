
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, C;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int left = 1, right = arr[N-1] - arr[0];
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(solve(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean solve(int mid) {
        int cnt = 0;
        int curr = 0;
        for(int i=0;i<N;i++) {
            if(curr == 0) {
                curr = arr[i];
                cnt++;
                continue;
            }
            
            // 범위 안에 있다면 공유기 설치
            if(arr[i] - curr >= mid) {
                curr = arr[i];
                cnt++;
            }
        }
        /**
         * 1 2 4 8 9
         * mid 가 최대일때가 아닌 최소일때를 구해야함
         * mid = 4,
         */

        if(cnt >= C) return true;
        else return false;
    }
}
