
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int ch = 0;
        int res = 0;
        int ans = 0;
        // 오른쪽으로 진행하면서 탐색
        while(right < arr.length) {
            // 삭제가 가능한 경우
            if(ch <= K) {
                if(arr[right] % 2 != 0) ch++;
                else {
                    ans++;
                    res = Math.max(res, ans);
                }
                right++;
            } 
            // 삭제가 불가능한 경우
            else {
                if(arr[left] % 2 != 0) ch--;
                else ans--;
                left++;
            }
        }

        System.out.println(res);
    }
}
