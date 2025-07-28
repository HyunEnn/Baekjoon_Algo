
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> PQ = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            PQ.add(Long.parseLong(st.nextToken()));
        }

        for(int i=0;i<M;i++) {
            if(PQ.size() >= 2) {
                long f = PQ.poll();
                long s = PQ.poll();
                PQ.offer(f + s);
                PQ.offer(f + s);
            }
            // f, s를 뽑고 더해준 다음 다시 PQ에 대입
        }

        long ans = 0;
        for(long i : PQ) {
            ans += i;
        }

        System.out.println(ans);
    }

    /**
     * x번 카드와 y번 카드를 골라 그 두 장에 쓰여진 수를 더한 값을 계산한다. (x ≠ y)
     * 계산한 값을 x번 카드와 y번 카드 두 장 모두에 덮어 쓴다.
     *
     * 카드 합체 총 M번 하면 놀이 종료, M번 이후 N장의 카드에 쓰여있는 수를 모두 더하면 놀이의 점수가 된다
     * 이 점수를 가장 작게 만들라면?
     *
     * 1. 처음부터, (x,y) 카드를 뽑을때 가장 작은 값들을 뽑아서 더하게 만들면 쉽게 가능
     */
}
