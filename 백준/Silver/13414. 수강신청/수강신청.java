
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(int i=0;i<M;i++) {
            String now = br.readLine();
            set.remove(now);
            set.add(now);
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(String i : set) {
            sb.append(i).append("\n");
            cnt++;
            if(cnt == N) break;
        }

        System.out.println(sb.toString());

    }
}
