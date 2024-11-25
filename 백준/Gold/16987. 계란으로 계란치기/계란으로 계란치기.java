import java.util.*;
import java.io.*;

public class Main {
    static class egg {
        int s, w;

        egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }

    static int N;
    static int max = 0;
    static List<egg> eggs = new ArrayList<egg>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs.add(new egg(s, w));
        }

        // 백트레킹
        dfs(0);
        System.out.println(max);
    }

    public static void dfs(int idx) {
        // 가장 최근에 든 계란이 가장 오른쪽이면 종료
        if (idx == N) {
            int cnt = 0;
            for(int i=0;i<N;i++) {
                if(eggs.get(i).s <= 0) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        egg now = eggs.get(idx);
        // 깨진 달걀이면, 패스
        if(now.s > 0) {
            boolean attacked = false;
            for(int i=0;i<N;i++) {
                egg curr = eggs.get(i);
                if(curr == now) continue;
                if(curr.s <= 0) continue;

                attacked = true;
                now.s -= curr.w;
                curr.s -= now.w;
                dfs(idx + 1);
                now.s += curr.w;
                curr.s += now.w;
            }
            if(!attacked)
                dfs(idx+1);
        } else {
            dfs(idx + 1);
        }
    }
}
