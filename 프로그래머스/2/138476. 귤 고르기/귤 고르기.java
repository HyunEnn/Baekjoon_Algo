import java.util.*;
import java.io.*;

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    public int solution(int k, int[] tangerine) {
        for(int i=0;i<tangerine.length;i++) {
            int a = tangerine[i];
            map.put(a, map.getOrDefault(a, 0) + 1);
        } // 값을 모두 hashMap에 넣은 후 정렬?
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        
        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(map.get(o2), map.get(o1));
            }
        }); // key 의 value 들을 내림차순하여 정렬하고, keySet에는 key인 1, 3, 4가 들어감
        
        // for(Integer key : keySet) {
        //     System.out.println(key + " " + map.get(key));
        // }
        int answer = 0;
        int max = k;
        for(int i=0;i<keySet.size();i++) {
            int size = map.get(keySet.get(i));
            if(max <= 0) 
                break;
            else {
                max -= size;
                answer++;
            }
        }
        return answer;
    }
}