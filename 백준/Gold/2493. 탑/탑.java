
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] ans;
    static int[] tops;
    static Stack<Integer> stack = new Stack<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tops = new int[N+1];
        ans = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=N;i>=1;i--) {
            while(!stack.isEmpty() && tops[i] > tops[stack.peek()]) {
                ans[stack.pop()] = i;
            }
            stack.push(i);
        }

        for(int i=1;i<=N;i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
