
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Deque<Integer> Q = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            Q.offer(i);
        }

        st = new StringTokenizer(br.readLine());
        int ans = 0;
        for(int i=0;i<M;i++) {
            int num = Integer.parseInt(st.nextToken());
            int pos = 0;
            for(int x : Q) {
                if(x == num) break;
                pos++;
            }
            int len = Q.size();

            int cnt = 0;
            if(len - pos < pos) {
                pos = len - pos;
                while(pos != 0) {
                    Q.offerFirst(Q.pollLast());
                    pos--;
                    cnt++;
                }
            } else {
                while(pos != 0) {
                    Q.offerLast(Q.pollFirst());
                    pos--;
                    cnt++;
                }
            }
            Q.removeFirst();
            ans += cnt;
//            System.out.println(i + " 번째 현재 진행 : " + ans);
//            for (int x : Q) {
//                System.out.print(x + " ");
//            }
//            System.out.println();
        }
        System.out.println(ans);
    }
}
