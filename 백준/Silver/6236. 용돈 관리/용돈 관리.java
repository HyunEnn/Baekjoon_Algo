
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
        int left = 0, right = 0;
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }
        int answer = right;
        // 금액은 최대 10,000원
        while(left <= right) {
            int cnt = 1;
            int mid = (left + right) / 2;
            int sum = 0;
            for(int i=0;i<N;i++) {
                // arr[i] 가 mid 보다 크면 넣었다가 다시 뺀다.
                // 여기서 넣었다가 다시 빼도, 불가능한 경우는 아예 종료?
                if(sum + arr[i] > mid) {
                    cnt++;
                    sum = arr[i];
                }
                // 아니라면, mid 에서 arr[i]를 빼준다.
                else {
                    sum += arr[i];
                }
            }

            if(cnt <= M) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
