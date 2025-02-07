
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static char[][] map;
    static StringTokenizer st;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        boolean[][] first = new boolean[N][N];
        boolean[][] second = new boolean[N][N];
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 탐색을 동시에 2개 진행할건데, 적록색약인지 아닌지 파라미터를 정의하고
        // 그러기 위한 탐색용 boolean 배열이 2개 설정

        int normal = 0;
        int abnormal = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                // 현재 위치를 탐색하지 않았다면, 현재 위치 기반 bfs 탐색 시작
                if(!first[i][j]) {
                    bfs(i, j, 0, first);
                    normal++;
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(!second[i][j]) {
                    bfs(i, j, 1, second);
                    abnormal++;
                }
            }
        }

        System.out.println(normal + " " + abnormal);

        // 적록색약이 아니면 현재 색상에 대해서 정확히 구분
        // 적록색약이면 R, G 가 동일 색으로 보임
    }

    public static void bfs(int r, int c, int check, boolean[][] v) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        v[r][c] = true;
        char color = map[r][c];

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc]) {
                    if(check == 0 && map[nr][nc] == color) {
                        Q.offer(new Point(nr, nc));
                        v[nr][nc] = true;
                        // color 가 R or G이면 둘 다 탐색하도록 세팅
                    } else if(check == 1) {
                        if(color == 'R' || color == 'G') {
                            if(map[nr][nc] == 'R' || map[nr][nc] == 'G') {
                                Q.offer(new Point(nr, nc));
                                v[nr][nc] = true;
                            }
                        } else {
                            if(map[nr][nc] == color) {
                                Q.offer(new Point(nr, nc));
                                v[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
