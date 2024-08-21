import java.util.*;

class Solution {
    static HashMap<String, Integer> mapA = new HashMap<>();
    static HashMap<String, Integer> mapB = new HashMap<>();
    public int solution(String str1, String str2) {
        int aLen = 0;
        int bLen = 0;
        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();
        for(int i=0;i<s1.length() - 1;i++) {
            char c1 = s1.charAt(i);
            char c2 = s1.charAt(i+1);
            if(c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                String s = String.valueOf(c1) + String.valueOf(c2);
                mapA.put(s, mapA.getOrDefault(s, 0) + 1);
                aLen++;
            }
        }
        
        for(int i=0;i<s2.length() - 1;i++) {
            char c1 = s2.charAt(i);
            char c2 = s2.charAt(i+1);
            if(c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                String s = String.valueOf(c1) + String.valueOf(c2);
                mapB.put(s, mapB.getOrDefault(s, 0) + 1);
                bLen++;
            }
        }
        
        int intersect = 0;
        List<String> keys = new ArrayList<>(mapA.keySet());
        for(int i=0;i<keys.size();i++) {
            String k = keys.get(i);
            int first = mapA.get(k);
            int second = mapB.getOrDefault(k, 0);
            intersect += Math.min(first, second);
        }
        int union = aLen + bLen - intersect;
        int answer = 0;
        if(union == 0 && intersect == 0) 
            answer = 65536;    
        else
            answer = (int) Math.floor(((double) intersect / union) * 65536);
        return answer;
    }
}