
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer;
    static char[][] map = new char[5][5];
    static List<Point> selected = new ArrayList<>();
    static List<Point> list = new ArrayList<Point>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
                list.add(new Point(i, j));
            }
        }

        answer = 0;
        // 조합
        dfs( 0, 0);

        System.out.println(answer);
    }

    public static void dfs(int cnt, int idx) {
        if (cnt == 7) {
            if(isPossible())
                answer++;
            return;
        }

        for(int i=idx;i<list.size();i++) {
            selected.add(list.get(i));
            dfs(cnt + 1, i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    public static boolean isPossible() {
        int check = 0;
        for(Point p : selected) {
            if(map[p.r][p.c] == 'S') {
                check++;
            }
        }

        if(check >= 4)
            return bfs();
        else
            return false;
    }

    public static boolean bfs() {
        boolean[][] v = new boolean[5][5];
        Queue<Point> Q = new ArrayDeque<>();

        v[selected.get(0).r][selected.get(0).c] = true;
        Q.offer(new Point(selected.get(0).r, selected.get(0).c));

        int check = 1;


        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc] && isInSelected(nr, nc)) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    check++;
                }
            }
        }

//        System.out.println(check);

        return check == 7;
    }

    public static boolean isInSelected(int r, int c) {
        for(Point p : selected) {
            if(p.r == r && p.c == c)
                return true;
        }
        return false;
    }
}
