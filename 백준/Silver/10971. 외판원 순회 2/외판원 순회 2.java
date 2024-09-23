
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] list;
    static int min;
    static boolean[] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N];
        min = Integer.MAX_VALUE;
        list = new ArrayList[N];
        for(int i=0;i<N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(0, 0, 0);
        System.out.println(min);
    }

    static void dfs(int idx, int cnt, int village) {
        // 마을을 다 탐색하면 최소값 뽑고 종료
        if(idx == N-1) {
            // 시작점으로 못돌아갈 경우
            if(list[village].get(0) == 0)
                return;
            min = Math.min(min, cnt + list[village].get(0));
            return;
        }

        // 마을별 탐색
        for(int i=1;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                int moveDir = list[village].get(i);
                // 갈 수 없는 마을인 경우는 제외
                if(moveDir != 0)
                    dfs(idx + 1, cnt + moveDir, i);
                v[i] = false;
            }
        }
    }
}
