
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int start , end;
    static int[] v;
    static List<Integer>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

//        for(int i=1;i<=N;i++) {
//            System.out.println(list[i].size());
//            for(int j=0;j<list[i].size();j++) {
//                System.out.print(list[i].get(j) + " ");
//            }
//            System.out.println();
//        }

        v = new int[N + 1];
        for(int i=1;i<=N;i++) {
            v[i] = -1;
        }
        find();

//        for(int i=1;i<=N;i++) {
//            System.out.print(v[i] + " ");
//        }
        System.out.println(v[end]);
    }

    public static void find() {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(start);
        v[start] = 0;
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            for(int i=0;i<list[cur].size();i++) {
                int next = list[cur].get(i);
                if(v[next] == -1) {
                    v[next] = v[cur] + 1;
                    Q.offer(next);
                }
            }
        }
    }
}
