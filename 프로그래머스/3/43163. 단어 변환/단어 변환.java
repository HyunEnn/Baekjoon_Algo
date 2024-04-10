import java.io.*;
import java.util.*;

class Solution {
    static int answer;
    static boolean[] v;
    static boolean flag;
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        flag = false;
        v = new boolean[words.length];
        for(int i=0;i<words.length;i++) {
            if(changeWord(begin, words[i]) == 1) {
                v[i] = true;
                bfs(i, begin, target, words);   
            }
        }
        if(!flag) answer = 0;
        return answer;
    }
    
    public static void bfs(int idx, String begin, String target, String[] words) {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(idx);
        
        while(!Q.isEmpty()) {
            int size = Q.size();
            for(int j=0;j<size;j++) {
                int p = Q.poll();
                if(words[p].equals(target)) {
                    flag = true;
                    break;
                }
                    
                for(int i=0;i<words.length;i++) {
                   if(!v[i] && changeWord(words[p], words[i]) == 1) {
                        v[i] = true;
                        Q.offer(i);
                    }
                }
            }           
            // for(int x : Q) {
            //     System.out.print(x + " ");
            // } System.out.println();
            answer++;
        }
    }
    
    public static int changeWord(String begin, String word) {
        int cnt = 0;
        for(int i=0;i<begin.length();i++) {
            if(begin.charAt(i) != word.charAt(i)) 
                cnt++;
        }
        return cnt;
    }
}