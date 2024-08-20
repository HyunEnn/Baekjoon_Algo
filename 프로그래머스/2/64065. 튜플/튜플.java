import java.util.*;

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    public int[] solution(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<c.length;i++) {
            if(c[i] >= 48 && c[i] <= 57) {
                flag = true;
                sb.append(c[i]);
            } else 
                if(flag) {
                    int a = Integer.parseInt(sb.toString());
                    map.put(a, map.getOrDefault(a, 0) + 1);
                    sb = new StringBuilder();
                    flag = false;
                } 
        }
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        int[] answer = new int[keys.size()];
        for(int i=0;i<keys.size();i++) {
            answer[i] = keys.get(i);
        }
        return answer;
    }
    // 48 ~ 57
}