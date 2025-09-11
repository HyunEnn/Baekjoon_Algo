
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int num, wh;
        Point(int num, int wh) {
            this.num = num;
            this.wh = wh;
        }
    }
    static int N, M;
    static int[][] players;
    static List<Point> list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        players = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
                list.add(new Point(players[i][j], i));
            }
        }

        list.sort((a, b) -> Integer.compare(a.num, b.num));

        int left = 0, have = 0, ans = Integer.MAX_VALUE;
        int[] ch = new int[N];
        for(int r=0;r<list.size();r++) {
            int cls = list.get(r).wh;
            if(ch[cls] == 0) have++;
            ch[cls]++;

            while(have == N) {
                ans = Math.min(ans, list.get(r).num - list.get(left).num);

                int other = list.get(left).wh;
                if(ch[other] == 1) have--;
                ch[other] = Math.max(0, ch[other] - 1);
                left++;
            }
        }

        System.out.println(ans);
    }
    /**
     * 3 4
     * 12 16 67 43
     * 7 17 48 68
     * 14 15 54 77
     *
     * 7-2 12-1 14-3 15-3 16-1 17-2 43-1 48-2 54-3 67-1 68-2 77-3
     */
}
