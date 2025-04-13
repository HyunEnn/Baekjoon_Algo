
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int h, cnt, day;
        Pair(int h, int cnt, int day) {
            this.h = h;
            this.cnt = cnt;
            this.day = day;
        }
    }
    static int X, Y;
    static int ans = Integer.MAX_VALUE;
    static Set<Long> set = new HashSet<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int diff = Y - X;
        long n = 0;
        long ans = 0;

        if(diff == 0) {
            System.out.println(0);
            return;
        }

        while(n*n <= diff) {
            n++;
        }
        n-=1;
        ans = 2*n-1;

        diff -= n*n;
        long a = n;
        int answer = 0;

        while(diff > 0) {
            diff -= Math.min(n, diff);
            answer++;
        }

        System.out.println(answer + ans);

        // 121, 144 -> 140 이면 19
//        System.out.println(ans);
    }

//    public static int bfs() {
//        Queue<Pair> Q = new ArrayDeque<>();
//        Q.offer(new Pair(X, 0, 0));
//
//        while(!Q.isEmpty()) {
//            Pair p = Q.poll();
//            if(p.h == Y) return p.day;
//            // 줄거나, 그대로거나, 다음값이거나
//            for(int k=-1;k<=1;k++) {
//                int next = p.cnt + k;
//                if(next >= 0) {
//                    int curr = p.h + next;
//                    long key = ((long) curr << 20) | next;
//                    if(set.contains(key)) continue;
//                    set.add(key);
//                    Q.offer(new Pair(curr, next, p.day + 1));
//                }
//            }
//        }
//
//        return -1;
//    }

//    public static void dfs(int st, int height, int day) {
//        // basis
//        String key = st + " " + height;
//        if(set.contains(key)) return;
//        set.add(key);
//
//        if(st == Y) {
//            ans = Math.min(ans, day);
//            return;
//        }
//        if(st > Y) return;
//        // inductive
//        dfs(st + height + 1, height + 1, day + 1);
//        dfs(st + height, height, day + 1);
//        if(height - 1 >= 0)
//            dfs(st + height - 1, height - 1, day + 1);
//    }
    // 하루에 늘릴 수 있는 키의 양은 1cm
    // 0cm 밑으로 내려갈 순 없고, 첫날과 마지막 날은 무조건 1cm 만 가능
    // 45 -> (46, 1, 1) ->
}
