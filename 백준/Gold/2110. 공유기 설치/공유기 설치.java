
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int left = 1, right = arr[N-1] - arr[0];
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(canDivide(mid)) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            } else right = mid - 1;
        }

        System.out.println(ans);
    }

    private static boolean canDivide(int idx) {
        // 정해진 idx 기준으로 cnt 가 C개 이상을 세울 수 있다면 true
        int cnt = 0, curr = 0;
        for(int i=0;i<N;i++) {
            if(curr == 0) {
                curr = arr[i];
                cnt++;
                continue;
            }

            if(arr[i] - curr >= idx) {
                curr = arr[i];
                cnt++;
            }
        }

        if(cnt >= C) return true;
        else return false;
    }
}
