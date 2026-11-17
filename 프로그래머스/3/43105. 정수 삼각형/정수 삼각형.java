import java.util.*;

class Solution {
    static int[][] dp;
    public int solution(int[][] triangle) {
        int answer = 0;
        dp = new int[triangle.length][];
        for(int i=0;i<triangle.length;i++) {
            dp[i] = new int[triangle[i].length];
        }
        
        dp[0][0] = triangle[0][0];
        // 좌, 우를 모두 채운다.
        for(int i=1;i<triangle.length;i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][triangle[i].length-1] = dp[i-1][triangle[i-1].length-1] + triangle[i][triangle[i].length-1];
        }
        
        for(int i=2;i<triangle.length;i++) {
            for(int j=1;j<triangle[i].length-1;j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];                 }
        }
        
        for(int j=0;j<dp[dp.length-1].length;j++) {
            answer = Math.max(answer, dp[dp.length-1][j]);
        }
        return answer;
    }
}