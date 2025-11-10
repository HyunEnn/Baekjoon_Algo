
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long ans = Long.MAX_VALUE;
        int ans_left = 0, ans_right = 0;
        int left = 0, right = N - 1;
        while (left < right) {
            if(Math.abs(arr[left] + arr[right]) <= ans) {
                ans = Math.abs(arr[left] + arr[right]);
                ans_left = left;
                ans_right = right;
            }
            if(arr[left] + arr[right] > 0) right--;
            else left++;
        }

        System.out.println(arr[ans_left] + " " + arr[ans_right]);
//        System.out.println(ans);
    }
}
