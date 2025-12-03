
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, hp, barrier, cnt;
        Point(int r, int c,int hp, int barrier, int cnt) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.barrier = barrier;
            this.cnt = cnt;
        }
    }
    static int N, H, D; // 길이, 현재 체력, 우산 내구도
    static char[][] map;
    static int[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC, endR, endC;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        v = new int[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S') { startR = i; startC = j; }
                else if(map[i][j] == 'E') { endR = i; endC = j; }
            }
        }

        Queue<Point> Q = new ArrayDeque<>();
        v[startR][startC] = H;
        Q.offer(new Point(startR, startC, H, 0, 0)); // 시작할때, 우산없음

        while(!Q.isEmpty()) {
            Point p = Q.poll();

            if(p.hp <= 0) continue;
            // 기존 들어와 있던 피보다 적으면 무시하고 다음 내용을 진행한다.

            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                int hp = p.hp;
                int barrier = p.barrier;

                // 우선, 범위를 벗어나면 패스
                if(!inRange(nr, nc)) continue;
                // 움직이는 자리가 '.' 이라면, barrier 가 있다면 -1, 없으면 hp -1
                if(map[nr][nc] == '.') {
                    if(barrier > 0) barrier--;
                    else hp--;
                }
                // 움직이는 자리가 'U' 라면, 베리어를 현재 새로운 우산으로 교체한다.
                else if(map[nr][nc] == 'U') {
                    barrier = D;
                    barrier--;
                } else if(map[nr][nc] == 'E') {
                    System.out.println(p.cnt + 1);
                    return;
                }

                if(v[nr][nc] < hp + barrier) {
                    v[nr][nc] = hp + barrier;
                    Q.offer(new Point(nr, nc, hp, barrier, p.cnt + 1));
                }
//                System.out.println(nr + " " + nc + " , " + hp + " 와 " + barrier);
            }
        }

        System.out.println(-1);


        /**
         * 0. 시작할떄, 현재 시작 위치와 배리어를 통해 우산의 상태를 저장할 수 있는 상태로 진행한다. 만약에, 우산이 있으면 다음 칸 이동할때
         *  우산의 내구도를 깎고 피는 유지한 상태로 이동한다.
         *  이동할때, 방문배열을 통해 현재 자리의 피보다 많은 상태면 교체한다. 그리고, Q를 진행할때 현재 Q에 들어온 값이 기존 방문배열
         *  숫자보다 작다면 무시하고 다음 내용을 진행한다.
         * 1. 우산을 먹으면, 이동할때마다 1씩 우산의 내구도가 감소한다.
         * 1-1. 새로운 우산이 있다면, 기존 우산을 버리고 새로운 우산을 획득한다.
         * 2. BFS 탐색을 진행하면서, 우산이 있는곳을 진행하게 되면 현재 체력을 깎지 않고 진행
         * 2-1. 현재 체력이 0이 되면, 즉시 종료
         * 2-2. 도착지에 안전하게 도착하면 이동 횟수 출력
         *
         */
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
