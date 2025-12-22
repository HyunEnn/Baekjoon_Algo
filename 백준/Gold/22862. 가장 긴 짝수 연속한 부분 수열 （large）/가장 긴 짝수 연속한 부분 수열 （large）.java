
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
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int cnt = 0, ans = 0, ch = 0;
        while(right < N) {
            // 홀수면, cnt 증가하는데, cnt 증가가 불가능한 경우 계산
            if(arr[right] % 2 != 0) {
                if(cnt == M) {
                    while(cnt == M) {
                        if(arr[left] % 2 != 0) cnt--;
                        else ch--;
                        left++;
                    }
                }
                cnt++;
            }
            // 짝수면, 그냥 갯수 증가하고 최대값 계산
            else {
                ch++;
                ans = Math.max(ans, ch);
            }
//            System.out.println(right + " , " + cnt + " , " + ans);
            right++;
        }

        System.out.println(ans);
    }
    /**
     * left, right 설정해두고 만약에 right 가 홀수라면, cnt++
     * 짝수라면 ans++ 을 진행한다. 만약에, right 가 홀수인데 cnt 가 이미 M 이라면
     * left 를 한 칸씩 움직인다.
     */
}
