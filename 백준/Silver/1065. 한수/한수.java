
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 0;
        if(N < 100) {
            System.out.println(N);
            return;
        }
        else {
            ans = 99;
            for(int i=101;i<=N;i++) {
                String s = String.valueOf(i);
                int[] arr = new int[s.length()];
                for(int j=0;j<s.length();j++) {
                    arr[j] = s.charAt(j) - '0';
                }

                int first = arr[1] - arr[0];
                int second = arr[2] - arr[1];
                if(first == second) ans++;
            }

        }

        System.out.println(ans);
        // 101 -> [1, 0, 1]
    }
}
