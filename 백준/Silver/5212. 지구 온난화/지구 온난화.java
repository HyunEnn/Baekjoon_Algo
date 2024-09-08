
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] map;
    static char[][] copyMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int minR, minC, maxR, maxC;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        minR = Integer.MAX_VALUE;
        minC = Integer.MAX_VALUE;
        maxR = Integer.MIN_VALUE;
        maxC = Integer.MIN_VALUE;
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        copyMap = new char[r][c];
        map = new char[r][c];
        for(int i=0;i<r;i++) {
            String line = br.readLine();
            for(int j=0;j<c;j++) {
                map[i][j] = line.charAt(j);
                copyMap[i][j] = line.charAt(j);
            }
        }

        removeMap();
        // 삭제한 후에, 전부 .인 부분의 행들 제거와 열들 제거
        for(int i=minR;i<=maxR;i++) {
            for(int j=minC;j<=maxC;j++) {
                System.out.print(copyMap[i][j]);
            }
            System.out.println();
        }


    }

    static boolean inRange(int nr, int nc) {
        return nr >= 0 && nr < r && nc >= 0 && nc < c;
    }

    static boolean canGo(int nr, int nc) {
        if(!inRange(nr, nc))
            return false;
        if(map[nr][nc] != '.') // 바다가 아니면 false
            return false;
        return true;
    }

    static void findValue(int i, int j) {
        minR = Math.min(minR, i);
        minC = Math.min(minC, j);
        maxR = Math.max(maxR, i);
        maxC = Math.max(maxC, j);
    }

    static void removeMap() {
        // 행 0 과 r-1 부분은 무조건 한 칸은 바다가 존재하기 때문에, 2개 이상이면 .으로
        // 열 0 과 c-1 부분도 마찬가지로 처리해줘야함. 다만, 각 모서리별 처리에 대한 부분은 예외로
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(map[i][j] == 'X') {
                    int cnt = 0;
                    for(int k=0;k<4;k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(!inRange(nr, nc) || map[nr][nc] == '.')
                            cnt++;
                    }
                    if(cnt >= 3) {
                        copyMap[i][j] = '.';
                    }
                }
            }
        }

        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(copyMap[i][j] == 'X')
                    findValue(i, j);
            }
        }

    }
}
