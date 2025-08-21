
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, Q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int[][] tmp;
    static int size;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        map = new int[size][size];
        tmp = new int[size][size];

        for(int i=0;i<size;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<size;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int q=0;q<Q;q++) {
            int s = Integer.parseInt(st.nextToken());
            divide(s);
            meltGlacier();

//            printMap();
        }

        System.out.println(countMap());
        System.out.println(findGlacier());
    }

    public static int findGlacier() {
        boolean[][] v = new boolean[size][size];
        int ans = 0;

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                Queue<Point> Q = new ArrayDeque<>();
                int cnt = 0;
                if(!v[i][j] && map[i][j] > 0) {
                    v[i][j] = true;
                    Q.offer(new Point(i, j));
                    cnt = 1;
                }
                while(!Q.isEmpty()) {
                    Point p = Q.poll();
                    for(int k=0;k<4;k++) {
                        int nr = p.r + dr[k];
                        int nc = p.c + dc[k];
                        if(inRange(nr, nc) && map[nr][nc] > 0 && !v[nr][nc]) {
                            v[nr][nc] = true;
                            Q.offer(new Point(nr, nc));
                            cnt++;
                        }
                    }
                }

                ans = Math.max(ans, cnt);
            }
        }

        return ans;
    }

    public static int countMap() {
        int cnt = 0;
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                cnt += map[i][j];
            }
        }

        return cnt;
    }

    public static void printMap() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void meltGlacier() {
        tmp = deepCopy();

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                int cnt = 0;
                for(int k=0;k<4;k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(inRange(nr, nc) && map[nr][nc] > 0) cnt++;
                }
                if(cnt < 3) tmp[i][j] = map[i][j] - 1;
                if(tmp[i][j] < 0) tmp[i][j] = 0;
            }
        }

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    public static void divide(int q) {
        int len = (int) Math.pow(2, q); // 격자의 최대 길이

        for(int i=0;i<size;i+=len) {
            for(int j=0;j<size;j+=len) {
                rotateBlock(i, j, len);
            }
        }

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    public static void rotateBlock(int r, int c, int len) {
        // 조건대로, len 에서 i를 뺀 열이 i 행이 되어야한다?
        Queue<Integer> Q = new ArrayDeque<>();

        for(int i=r;i<r+len;i++) {
            for(int j=c;j<c+len;j++) {
                Q.offer(map[i][j]);
            }
        }

        for(int j=c+len-1;j>=c;j--) {
            for(int i=r;i<r+len;i++) {
                tmp[i][j] = Q.poll();
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    public static int[][] deepCopy() {
        int[][] temp = new int[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }
    /**
     * 1. 격자를 나눈다. Math.pow(2, i)
     * 2. cnt - j의 열의 값들이 , i 행의 값들로 대체되야한다. ( 90도 회전한 값 )
     * 3. 주변에 0 초과가 3개 이상이 안될 경우 현재 자리 -1
     */
}
