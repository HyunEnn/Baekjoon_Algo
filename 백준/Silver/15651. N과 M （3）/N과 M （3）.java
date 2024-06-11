
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        recursive(0);
        System.out.println(sb.toString());
    }

    public static void recursive(int cnt) {

        if(cnt == M) {
            for(int i : list) {
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<N;i++) {
            list.add(i+1);
            recursive(cnt + 1);
            list.remove(list.size()-1);
        }
    }
}
