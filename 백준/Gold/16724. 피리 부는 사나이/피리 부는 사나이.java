
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;
    static char[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        arr = new int[N * M];
        Arrays.fill(arr, -1);
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                int dir = -1;
                if(map[i][j] == 'U') dir = 0;
                else if(map[i][j] == 'R') dir = 1;
                else if(map[i][j] == 'D') dir = 2;
                else if(map[i][j] == 'L') dir = 3;

                int r = i + dr[dir];
                int c = j + dc[dir];
                uni(M * i + j, M * r + c);
            }
        }

        long ans = 0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == -1) ans++;
        }
        System.out.println(ans);
    }

    private static int find(int x) {
        if(arr[x] < 0) return x;
        return arr[x] = find(arr[x]);
    }

    private static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        arr[v] = u;
        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}

/**
 * union - find 로 현재 자리에서 방향에 대한 이동을 따라 union(u, v)를 통해서, 뿌리를 저장한다.
 * 배열은 일렬로 세운다는 가정하에 N*M 으로 배열의 갯수를 만든다.
 */
