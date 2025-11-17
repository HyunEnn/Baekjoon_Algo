
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int c, w;

        Point(int c, int w) {
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            return this.w - p.w;
        }
    }

    static int T, N, M, K;
    static int[][] dist;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            List<Point>[] list = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                list[r].add(new Point(c, w));
                list[c].add(new Point(r, w));
            }
            K = Integer.parseInt(br.readLine());
            // 시작하게 될 좌표 위치 저장
            st = new StringTokenizer(br.readLine());
            int[] starts = new int[K];
            for (int i = 0; i < K; i++) {
                starts[i] = Integer.parseInt(st.nextToken());
            }
            // 좌표갯수만큼 dist 배열 생성
            dist = new int[K][N + 1];
            for (int i = 0; i < K; i++) {
                Arrays.fill(dist[i], -1);
            }
            // 시작 정점 순서대로 탐색해서 dist 에 저장한다.
            PriorityQueue<Point> PQ = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                int curr = starts[i];
                dist[i][curr] = 0;
                PQ.offer(new Point(starts[i], 0));

                while (!PQ.isEmpty()) {
                    Point p = PQ.poll();
                    // 이동하는 위치는 np, 현재 위치는 p
                    for (Point np : list[p.c]) {
                        if (dist[i][np.c] == -1 || dist[i][p.c] + np.w < dist[i][np.c]) {
                            dist[i][np.c] = dist[i][p.c] + np.w;
                            PQ.offer(new Point(np.c, np.w));
                        }
                    }
                }

//                System.out.println("이동한 dist : ");
//                for(int j=1;j<=N;j++) {
//                    System.out.print(dist[i][j] + " ");
//                }
//                System.out.println();
            }

            int ans = Integer.MAX_VALUE;
            int idx = 0;
            for (int j = 1; j <= N; j++) {
                int sum = 0;
                for (int i = 0; i < K; i++) {
                    sum += dist[i][j];
                }
                if (sum < ans) {
                    ans = sum;
                    idx = j;
                }
            }

            System.out.println(idx);
        }

    }
}
