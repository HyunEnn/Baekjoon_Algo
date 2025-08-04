
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int ans = 0;
        while(right < N) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while(map.get(arr[right]) > K) {
                map.put(arr[left], map.get(arr[left]) - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        System.out.println(ans);

    }
}
