import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int w, v;
        public Pair(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
    static int N, K;
    static List<Pair> list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new Pair(m, v));
        }
        
        // 무게 오름차순으로 정렬
        list.sort((a, b) -> {
            return a.w - b.w;
        });

        long ans = 0;
        int[] bags = new int[K];
        for(int i=0;i<K;i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 가방도 오름차순 정렬
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
        // 그리디하게, heap 에 가능한 무게들의 가치들을 전부 push 해놓고, 현재에서 가장 높은 가치 하나를 쓴다
        int idx = 0;
        for(int i=0;i<K;i++) {
            // 현재 보석의 최대 갯수까지 탐색하면서, 무게는 현재 bags[i] 보다 작거나 같으면 PQ에 넣는다.
            while(idx < list.size() && list.get(idx).w <= bags[i]) {
                PQ.offer(list.get(idx).v);
                idx++;
            }

            // 그리고, PQ에 값이 있다면 그 중 가장 큰 값을 뽑아서 사용한다
            if(!PQ.isEmpty()) ans += PQ.poll();
        }
        System.out.println(ans);

        // 주어지는 보석 갯수는 백만개, K의 갯수는 30만개
    }
}
