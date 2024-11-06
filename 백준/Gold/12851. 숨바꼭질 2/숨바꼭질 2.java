import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr = new int[100002];
    static StringTokenizer st;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cnt = 0;
        for(int i=0;i<100002;i++) {
            arr[i] = -1;
        }
        BFS();
    }

    public static void BFS() {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(N);
        arr[N] = 0;
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            if(cur == K) {
                cnt++;
                continue;
            }
            if(cur < 0 || cur > 100001) continue;
            // +1을 할 때 기존의 값과 같으면
            if(cur + 1 <= 100001) {
                if(arr[cur + 1] == arr[cur] + 1)
                   Q.offer(cur + 1);
                else if(arr[cur + 1] == -1) {
                    Q.offer(cur + 1);
                    arr[cur + 1] = arr[cur] + 1;
                }
            }
            // -1을 할 때 기존 값과 비교
            if(cur - 1 >= 0) {
                if(arr[cur - 1] == arr[cur] + 1)
                    Q.offer(cur - 1);
                else if(arr[cur - 1] == -1) {
                    Q.offer(cur - 1);
                    arr[cur - 1] = arr[cur] + 1;
                }
            }
            // *2 텔레포트
            if(cur * 2 <= 100001) {
                if(arr[cur * 2] == arr[cur] + 1)
                    Q.offer(cur * 2);
                else if(arr[cur * 2] == -1) {
                    Q.offer(cur * 2);
                    arr[cur * 2] = arr[cur] + 1;
                }
            }
        }

        System.out.println(arr[K]);
        System.out.println(cnt);
    }
}
