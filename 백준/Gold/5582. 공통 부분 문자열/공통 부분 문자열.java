
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] dp;
    static char[] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        A = new char[line.length()+1];
        for(int i=1;i<=line.length();i++) {
            A[i] = line.charAt(i-1);
        }
        String line2 = br.readLine();
        B = new char[line2.length()+1];
        for(int i=1;i<=line2.length();i++) {
            B[i] = line2.charAt(i-1);
        }
        dp = new int[A.length][B.length];
        int n = A.length-1, m = B.length-1;


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(A[i] == B[j]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = 0;
            }
        }

//        for(int i=1;i<=n;i++) {
//            for(int j=1;j<=m;j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        int ans = 0;
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans);
    }
}
