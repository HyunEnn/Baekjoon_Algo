
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        Arrays.sort(arr);

        int left = 0, right = N-1, ans = Integer.MAX_VALUE;
        int al = 0, san = 0;
        while(left < right) {

            int sum = arr[left] + arr[right];
            int curr = Math.abs(sum);
            if(curr < ans) {
                ans = curr;
                al = arr[left];
                san = arr[right];
                if(curr == 0) break;
            }

            if(sum > 0) right--;
            else left++;
        }

        System.out.println(al + " " + san);
    }
}
