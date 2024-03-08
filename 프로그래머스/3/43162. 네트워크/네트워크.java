import java.io.*;
import java.util.*;

class Solution {
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[n];
        for(int i=0;i<n;i++) {
            if(!v[i]) {
                bfs(n, computers, i);
                answer++;    
            }
        }
        
        return answer;
    }
    
    public static void bfs(int n, int[][] computers, int idx) {
        Queue<Integer> Q = new ArrayDeque<>();
        v[idx] = true;
        Q.offer(idx);
        
        while(!Q.isEmpty()) {
            int p = Q.poll();
            for(int i=0;i<n;i++) {
                if(computers[i][p] == 1 && !v[i]) {
                    v[i] = true;
                    Q.offer(i);
                }
            }
        }
    }
}