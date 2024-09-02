import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] memo;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            memo = new int[C+1][R+1];
            sb.append(recursive(C, R) + "\n");
        }
        System.out.println(sb);
    }

    static int recursive(int C, int R) {
        if(R == 0 || R == C)
            return 1;
        if(memo[C][R] != 0)
            return memo[C][R];
        memo[C][R] = recursive(C-1, R-1) + recursive(C-1, R);
        return memo[C][R];
    }
}