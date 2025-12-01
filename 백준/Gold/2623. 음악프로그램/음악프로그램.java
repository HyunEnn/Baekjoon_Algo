
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
        list = new ArrayList[N + 1];
        degree = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++){
            list[i] = new ArrayList();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int[] points = new int[k+1];
            for(int j=0;j<k;j++) points[j] = Integer.parseInt(st.nextToken());

            for(int j=0;j<k-1;j++) {
                int a = points[j];
                int b = points[j+1];
                list[a].add(b);
                degree[b]++;
            }
        }

        Queue<Integer> Q = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            if(degree[i] == 0) Q.offer(i);
        }

        int cnt = 0;
        while(!Q.isEmpty()) {
            int p = Q.poll();
            cnt++;
            for(int np : list[p]) {
                degree[np]--;
                if(degree[np] == 0) Q.offer(np);
            }
            sb.append(p).append("\n");
        }

        if(cnt != N) System.out.println(0);
        else System.out.println(sb.toString());
    }
}
