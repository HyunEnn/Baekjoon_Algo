
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static List<Integer>[] list;
    static int[] degree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N+1];
        list = new ArrayList[N+1];
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            degree[b]++;
        }

        Queue<Integer> Q = new ArrayDeque<>();

        for(int i=1;i<=N;i++) {
            if(degree[i] == 0) Q.offer(i);
        }

        while(!Q.isEmpty()) {
            int p = Q.poll();
            sb.append(p).append(" ");
            for(int np : list[p]) {
                degree[np]--;
                if(degree[np] == 0) Q.offer(np);
            }
        }

        System.out.println(sb.toString());
    }
}
