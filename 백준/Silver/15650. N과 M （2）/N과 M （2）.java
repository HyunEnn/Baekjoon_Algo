
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static StringTokenizer st;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N];
        recursive(0, 0);
    }

    public static void recursive(int cnt, int idx) {

        // basis
        if(cnt == M) {
            for(int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        // inductive
        for(int i=idx;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                list.add(i+1);
                recursive(cnt + 1, i);
                list.remove(list.size() - 1);
                v[i] = false;
            }
        }
    }
}
