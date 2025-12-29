import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Long> first = new ArrayList<>();
    static List<Long> second = new ArrayList<>();
    static long[][] arr;
    static long[] ab, cd;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N][4];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            // 2^28
            arr[i][0] = Long.parseLong(st.nextToken());
            arr[i][1] = Long.parseLong(st.nextToken());
            arr[i][2] = Long.parseLong(st.nextToken());
            arr[i][3] = Long.parseLong(st.nextToken());
        }

        int M = N * N;
        ab = new long[M];
        cd = new long[M];

        int idx = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                ab[idx] = arr[i][0] + arr[j][1];
                cd[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        // 2. 두 개의 배열를 오름차순 정렬
        Arrays.sort(ab);
        Arrays.sort(cd);

        long ans = 0;
        // 3. 두 개의 리스트를 투포인터 혹은 이분탐색으로 총 0이 되는 갯수를 찾는다.
        // 이분탐색 : O(log N), 투 포인터 : O(N)
        for(int i=0;i<ab.length;i++) {
            long target = -ab[i];
            // 타겟을 지정해두고, 가장 먼저 나오는 위치와 끝나는 위치를 찾는다.
            int start = lowerBound(target);
            int end = upperBound(target);
            
            ans += end - start;
        }

        System.out.println(ans);

    }

    public static int lowerBound(long target) {
        int left = 0, right = cd.length;
        while(left < right) {
            int mid = (right + left) / 2;
            if(cd[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int upperBound(long target) {
        int left = 0, right = cd.length;
        while(left < right) {
            int mid = (right + left) / 2;
            if(cd[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
