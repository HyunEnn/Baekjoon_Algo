import java.io.*;
import java.util.*;

public class Main {
    static boolean[] v;
    static ArrayList<Integer>[] list;
    static int N, M;
    static StringTokenizer st;
    static boolean flag;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        flag = false;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N];
        list = new ArrayList[N];
        for(int i=0;i<N;i++) {
            list[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for(int i=0;i<N;i++) {
            dfs(i, 1);
            if(flag) break;
        }

        if(flag)
            System.out.println(1);
        else
            System.out.println(0);
    }

    public static void dfs(int idx, int depth) {
        if(depth == 5) {
            flag = true;
            return;
        }

        v[idx] = true;
        for(int i=0;i<list[idx].size();i++) {
            int next = list[idx].get(i);
            if(!v[next])
                dfs(next, depth+1);
        }
        v[idx] = false;
    }
}
