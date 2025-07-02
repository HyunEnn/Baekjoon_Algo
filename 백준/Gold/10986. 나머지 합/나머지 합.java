import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static long[] prefix;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        prefix = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        // 나머지 카운팅
        int[] ch = new int[M];
        for (int i = 0; i <= N; i++) {
            int mod = (int)(prefix[i] % M);
            ch[mod]++;
        }

        // 정답 계산
        long answer = 0;
        for (int i = 0; i < M; i++) {
            if (ch[i] >= 2) {
                answer += (long) ch[i] * (ch[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}