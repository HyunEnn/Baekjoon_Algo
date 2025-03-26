
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int r, c, val;
        Point(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        @Override
        public int compareTo(Point p) {
            return this.val - p.val;
        }
    }
    static int N, K;
    static int S, X, Y;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static PriorityQueue<Point> PQ = new PriorityQueue<Point>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) PQ.offer(new Point(i, j, map[i][j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int time = 0;
//        rotate();
        while(time < S) {
            if(PQ.isEmpty())
                break; // PQ가 비었다면 종료
            else
                rotate();
            time++;
        }

//        for(int i=0;i<N;i++) {
//            for(int j=0;j<N;j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(map[X-1][Y-1]);
    }

    public static void rotate() {
        Queue<Point> Q = new ArrayDeque<>();
        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(!inRange(nr, nc) || map[nr][nc] != 0) continue; // 범위를 벗어나거나, 0이 아닌 경우 무시

                map[nr][nc] = p.val;
                Q.offer(new Point(nr, nc, map[nr][nc]));
            }
        }
        PQ = new PriorityQueue<>(Q);
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    // 매 시간마다 PQ에 들어있는 값을 4방 탐색을 통해 접근한다.
    // 그 값이 0이라면, 현재 값을 적용하고 그 위치를 새로운 Q에 삽입한다.
    // 그리고, 다시 기존 PQ에 정렬해서 넣는다.
    // PQ가 더 이상 탐색할 노드가 없다면 종료한다.
}
