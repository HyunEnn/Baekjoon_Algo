import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, K;
    static int[] arr = new int[100002];
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        BFS();
        System.out.println(arr[K]);
    }

    public static void BFS() {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(N);
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            if(cur == K) break;
            // X + 1
            if(cur + 1 <= 100001 && arr[cur + 1] == 0) {
                Q.offer(cur + 1);
                arr[cur + 1] = arr[cur] + 1;
            }
            // X - 1
            if(cur - 1 >= 0 && arr[cur - 1] == 0) {
                Q.offer(cur - 1);
                arr[cur - 1] = arr[cur] + 1;
            }
            // 텔레포트
            if(cur * 2 <= 100001 && arr[cur * 2] == 0) {
                Q.offer(cur * 2);
                arr[cur * 2] = arr[cur] + 1;
            }
        }
    }
}
