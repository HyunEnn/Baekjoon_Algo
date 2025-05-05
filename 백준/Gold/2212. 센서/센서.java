
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        PriorityQueue<Integer> PQ = new PriorityQueue<>((a,b) -> b - a);

        for(int i=0;i<N-1;i++) {
            PQ.offer(Math.abs(arr[i] - arr[i+1]));
        }

        for(int i=0;i<K-1;i++) {
            PQ.poll();
        }

        int sum = 0;
        while(!PQ.isEmpty()) {
            sum += PQ.poll();
        }
        System.out.println(sum);
    }
    // 1. 정렬
    // 2. 거리 순 정렬 PQ
    // 3. K-1 개 만큼 제거
}
