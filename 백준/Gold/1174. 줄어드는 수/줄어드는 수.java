
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int N;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<10;i++) {
            dfs(i, i);
        }
        list.sort((a, b) -> Long.compare(a, b));
        if(list.size() < N) System.out.println(-1);
        else System.out.println(list.get(N-1));
    }

    //
    public static void dfs(int idx, long num) {
        list.add(num);

        for(int i=0;i<idx;i++) {
            dfs(i, num * 10 + i);
        }
    }
    // 줄어드는 수
    // 1번이 0, 10번 9
    // 10, 20, 21, 30, 31, 32, 40, 41, 42
    // 애초에, 가능한 경우의 수를 다 만들고, 정렬을 하면 된다.
    // 0 -> 0
    // 1 -> 1, 10,
    // 2 -> 2, 21, 210, 20
}
