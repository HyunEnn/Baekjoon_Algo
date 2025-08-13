import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] list;
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        list = new ArrayList[N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) list[i].add(j); // 어디로 이어지는지
            }
        }

        for(int i=0;i<N;i++) {
            bfs(i);
        }

        printMap();
    }

    public static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int idx) {
        Queue<Integer> Q = new ArrayDeque<>();
        boolean[] v = new boolean[N];
        Q.offer(idx);

        while(!Q.isEmpty()) {
            int cur = Q.poll();
            for(int k : list[cur]) {
                if(!v[k]) {
                    v[k] = true;
                    Q.offer(k);
                    map[idx][k] = 1;
                }
            }
        }

//        System.out.println("차례 순서 : " + idx);
//        printMap();
    }
    /**
     * 0 -> 1, 1 -> 2, 2 -> 0
     * 0 -> [1]
     * 1 -> [2]
     * 2 -> [0]
     */
}
