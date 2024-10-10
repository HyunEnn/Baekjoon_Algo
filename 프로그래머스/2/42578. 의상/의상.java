import java.util.*;
import java.io.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    static int max = 0;
    static boolean[] v;
    public int solution(String[][] clothes) {
        int answer = 1;
        for(int i=0;i<clothes.length;i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        List<String> list = new ArrayList<>(map.keySet());
    
        int sum = 1;
        for(int i : map.values()) {
            answer *= (i + 1);
        }
        
    
        return answer - 1;
    }
    
    
}