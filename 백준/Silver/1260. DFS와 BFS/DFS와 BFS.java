
import java.io.*;
import java.util.*;
public class Main {

    static List<Integer> dfsList;
    static List<Integer> bfsList;
    static boolean[] v;
    static int N, M, K;
    static StringTokenizer st;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        v = new boolean[N+1];
        list = new ArrayList[N+1];

        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        for(int i=1;i<=N;i++) {
            Collections.sort(list[i]);
        }

        dfsList = new ArrayList<>();
        bfsList = new ArrayList<>();
        dfsList.add(K);
        dfs(K);
        for(int x : dfsList) {
            System.out.print(x + " ");
        }
        System.out.println();
        v = new boolean[N+1];
        bfs(K);
        for(int y : bfsList) {
            System.out.print(y + " ");
        }
    }
    static void dfs(int idx) {
        v[idx] = true;
        for(int i=0;i<list[idx].size();i++) {
            if(!v[list[idx].get(i)]) {
                dfsList.add(list[idx].get(i));
                dfs(list[idx].get(i));
            }
        }
    }

    static void bfs(int idx) {
        Queue<Integer> Q = new ArrayDeque<>();
        v[idx] = true;
        bfsList.add(idx);
        Q.offer(idx);
        while(!Q.isEmpty()) {
            int x = Q.poll();
            for(int i=0;i<list[x].size();i++) {
                int next = list[x].get(i);
                if(!v[next]) {
                    v[next] = true;
                    bfsList.add(next);
                    Q.offer(next);
                }
            }
        }
    }

}
