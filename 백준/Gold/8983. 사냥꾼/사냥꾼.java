
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] arr;
    static long[][] animals;
    static int ans;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[M];
        animals = new long[N][2];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            animals[i][0] = a; animals[i][1] = b;
        }

        Arrays.sort(arr);
//        Arrays.sort(animals, (a, b) ->
//           Integer.compare(a[0] + a[1], b[0] + b[1])
//        );

        ans = 0;

        for(int i=0;i<N;i++) {
            // i번째 동물이 갈 수 있는 가장 작은 사대를 찾기
            if(animals[i][1] > L) continue;

            long remain = L - animals[i][1];
            int idx = binarySearch(animals[i][0]);

            boolean flag = false;

            // 후보 1 : idx 그대로
            if(idx < M && Math.abs(arr[idx] - animals[i][0]) <= remain) flag = true;
            // 후보 2 : idx - 1 자리
            if(!flag && idx > 0) {
                if (Math.abs(arr[idx - 1] - animals[i][0]) <= remain) flag = true;
            }

            if(flag) ans++;
        }

        System.out.println(ans);

//        for(int i=0;i<N;i++) {
//            System.out.println(animals[i][0] + " " + animals[i][1]);
//        }

    }

    private static int binarySearch(long target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] >= target) right = mid;
            else left = mid + 1;
        }

        return left;
    }
    /**
     * M : 사대의 수, N : 동물의 수, L : 사정거리
     * M개의 사대별 X위치
     * N개의 동물들의 (x, y) 좌표가 주어짐
     */
}
