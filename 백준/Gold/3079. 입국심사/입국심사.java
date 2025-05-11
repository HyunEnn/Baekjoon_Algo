
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] cal;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 7, 10이 주어지면 어떻게 이분 탐색을 진행해야하지?
        // 1. 탐색할 최대의 시간은 가장 오래 걸리는 심사관 * 친구 숫자
        // 2. 이분 탐색을 진행하는데, 정해진 시간을 기준으로 모든 심사관들이 가능한 친구들의 수를 합한다.
        // 2-1. 합한 인원들의 숫자가 주어진 N보다 크면 가능한 시간대 이므로, right = mid - 1로 설정
        // 3. 그렇게 이분탐색으로 주어질 수 있는 시간 중에 가장 최솟값을 고른다.
        cal = new int[N];
        long left = 1, right = 1;
        for(int i=0;i<N;i++) {
            cal[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, (long) cal[i] * K);
        }
        long answer = right;

        while(left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for(int i=0;i<N;i++) {
                sum += mid / cal[i];
                if(sum > K) break;
            }
            // 여기서 sum 이 K보다 크면, 시간을 줄인다.
            if(sum >= K) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else if (sum < K){
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
