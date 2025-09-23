import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int curr, move;
        Point(int curr, int move) {
            this.curr = curr;
            this.move = move;
        }
    }
    static int max = 100001;
    static int ans = Integer.MAX_VALUE;
    static int N, K;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs();
        // 갈 수 있는 경우는, 현재 자리에서 2x 순간이동 혹은 +1, -1 을 이동하는 경우이다.
    }

    public static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        int[] v= new int[max];
        Q.offer(new Point(N, 0));
        Arrays.fill(v, Integer.MAX_VALUE);
        v[N] = 0;
        // 다 MAX 로 채워두고, 현재 배열의 curr 보다 작으면 그 값을 재갱신할 수 잇도록 한다

        while(!Q.isEmpty()) {
            Point p = Q.poll();

            // 2배 순간이동 하는 경우, 범위를 벗어나는 경우는 제외하고 위치값만 2배, 이동값은 그대로
            if(p.curr * 2 < max && v[p.curr * 2] > p.move) {
                v[p.curr * 2] = p.move;
                Q.offer(new Point(p.curr * 2, p.move));
            }
            // +1, -1을 진행하는경우
            if(p.curr + 1 < max && v[p.curr + 1] > p.move + 1) {
                v[p.curr + 1] = p.move + 1;
                Q.offer(new Point(p.curr + 1, p.move + 1));
            }

            if(p.curr - 1 >= 0 && v[p.curr - 1] > p.move + 1) {
                v[p.curr - 1] = p.move + 1;
                Q.offer(new Point(p.curr - 1, p.move + 1));
            }
        }

        System.out.println(v[K]);
    }
    // 10 14
}
