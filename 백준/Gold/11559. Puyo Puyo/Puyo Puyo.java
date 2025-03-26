
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
    static int answer = 0;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for(int i=0;i<12;i++) {
            String line = br.readLine();
            for(int j=0;j<6;j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 한번에 모든 bfs 를 터트리고 1개의 값으로 처리해야함
        while(true) {
            int cnt = 0;
            v = new boolean[12][6];
            for(int i=11;i>=0;i--) {
                for(int j=0;j<6;j++) {
                    if(map[i][j] != '.' && !v[i][j])
                        if(bfs(i, j)) cnt++;
                }
            }
//            for(int i=0;i<12;i++) {
//                for(int j=0;j<6;j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            if(cnt == 0) break;
            else answer++;

            moveBlock();
        }

        System.out.println(answer);
    }

    public static boolean bfs(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r,c));
        boolean[][] ch = new boolean[12][6];
        int cnt = 1;
        List<Point> list = new ArrayList<>();
        list.add(new Point(r,c));
        ch[r][c] = true;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];

                // 범위를 벗어나거나 이미 지난 자리면 무시
                if(!inRange(nr, nc) || ch[nr][nc] || map[nr][nc] == '.') continue;
                // . 인 구역은 빈 공간이니 무시

                if(map[nr][nc] == map[p.r][p.c]) {
                    ch[nr][nc] = true;
                    cnt++;
                    Q.offer(new Point(nr, nc));
                    list.add(new Point(nr, nc));
                }
            }
        }

        if(cnt >= 4) {
            // 4 이상이면 지워야 하기 때문에, list 에 있는 값들 true 처리
            for(Point p : list) {
                map[p.r][p.c] = '.';
                v[p.r][p.c] = true;
            }

            return true;
        }

        return false;
    }

    public static void moveBlock() {
        for(int j=0;j<6;j++) {
            int ch = 0;
            for(int i=11;i>=0;i--) {
                // .으로 변했지만, 방문했던 자리라면
                if(v[i][j]) ch++;
                else if(map[i][j] != '.' && !v[i][j] && ch > 0){ // .이 아니고 방문안했을 경우와 한번이라도 체킹이 됬으면 내림
                    map[i+ch][j] = map[i][j];
                    map[i][j] = '.';
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 12 && c >= 0 && c < 6;
    }
}
