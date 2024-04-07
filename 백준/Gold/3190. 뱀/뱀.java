
import java.io.*;
import java.util.*;
public class Main {

    static int N, K, L;
    static int[][] map;
    static List<int[]> snake;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int cnt;
    static HashMap<Integer, String> hashMap;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        cnt = 0;
        map = new int[N+1][N+1];
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }
        L = Integer.parseInt(br.readLine());
        hashMap = new HashMap<>();
        for(int i=0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            hashMap.put(a, s);
        }
        snake = new ArrayList<>();
        dfs();
    }

    public static void dfs() {
        int nr = 1, nc = 1;
        map[nr][nc] = 1;
        int loc = 1;
        snake.add(new int[] {nr, nc});

        while(true) {
            /**
             * 1. 시간++
             * 2. 정해진 방향으로 뱀이 출발
             * 3. 벽을 만나거나, 뱀의 신체 일부에 닿으면 종료
             * 4. 사과를 만나면, 현재 꼬리는 그대로 머리만 늘어남
             * 5. 문제 없으면, 머리가 다음 위치로 1, 꼬리는 다음 위치로 0
             * 6. 방향을 바꿔주는 시간을 만나면, 방향을 바꾼다.
             */
            cnt++;
            int nextR = nr + dr[loc];
            int nextC = nc + dc[loc];

            if((nextR <= 0 || nextR > N || nextC <= 0 || nextC > N) ||
                map[nextR][nextC] == 1) {
                break;
            }

            if(map[nextR][nextC] == 2) {
                map[nextR][nextC] = 1;
                snake.add(new int[] {nextR, nextC});
            }

            else if(map[nextR][nextC] == 0) {
                int[] tail = snake.remove(0);
                map[tail[0]][tail[1]] = 0; // 꼬리 값 0으로 변경
                map[nextR][nextC] = 1; // 새로운 머리 값 1로 변경
                snake.add(new int[] {nextR, nextC});
            }

            if(hashMap.containsKey(cnt)) {
                String location = hashMap.get(cnt); // cnt 값에 따른 방향 값 확인
                if(location.equals("L")) {
                    if(loc == 0) loc = 3;
                    else loc--;
                }
                else if(location.equals("D")) {
                    if(loc == 3) loc = 0;
                    else loc++;
                }
            }
            nr = nextR;
            nc = nextC;
        }
        System.out.println(cnt);
    }
}
