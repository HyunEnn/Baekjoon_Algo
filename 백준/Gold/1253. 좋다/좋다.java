import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        // 0, 1은 선택될 일이 없으니 제외
        int answer = 0;
        for(int i=0;i<N;i++) {
            if(find(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    // 정렬하고, 현재 값을 left, right 를 처음부터 검사해야한다? N 이 2000개이므로, 시간 복잡도에서 터지진 않는다.
    public static boolean find(int x) {
        int left = 0, right = N-1;
        // 조건은, right 가 left 보다 클 때 (중복 x)
        // left, right 가 나아가야 하는 조건은? right 는 끝에서 시작하고, left 는 처음부터 시작
        // mid 가 arr[x] 보다 크면 right 를 줄이고, mid 가 arr[x] 보다 작다면 left 를 늘리는 식으로 접근
        while(left < right) {
            if(left == x) {
                left++;
                continue;
            }
            if(right == x) {
                right--;
                continue;
            }
            int mid = arr[left] + arr[right];
            // left + right 값이 x 가 된다면, 그 수는 Good 아므로 true
            if(mid == arr[x]) return true;
            else if(mid > arr[x]) right--;
            else left++;
        }

        return false;
    }
}
