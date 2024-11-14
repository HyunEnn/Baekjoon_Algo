
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
        int currentWeight = 0;  // 현재 다리 위의 트럭 총 무게

        while (!bridge.isEmpty()) {
            time++; // 매 초마다 시간을 증가

            // 다리에서 트럭을 한 칸씩 이동시킴 (다리 끝에서 트럭을 내림)
            currentWeight -= bridge.poll();

            // 트럭이 아직 남아있다면 다리에 진입을 시도
            if (!trucks.isEmpty()) {
                int nextTruck = trucks.peek();

                // 다리에 트럭을 올릴 수 있는지 무게를 확인
                if (currentWeight + nextTruck <= L) {
                    bridge.offer(nextTruck);  // 트럭을 다리에 올림
                    currentWeight += nextTruck;  // 현재 다리 무게 증가
                    trucks.poll();  // 트럭을 대기 큐에서 제거
                } else {
                    bridge.offer(0);  // 무게가 초과하면 빈 칸을 추가해 다리를 채움
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
