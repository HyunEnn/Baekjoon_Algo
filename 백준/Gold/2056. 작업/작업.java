
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] degree;
    static int[] time;
    static int[] dp;
    static List<Integer>[] list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        degree = new int[N + 1];
        dp = new int[N + 1];
        list = new ArrayList[N + 1];
        time = new int[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        // 걸리는 시간, 선행 작업 갯수, (선행 작업 ..)
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            int k = Integer.parseInt(st.nextToken());
            for(int j=0;j<k;j++) {
                // 선행 조건이므로, 방향 그래프 리스트에 추가하고 degree 를 증가시킨다.
                int a = Integer.parseInt(st.nextToken());
                list[i].add(a);
                degree[a]++;
            }
        }
        
        Queue<Integer> Q = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            if(degree[i] == 0) {
                dp[i] = time[i];
                Q.offer(i);
            }
        }
        // degree 기준으로 차수가 0인 값들을 우선 제거하고, 연결된 노드의 degree 를 1씩 낮춘다.
        // dp 에 시간을 갱신하고, 가장 오래 걸리는 시간을 갱신한다.
        while(!Q.isEmpty()) {
            int p = Q.poll();
            for(int np : list[p]) {
                degree[np]--;
                dp[np] = Math.max(dp[np], dp[p] + time[np]);
                if(degree[np] == 0) Q.offer(np);
            }
        }

        int answer = 0;
        for(int i=1;i<=N;i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
