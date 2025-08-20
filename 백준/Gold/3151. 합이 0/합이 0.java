
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
        long ans = 0;

        for(int i=0;i<N-2;i++) {
            int pin = arr[i];
            int left = i + 1;
            int right = N - 1;

            while(right > left) {
                int sum = arr[right] + arr[left] + pin;

                if(sum == 0) {
                    // left , right 값이 같다면 사이 값은 모두 같다.
                    if(arr[left] == arr[right]) {
                        long cnt = right - left + 1;
                        ans += cnt * (cnt - 1) / 2;
                        break;
                    }
                    // left, right 값이 다를때 각각 왼, 오른쪽으로 같은 값이 몇 개인지 계산한다.
                    else {
                        long c1 = 1, c2 = 1;
                        while(left + 1 < right && arr[left] == arr[left + 1]) {
                            c1++;
                            left++;
                        }

                        while(right - 1 > left && arr[right] == arr[right - 1]) {
                            c2++;
                            right--;
                        }
                        ans += c1 * c2;
                    }
                }

                if(sum > 0) right--;
                else left++;
            }
        }

        System.out.println(ans);
    }
    /**
     * 우선, 하나의 포인트를 정하고 나머진 투포인터로 접근하는 방식?
     */
}
