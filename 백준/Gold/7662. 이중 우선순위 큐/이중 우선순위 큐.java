
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T, K;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            K = Integer.parseInt(br.readLine());
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());

                // 양 쪽에 다 더해줌
                if (c == 'I') {
                    minHeap.add(a);
                    maxHeap.add(a);
                    map.put(a, map.getOrDefault(a, 0) + 1);
                } else if (c == 'D') {
                    // 최소 값 제거
                    if (a == -1) {
                        while (!minHeap.isEmpty() && (map.get(minHeap.peek()) == null || map.get(minHeap.peek()) == 0)) {
                            minHeap.poll();
                        }

                        if (minHeap.isEmpty()) continue;

                        int poll = minHeap.poll();
                        map.put(poll, map.get(poll) - 1);
                        if (map.get(poll) == 0) map.remove(poll);
                    } 
                    // 최대값 제거
                    else {
                        while(!maxHeap.isEmpty() && (map.get(maxHeap.peek()) == null || map.get(maxHeap.peek()) == 0)) {
                            maxHeap.poll();
                        }

                        if (maxHeap.isEmpty()) continue;

                        int poll = maxHeap.poll();
                        map.put(poll, map.get(poll) - 1);
                        if (map.get(poll) == 0) map.remove(poll);
                    }
                }
            }

            while (!minHeap.isEmpty() && (map.get(minHeap.peek()) == null || map.get(minHeap.peek()) == 0)) {
                minHeap.poll();
            }

            while(!maxHeap.isEmpty() && (map.get(maxHeap.peek()) == null || map.get(maxHeap.peek()) == 0)) {
                maxHeap.poll();
            }

            if(minHeap.isEmpty() || maxHeap.isEmpty()) System.out.println("EMPTY");
            else {
                System.out.println(maxHeap.peek() + " " + minHeap.peek());
            }


        }
    }
}
