
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        int length = N.length();
        int[] arr = new int[length];
        long[] dp = new long[length];
        for(int i=0;i<length;i++) {
            arr[i] = N.charAt(i) - '0';
        }

        if(arr[0] != 0) dp[0] = 1;
        else {
            System.out.println(0);
            return;
        }

        for(int i=1;i<length;i++) {
            // 현재 자리가 0이라면, 10, 20 인 경우에만 현존 그대로 유지
            if(arr[i] == 0) {
                if(arr[i-1] == 1 || arr[i-1] == 2) {
                    if(i == 1) dp[i] = 1;
                    else dp[i] = dp[i-2];
                // 아니라면, 애초에 불가능한 암호 0 리턴
                } else {
                    System.out.println(0);
                    return;
                }
            }

            // 현재 자리가 0이 아니라면, 우선 기존 현행값 유지에 두자릿수 기준으로 'Z' 안이라면 +1 추가
            else {
                dp[i] = dp[i-1];
                int two = arr[i-1] * 10 + arr[i];
                if(10<=two && two <= 26) {
                    if(i == 1) dp[i] += 1;
                    else dp[i] += dp[i-2];
                }
            }

            dp[i] %= 100_000_0;
        }

        System.out.println(dp[length-1]);
    }
}
