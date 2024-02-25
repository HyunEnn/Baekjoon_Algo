import java.util.*;
import java.io.*;

class Solution {
    static int[] memo;
    public int solution(int n) {
        int answer = 0;
        memo = new int[n+1];
        answer = dfs(n);
        return answer;
    }
    public static int dfs(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        
        if(memo[n] != 0) {
            return memo[n];
        }
        int res = dfs(n-2) + dfs(n-1);
        res %= 1234567;
        memo[n] = res;
        return res;
    }
}