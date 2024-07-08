import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        char[] chA = new char[A.length() + 1];
        for(int i=1;i<=A.length();i++) {
            chA[i] = A.charAt(i - 1);
        }
        
        String B = br.readLine();
        char[] chB = new char[B.length() + 1];
        for(int i=1;i<=B.length();i++) {
            chB[i] = B.charAt(i - 1);
        }

        int[][] dp = new int[B.length() + 1][A.length() + 1];
        for(int i=1;i<=B.length();i++) {
            for(int j=1;j<=A.length();j++) {
                if(chB[i] == chA[j])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(dp[B.length()][A.length()]);
    }
}
