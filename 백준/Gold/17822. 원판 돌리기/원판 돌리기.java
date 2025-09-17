
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] map;
    static int[][] tmp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            tmp = new int[N+1][M];
            copyMap();

            // 1. 번호가 xi의 배수인 원판을 di 방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
            if(d == 0){
                for(int s=x;s<=N;s+=x) {
                    for(int i=0;i<k;i++) { rotateClock(s);}
                }

            }
            else
                for(int s=x;s<=N;s+=x) {
                    for(int i=0;i<k;i++) { rotateOther(s); }
                }

//            System.out.println("회전 직후 : ");
//            printMap();
            // 2. 원판에 수가 남아있으면, 인접하면서 수가 같은 것을 모두 지운다.
            removeNear();

//            printMap();
//            System.out.println();
            // 3. 기존 맵과 체크하고, true 가 나오면 원판에 적힌 평균을 구하고, 평균보다 큰 수는 -1, 작으면 +1
            if(checkMap()) {
                double avg = calAvg();
//                System.out.println("평균 : " + avg);
                reflect(avg);
            }
        }

        int ans = 0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                ans += map[i][j];
//                System.out.print(map[i][j] + " ");
            }
//            System.out.println();
        }

        System.out.println(ans);
    }

    public static void printMap() {
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotateClock(int x) {
        // 시계 방향 회전
        int tmp = map[x][M-1];
        for(int i=M-1;i>0;i--) {
            map[x][i] = map[x][i-1];
        }
        map[x][0] = tmp;
    }

    public static void rotateOther(int x) {
        // 반시계 방향 회전
        int tmp = map[x][0];
        for(int i=0;i<M-1;i++) {
            map[x][i] = map[x][i+1];
        }
        map[x][M-1] = tmp;
    }

    public static void copyMap() {
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }

    public static boolean checkMap() {
        int first = 0, second = 0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                first += map[i][j];
                second += tmp[i][j];
            }
        }
        return first == second;
    }

    public static double calAvg() {
        long sum = 0; int cnt = 0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] != 0) {
                    cnt++;
                    sum += map[i][j];
                }
            }
        }
        if(cnt == 0) return sum;
        return (double) sum / cnt;
    }

    public static void reflect(double avg) {
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] != 0) {
                    if(map[i][j] > avg) map[i][j] -= 1;
                    else if(map[i][j] < avg) map[i][j] += 1;
                }
            }
        }
    }

    public static void removeNear() {
        boolean[][] v = new boolean[N+1][M];
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                boolean ch = false;
                int curr = map[i][j];

                if(curr == 0) continue; // 현재 자리가 0이라면, 누군가와 비교가 불가능하니 모두 패스

                // 행의 경우 -> (1,0) 인 경우
                if(i == 1) {
                    if(map[i+1][j] == curr) { v[i][j] = v[i+1][j] = true; ch = true;}
                }
                // (N,0) 인 경우
                else if(i == N) {
                    if(map[i-1][j] == curr) { v[i][j] = v[i-1][j] = true; ch = true;}
                }
                // 나머지인 경우
                else {
                    if(map[i-1][j] == curr) { v[i][j] = v[i-1][j] = true; ch = true;}
                    if(map[i+1][j] == curr) { v[i][j] = v[i+1][j] = true; ch = true;}
                }
                // 열의 경우 -> (1,0) 인 경우
                if(j == 0) {
                    if(map[i][j+1] == curr) { v[i][j] = v[i][j+1] = true; ch = true;}
                    if(map[i][M-1] == curr) { v[i][j] = v[i][M-1] = true; ch = true;}
                }
                // (i, M-1) 인 경우
                else if(j == M-1) {
                    if(map[i][0] == curr) { v[i][j] = v[i][0] = true; ch = true;}
                    if(map[i][j-1] == curr) {v[i][j] = v[i][j-1] = true; ch = true;}
                }
                // 나머지 일반 경우
                else {
                    if(map[i][j-1] == curr) {v[i][j] = v[i][j-1] = true; ch = true;}
                    if(map[i][j+1] == curr) {v[i][j] = v[i][j+1] = true; ch = true;}
                }
            }
        }

        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(v[i][j]) map[i][j] = 0;
            }
        }
    }
    /**
     * (i, 1)은 (i, 2), (i, M)과 인접하다.
     * (i, M)은 (i, M-1), (i, 1)과 인접하다.
     * (i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
     * (1, j)는 (2, j)와 인접하다.
     * (N, j)는 (N-1, j)와 인접하다.
     * (i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)
     */
}
