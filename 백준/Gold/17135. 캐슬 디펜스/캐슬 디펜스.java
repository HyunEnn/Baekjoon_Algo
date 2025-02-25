
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int N, M, D;
    static int[][] map;
    static int[][] copyMap;
    static boolean[] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];
        copyMap = new int[N+1][M];
        answer = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        placeArchers(0, 0);
        System.out.println(answer);
    }

    public static void placeArchers(int idx, int cnt) {
        // basis
        if(cnt == 3) {
            answer = Math.max(answer, findMaxsum());
            return;
        }

        // inductive
        for(int i=idx;i<M;i++) {
            if(map[N][i] == 0) {
                map[N][i] = -1;
                placeArchers(i+1, cnt+1);
                map[N][i] = 0;
            }
        }
    }

    public static int findMaxsum() {
        int sum = 0;
        int idx = 0;
        for(int i=0;i<=N;i++) {
            for(int j=0;j<M;j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        // N 만큼 반복
        while(idx < N) {
            // 배열 검사 -> 모두 0 이면 종료
            if(checkArr()) break;
            // D 가 가능한 거리의 적들을 계산
            sum += calculate();
            // 배열 이동
            copyMap = moveArr(copyMap);
//            System.out.println("========");
//            printMap();
//            System.out.println("========");
            idx++;
        }
        return sum;
    }

    public static boolean checkArr() {

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(copyMap[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static void printMap(){
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int calculate() {
        int cnt = 0;
        List<int[]> targets = new ArrayList<>(); // 공격할 적 후보 리스트

        // 각 궁수의 위치에서 공격할 적을 찾는다
        for(int j=0; j<M; j++) {
            if(copyMap[N][j] == -1) { // 궁수 위치
                int[] target = findTarget(N, j); // 공격할 적 찾기
                if(target != null) {
                    targets.add(target); // 후보 리스트에 추가
                }
            }
        }

        // 모든 궁수가 공격할 적 선택 완료 후, 중복 없이 한 번에 제거
        for(int[] t : targets) {
            if(copyMap[t[0]][t[1]] == 1) { // 아직 살아있는 적일 경우에만 제거
                copyMap[t[0]][t[1]] = 0;
                cnt++;
            }
        }

        return cnt;
    }

    public static int[] findTarget(int r, int c) {
        int[] target = null;
        int minDist = D + 1; // 최대 사거리보다 1 큰 값으로 초기화

        for(int i=N-1; i>=0; i--) {
            for(int j=0; j<M; j++) {
                if(copyMap[i][j] == 1) {
                    int dist = Math.abs(r - i) + Math.abs(c - j);
                    if(dist <= D) {
                        if(dist < minDist) {
                            minDist = dist;
                            target = new int[]{i, j};
                        } else if(dist == minDist) {
                            // 거리가 같다면 가장 왼쪽의 적을 선택
                            if(target != null && j < target[1]) {
                                target = new int[]{i, j};
                            }
                        }
                    }
                }
            }
        }

        return target;
    }

    public static int[][] moveArr(int[][] copyMap) {
        int[][] mArr = new int[N+1][M];
        for(int i=N-1;i>0;i--) {
            for(int j=0;j<M;j++) {
                mArr[i][j] = copyMap[i-1][j];
            }
        }
        // 첫 행은 초기화
        for(int j=0; j<M; j++) {
            mArr[0][j] = 0;
        }
        // 궁수 자리는 참조
        mArr[N] = copyMap[N];

        return mArr;
    }
}
