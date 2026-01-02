
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] arr;
    static int[][] animals;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[M];
        animals = new int[N][2];

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

        Arrays.sort(animals, (a, b) ->
           Integer.compare(a[0] + a[1], b[0] + b[1])
        );

        int ans = 0;

        for(int i=0;i<N;i++) {
            // i번째 동물이 갈 수 있는 가장 작은 사대를 찾기
            int curr = binarySearch(i);

            boolean flag = false;
            // 가장 가까운 사대
            if(Math.abs(arr[curr] - animals[i][0]) + animals[i][1] <= L) flag = true;

            if(flag) ans++;
        }

        System.out.println(ans);

//        for(int i=0;i<N;i++) {
//            System.out.println(animals[i][0] + " " + animals[i][1]);
//        }

    }

    private static int binarySearch(int target) {
        int left = 0, right = arr.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(Math.abs(animals[target][0] - arr[mid]) + animals[target][1] <= L) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
    /**
     * M : 사대의 수, N : 동물의 수, L : 사정거리
     * M개의 사대별 X위치
     * N개의 동물들의 (x, y) 좌표가 주어짐
     */
}
