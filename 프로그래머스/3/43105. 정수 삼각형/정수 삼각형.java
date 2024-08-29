import java.util.*;

class Solution {
    static int[][] dp;
    public int solution(int[][] triangle) {
        int len = triangle.length;
        dp = new int[len][triangle[len-1].length];
        
        dp[0][0] = triangle[0][0];
        for(int i=1;i<triangle.length;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0)
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if(j==i)
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j],
                                        dp[i-1][j] + triangle[i][j]);
            }
        }
    
    
        int answer = 0;
        for(int i=0;i<triangle[len-1].length;i++) {
            answer = Math.max(answer, dp[len-1][i]);
        }
        return answer;
    }
}