
import java.util.*;
import java.io.*;

public class Main {

    static boolean[] v;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        recursive(0,0);
        System.out.println(sb.toString());
    }

    public static void recursive(int curr, int idx) {
        if(idx == M) {
            for(int i : list ) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=curr;i<N;i++) {
            list.add(arr[i]);
            recursive(i+1, idx+1);
            list.remove(list.size()-1);
        }
    }

}
