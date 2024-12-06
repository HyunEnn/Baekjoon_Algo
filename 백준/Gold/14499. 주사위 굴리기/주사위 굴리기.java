
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y, K; // 지도 크기 N*M, 주사위 시작 위치(X,Y), 명령 개수 K
    static int[][] map;
    static int[] moveX = {0,0,0,0}; // 왼, 오
    static int[] moveY = {0,0,0,0}; // 상, 하
    static int[] orders;
    static int[] dr = {0, 0, -1, 1}; // 동 서 북 남
    static int[] dc = {1, -1, 0, 0};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        orders = new int[K];
        for(int i=0;i<K;i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<K;i++) {
            // 주사위가 범위를 벗어나면 무시
            int dir = orders[i] - 1; // 방향 설정
            int nr = X + dr[dir];
            int nc = Y + dc[dir];
            if(!inRange(nr, nc)) continue;

            simulate(dir); // 주사위 이동

            // 주사위와 지도 비교
            calculate(nr, nc);

            // 주사위 윗면 출력
            if(dir < 2) System.out.println(moveX[2]);
            else System.out.println(moveY[2]);

            // 현재 주사위 위치 변경
            X = nr;
            Y = nc;
        }
    }

    public static void calculate(int r, int c) {
        // 주사위를 굴렸을 때, 이동한 칸이 0이면 주사위 바닥면이 지도로 복사
        if(map[r][c] == 0) {
            map[r][c] = moveX[0];
        }
        // 0이 아니면, 칸에 쓰여진 수가 주사위 바닥으로 복사되고 지도는 0
        else {
            moveX[0] = map[r][c];
            moveY[0] = map[r][c];
            map[r][c] = 0;
        }
    }

    public static void simulate(int dir) {
        // 동쪽
        if(dir == 0) {
            int tmp = moveX[0]; // X 배열 왼쪽 돌리기
            for(int i=0;i<3;i++) {
                moveX[i] = moveX[i+1];
            } moveX[3] = tmp;
            // Y 배열 0,2번 인덱스 교체
            moveY[0] = moveX[0];
            moveY[2] = moveX[2];
        }
        // 서쪽
        else if(dir == 1) {
            // X 배열 오른쪽 돌리기
            int tmp = moveX[3];
            for(int i=3;i>0;i--) {
                moveX[i] = moveX[i-1];
            } moveX[0] = tmp;
            // Y 배열 0,2번 인덱스 교체
            moveY[0] = moveX[0];
            moveY[2] = moveX[2];
        }
        // 북쪽
        else if(dir == 2) {
            // Y 배열 왼쪽 돌리기
            int tmp = moveY[0]; // X 배열 왼쪽 돌리기
            for(int i=0;i<3;i++) {
                moveY[i] = moveY[i+1];
            } moveY[3] = tmp;
            // X 배열 0,2 번 인덱스 교체
            moveX[0] = moveY[0];
            moveX[2] = moveY[2];
        }
        // 남쪽
        else if(dir == 3) {
            // Y 배열 오른쪽 돌리기
            int tmp = moveY[3];
            for(int i=3;i>0;i--) {
                moveY[i] = moveY[i-1];
            } moveY[0] = tmp;
            // X 배열 0,2번 인덱스 교체
            moveX[0] = moveY[0];
            moveX[2] = moveY[2];
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
