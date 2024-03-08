import java.io.*;
import java.util.*;

class Solution {
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[n];
        for(int i=0;i<n;i++) {
            if(!v[i]) {
                dfs(n, computers, i);
                answer++;
            }
        }
        return answer;
    }
    
    public static void dfs(int n, int[][] computers, int idx) {
        v[idx] = true;
        for(int i=0;i<n;i++) {
            if(computers[i][idx] == 1 && !v[i]) {
                dfs(n, computers, i);
            }
        }
    }
}