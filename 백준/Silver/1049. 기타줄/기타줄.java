
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
        int[][] arr = new int[M][2];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int check = arr[0][0];
        for(int i=0;i<M;i++) {
            // 만약에 낱개로 6개를 사는게, 세트 한 품목으로 싼 경우가 발생한다면?
            int second = arr[i][1] * 6;
            if(second < check) check = second;
        }

        // 일단, 나누어질 수 있는 경우라면
        int ans = 0;
        ans = N / 6 * check;
        if(N % 6 != 0) {
            int remain = N % 6;
            for(int i=0;i<M;i++) {
                if(arr[i][1] * remain <= check) {
                    check = arr[i][1] * remain;
                }
            }
            ans += check;
        }

        System.out.println(ans);


    }
}
