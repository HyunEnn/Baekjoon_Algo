
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, s;
        Point(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    static List<Point> list = new ArrayList<>();
    static int N, M, K;
    static int[][] map;
    static List<Point> choose = new ArrayList<>();
    static boolean[] v;
    static int ans = Integer.MAX_VALUE;
    static int[][] copyMap;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가능한 순열을 뽑아서 배열 돌리기가 필요
        for(int k=0;k<K;k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list.add(new Point(r, c, s));
        }
        v = new boolean[list.size()];
        dfs(0);
        
        // 끝난 뒤 행 별로 최솟값을 출력
        System.out.println(ans);
    }

    public static void dfs(int idx) {
        // basis
        if(idx == K) {
            copyMap = new int[N+1][M+1];
            for(int i=1;i<=N;i++) {
                System.arraycopy(map[i], 1, copyMap[i], 1, M);
            }
            for(Point p : choose) {
                rotate(p.r, p.c, p.s);
            }
            ans = Math.min(ans, calculateRow());
            return;
        }
        // inductive
        for(int i=0;i<list.size();i++) {
            if(!v[i]) {
                v[i] = true;
                choose.add(list.get(i));
                dfs(idx + 1);
                v[i] = false;
                choose.remove(choose.size()-1);
            }
        }
    }

    public static void rotate(int r, int c, int s) {
        // 위 오른쪽 아래 왼쪽 순으로 배열 회전
        for(int k=0;k<s;k++) {
            int startR = r - s + k;
            int startC = c - s + k;
            int endR = r + s - k;
            int endC = c + s - k;

            int tmp = copyMap[startR][startC];
            // 왼쪽부터 위로 진행하는 걸 생각
            for(int i=startR;i<endR;i++) {
                copyMap[i][startC] = copyMap[i+1][startC];
            }
            // 아래 왼쪽 이동
            for(int j=startC;j<endC;j++) {
                copyMap[endR][j] = copyMap[endR][j+1];
            }
            // 오른쪽 아래 이동
            for(int i=endR;i>startR;i--) {
                copyMap[i][endC] = copyMap[i-1][endC];
            }
            // 위 오른쪽 이동
            for(int j=endC;j>startC;j--) {
                copyMap[startR][j] = copyMap[startR][j-1];
            }
            copyMap[startR][startC+1] = tmp;
            // 지도 확인
//            System.out.println();
//            printMap();
        }
    }

    public static int calculateRow() {
        int cnt = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++) {
            int check = 0;
            for(int j=1;j<=M;j++) {
                check += copyMap[i][j];
            }
            cnt = Math.min(cnt, check);
        }
        return cnt;
    }

    public static void printMap() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                System.out.print(copyMap[i][j]+" ");
            }
            System.out.println();
        }
    }
}
// 3 4 2 -> (3-2, 4-2) (3+2, 4+2) = (1,2) 부터 (5,6)
// 4 2 1 -> (4-1, 2-1) (4+1, 2+1) = (3,1) (5,3)
// 회전 갯수는 (5-1) / 2
// (5-3)/2
