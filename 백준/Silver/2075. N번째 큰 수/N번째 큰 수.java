
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                PQ.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int idx = 0;
        int answer = 0;
        while(idx < N) {
            answer = PQ.poll();
            idx++;
        }
        System.out.println(answer);
    }
}
