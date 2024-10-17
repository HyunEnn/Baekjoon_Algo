
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            int a = Integer.parseInt(br.readLine());
            // 두 개의 PQ를 통해서 값을 나눈다?
            // maxPQ, minPQ로 각 PQ는 크기를 동일하게 맞춘다
            // minPQ가 더 커지면 minPQ의 마지막 값을 maxPQ에 넣어주고, 나머진 반대로 진행한다.
            // 2개 이상 커졌을때 위의 로직을 진행한다

            if(maxPQ.isEmpty() || a <= maxPQ.peek()) {
                maxPQ.offer(a);
            } else {
                minPQ.offer(a);
            }

            if(maxPQ.size() > minPQ.size() + 1) {
                minPQ.offer(maxPQ.poll());
            }

            else if(minPQ.size() > maxPQ.size()) {
                maxPQ.offer(minPQ.poll());
            }

            // 새로온 값이 maxPQ의 root 값보다 크다면, 빼고 minPQ에 넣은다음 기존 값을 maxPQ 에 넣는다.

            // 값은 maxPQ의 root 값을 peek 을 통해서 뽑아낸다?
            sb.append(maxPQ.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
