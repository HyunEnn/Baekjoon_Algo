import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] arr;
    static int N, C;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long left = 1;
        long answer = 0;
        long right = arr[N-1] - arr[0];
        long mid = (left + right) / 2;

        while(left <= right) {

            if(check(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }

        System.out.println(answer);
    }

    public static boolean check(long range) {
        long cnt = 1, start = arr[0];

        for(int i=1;i<N;i++) {
            if(arr[i] >= start + range) {
                start = arr[i];
                cnt++;
            }
        }

        if(cnt < C)
            return false;
        return true;
    }
}
