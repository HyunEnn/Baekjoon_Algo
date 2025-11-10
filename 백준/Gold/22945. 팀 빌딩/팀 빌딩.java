
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
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0, left = 0, right = N-1;
        while(left < right) {
            ans = Math.max(ans, (right - left - 1) * Math.min(arr[left], arr[right]));
            if(arr[left] > arr[right]) {
                right--;
            } else {
                left++;
            }

        }

        System.out.println(ans);

    }
}
