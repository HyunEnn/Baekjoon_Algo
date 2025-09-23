
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    static long ans = Long.MAX_VALUE;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        
        // 0 부터 N-3까지가 첫번째 선택하는 용액, 2와 3번 용액은 투 포인터 접근으로 불필요한 탐색은 제거하고 진행
        long first = 0, second = 0, third = 0;
        for(int i=0;i<N-2;i++) {
            long f = arr[i];
            int left = i + 1, right = N - 1;
            while(left < right) {
                long sum = f + arr[left] + arr[right];
                long abs = Math.abs(sum);
                if(abs < ans) {
                    ans = abs;
                    first = f;
                    second = arr[left];
                    third = arr[right];
                    if(abs == 0) break;
                }

                // 여기서, sum 이 0보다 크면, right 를 내리고, 아니면 left 를 올림
                if(sum > 0) right--;
                else left++;
            }
        }
        System.out.println(first + " " + second + " " + third);
    }
}
