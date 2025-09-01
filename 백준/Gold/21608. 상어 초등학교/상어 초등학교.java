
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
    }
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static List<Integer>[] students;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        students = new List[N*N+1];
        int max = (int)Math.pow(N, 2);
        for(int i=1;i<=max;i++) {
            students[i] = new ArrayList<>();
        }

        for(int i=0;i<max;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            for(int j=0;j<4;j++) students[start].add(Integer.parseInt(st.nextToken()));
            fillMap(start);
//            printMap();
//            System.out.println();
        }

        System.out.println(examineMap());
    }

    public static int examineMap() {
        int ans = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                int likes = 0;
                int now = map[i][j];
                for(int k=0;k<4;k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    // 범위 안에 있고, 그 자리가 좋아하는 학생이면 likes 증가
                    if(inRange(nr, nc)) {
                        for(int l : students[now]) {
                            if(l == map[nr][nc]) {
                                likes++;
                                break;
                            }
                        }
                    }
                }

                if(likes == 1) ans++;
                else if(likes >= 2) ans += (int) Math.pow(10, likes-1);
            }
        }

        return ans;
    }

    public static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fillMap(int start) {
        /**
         * 1번 검사 -> 좋아하는 학생이 인접한 칸에 몇 명 있는지 likes<Point> 에 대입한다.
         * 만약에, list 의 0번 인덱스보다 큰 값이면 초기화하고 새로 넣는다.
         * 그게 아니라면 pass, 검사를 마치고 인덱스가 2개 이상이라면 2번 검사로 진행
         */

        List<Point> likes = new ArrayList<>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                int ch = 0;
                // 값이 이미 있다면 패스
                if(map[i][j] != 0) continue;
                // 빈 자리라면, 4방 탐색을 진행해서 좋아하는 학생들이 있는지 체크한다.
                for(int k=0;k<4;k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(inRange(nr, nc)) {
                        // 지도 범위 안에 있고, 그 자리가 좋아하는 학생이라면 + 1
                        for(int tmp : students[start]) {
                            if(map[nr][nc] == tmp) {
                                ch++;
                                break;
                            }
                        }
                    }
                }
                if(likes.isEmpty()) likes.add(new Point(i, j, ch));
                else {
                    Point p = likes.get(0);
                    if(p.cnt > ch) continue;
                    else if(p.cnt == ch) likes.add(new Point(i, j, ch));
                    else {
                        likes = new ArrayList<>();
                        likes.add(new Point(i, j, ch));
                    }
                }
            }
        }

//        System.out.println("likes size : " + likes.size());
//        for(Point p : likes) {
//            System.out.print(p.r + " " + p.c + " " + p.cnt + " ");
//        }
//        System.out.println();

        if(likes.size() == 1) {
            Point p = likes.get(0);
            map[p.r][p.c] = start;
            return;
        }
        // 2번 검사
        List<Point> empty = new ArrayList<>();
        for(Point p : likes) {
            int emptyCnt = 0;
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위 안에 존재하고, 빈칸이면 체크
                if(inRange(nr, nc) && map[nr][nc] == 0) {
                    emptyCnt++;
                }
            }
            empty.add(new Point(p.r, p.c, emptyCnt));
        }
        // 3번 검사
        empty.sort((a, b) -> {
            // cnt 를 내림차순, cnt 가 같으면 행 오름차순, 행도 같으면 열 오름차순
            if(a.cnt == b.cnt) {
                if(a.r == b.r)
                    return a.c - b.c;
                return a.r - b.r;
            }
            return b.cnt - a.cnt;
        });
        // 정렬을 하고, 가장 처음값을 뽑아서 지도에 저장한다.
        Point p = empty.get(0);
        map[p.r][p.c] = start;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    /**
     * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
     * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
     * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
     *
     * 4방 탐색을 계속 진행하면서, 현재 자리에서 좋아하는 학생 수를 체크하고
     * 그래서, 만일 좋아하는 학생 수가 가장 많은 리스트가 1개라면, 바로 답을 체크하고
     * 2개 이상이라면, 그 값들을 기준으로 주변에 빈칸의 갯수를 체크한다.
     * 그리고 빈칸의 갯수가 많은 순으로 내림차순 -> 행 오름차순 -> 열 오름차순으로 뽑아서 값을 정한다.
     */

}
