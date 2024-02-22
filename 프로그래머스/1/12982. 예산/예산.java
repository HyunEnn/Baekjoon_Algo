import java.io.*;
import java.util.*;

class Solution {
    static int res;
    public int solution(int[] d, int budget) {
        res = 0;
        Arrays.sort(d);
        for(int i=0;i<d.length;i++) {
            if(d[i] <= budget) {
                budget -= d[i];
                res++;
            } else 
                break;
        }
        
        // dfs(d, 0, 0, budget);
        return res;
    }
    
    static void dfs(int[] d, int size, int cnt, int budget) {
        // basis
        if(cnt == d.length || size == d.length) {
            res = Math.max(res, size);
            return; 
        }
            
        // inductive
        if(d[cnt] <= budget)
            dfs(d, size+1, cnt+1, budget-d[cnt]);
        dfs(d, size, cnt+1, budget);
    }
}