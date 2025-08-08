
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, cnt;

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] chMap;
    static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int startR, startC;
    static List<Point> arduino = new ArrayList<>();
    static StringTokenizer st;
    static String str;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        chMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'R') {
                    arduino.add(new Point(i, j, 1));
                    chMap[i][j] = 1;
                }
            }
        }

        str = br.readLine();
        if (bfs()) printMap();
        else System.out.println("kraj " + time);

        // I 번째 위치에 있는 아두이노가 먼저 이동함. 이것도 Q에 넣어서 진행할까?

        // 종수 아두이노가 미친 아두이노 칸으로 이동하게 되면 종료

        // 미친 아두이노는 맨해튼 거리로 종수 아두이노랑 가까운 거리로 한 칸 이동
        // 이동이 종수 위치로 이동되면 바로 종료

        // 2개 이상의 미친 아두이노가 모이면 모두 증발

        // 일단 최상단 while 문은 종수의 이동으로 시작하고, 나머진 아두이노 이동으로 감안한다.
        // 어차피, 종수 자리로 아두이노가 오면 게임 종료

        // 일단 p.cnt 로 아두이노가 현재 그 칸에 몇개 있는지를 생각하자. -> 2개 이상인 경우면 다 제거한다
        // 그리고, 종수는 따로 관리한다? 이게 되나 하나의 큐에서? 종수가 돌아가는 큐를 따로 지정하자?
        // 그럼 Q에 Q를 돌리는 느낌?
    }

    public static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean bfs() {
        time = 1;
        Queue<Point> Q = new ArrayDeque<>();
        for (Point p : arduino) {
            Q.offer(p);
        }

        boolean flag = false; // 종수가 터질 경우, 종료 조건문
        for (int i = 0; i < str.length(); i++) {
            // 벗어나는 입력이 주어지는 경우는 없다
            // 1. 이동을 진행한다.
            int dir = str.charAt(i) - '0' - 1;
            startR += dr[dir];
            startC += dc[dir];
            // 1-1. 여기서 아두이노를 만나면 바로 종료
            if (map[startR][startC] == 'R') {
                flag = true;
                break;
            }
            // 2. 미친 아두이노들 이동
            List<Point> stores = new ArrayList<>();
            while (!Q.isEmpty()) {
                Point p = Q.poll();
                // 맨해튼 거리 최소값 계산
                int dist = Integer.MAX_VALUE;
                int cal_dir = 0;
                for (int k = 0; k < 9; k++) {
                    if (k == 4) continue;
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];
                    int now = Math.abs(startR - nr) + Math.abs(startC - nc);
                    // 가장 짧은 맨해튼 거리의 방향값 설정
                    if (dist > now) {
                        dist = now;
                        cal_dir = k;
                    }
                }
                // 현재 자리가 터지는 자리냐 아니냐를 생각해야함 -> 판단 못함
                chMap[p.r][p.c]--;
                int moveR = p.r + dr[cal_dir];
                int moveC = p.c + dc[cal_dir];
                // 이동하는 자리가 종수가 있는 자리이면 바로 종료
                if (moveR == startR && moveC == startC) {
                    flag = true;
                    break;
                }
                chMap[moveR][moveC]++;
                stores.add(new Point(moveR, moveC));
            }

            if (flag) {
                break;
            }

            // 한번 아두이노들을 전부 이동시킨 다음에, 2개 이상 모인 아두이노들은 터트린다.
            // 즉, 2개 미만인 아두이노들만 Q에 다시 넣어준다.
            List<Point> selected = new ArrayList<>();
            for (Point p : stores) {
                if (chMap[p.r][p.c] < 2) {
                    Q.offer(p);
                    selected.add(p);
                }
            }
            // chMap 초기화하고 현재 selected 의 아두이노만 값을 설정
            chMap = new int[R][C];
            map = new char[R][C];
            for(Point p : selected) {
                chMap[p.r][p.c] = 1;
                map[p.r][p.c] = 'R';
            }
            // 지도는 종수 자리와 selected 자리 설정
            map[startR][startC] = 'I';
            time++;
        }


//        System.out.println("--- 아두이노 지도 ---");
//        for(int i=0;i<R;i++) {
//            for(int j=0;j<C;j++) {
//                System.out.print(chMap[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(Q.size());

    // flag 처리가 이루어지지 않고 모두 정상 종료했을때 지도 그려넣기
        if(!flag)

    {
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = '.';
            }
        }
        map[startR][startC] = 'I';
        while (!Q.isEmpty()) {
            Point p = Q.poll();
            map[p.r][p.c] = 'R';
        }
        return true;
    }
        return false;
}
}
