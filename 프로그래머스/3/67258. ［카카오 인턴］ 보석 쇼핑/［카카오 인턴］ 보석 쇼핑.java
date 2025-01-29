import java.util.*;

class Solution {
    static HashSet<String> set = new HashSet<>();
    public int[] solution(String[] gems) {
        for(int i=0;i<gems.length;i++) {
            set.add(gems[i]);
        }
        HashMap<String, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        
        while(right < gems.length) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            right++;
            while(map.size() == set.size()) {
                // 기존 길이보다 짧다면, 교체
                if(right - left < minLen) {
                    start = left;
                    end = right;
                    minLen = right - left;
                }
                
                map.put(gems[left], map.get(gems[left]) - 1);
                if(map.get(gems[left]) <= 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }
        int[] answer = new int[2];
        answer[0] = start + 1;
        answer[1] = end;
        
        return answer;
    }
}