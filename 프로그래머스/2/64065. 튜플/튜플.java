import java.util.*;

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    public int[] solution(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<c.length;i++) {
            if(c[i] >= '0' && c[i] <= '9') {
                flag = true;
                sb.append(c[i]);
            } else if(flag) {
                int a = Integer.parseInt(sb.toString());
                map.put(a, map.getOrDefault(a, 0) + 1);
                // 값을 넣고 초기화
                flag = false; 
                sb = new StringBuilder();
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        int[] answer = new int[map.size()];
        for(int i=0;i<list.size();i++) {
            answer[i] = list.get(i).getKey();
        }
        return answer;
    }
}