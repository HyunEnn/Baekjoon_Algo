
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, W, L;
    static Queue<Integer> trucks = new ArrayDeque<>();
    static Queue<Integer> bridge;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        bridge = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }
        // 다리 길이만큼 큐를 0으로 초기화하여 다리 위의 빈 공간을 나타냄
        for (int i = 0; i < W; i++) {
            bridge.offer(0);
        }

        int time = 0;      // 시간 초기화
        int weight = 0;  // 현재 다리 위의 트럭 총 무게

        while (!bridge.isEmpty()) {
            time++;
            int a = bridge.poll();
            weight -= a;

            if(!trucks.isEmpty()) {
                if(trucks.peek() + weight <= L) {
                    int now = trucks.poll();
                    weight += now;
                    bridge.offer(now);
                } else {
                    bridge.offer(0);
                }
            }
        }

        System.out.println(time);
    }


    public static boolean canGo(int p) {
        int sum = 0;
        for(int i : bridge) {
            sum += i;
        }
        return sum + p <= L;
    }
}
