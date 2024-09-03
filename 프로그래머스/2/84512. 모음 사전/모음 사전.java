import java.util.*;
import java.io.*;

class Solution {
    static String[] s = {"A", "E", "I", "O", "U"};
    static List<String> list = new ArrayList<>();
    public int solution(String word) {
        DFS(new StringBuilder(), 0);
        int cnt = 0;
        int answer = 0;
        for(String a : list) {
            cnt++;
            if(a.equals(word)) {
                answer = cnt;
            }
        }
        return answer;
    }
    
    public static void DFS(StringBuilder sb, int idx) {
        if(idx > 5) {
            return;
        }
        
        if(idx > 0) {
            list.add(sb.toString());
        }
        
        for(int i=0;i<s.length;i++) {
            sb.append(s[i]);
            DFS(sb, idx+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}