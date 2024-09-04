import java.util.*;

class Solution {
    static HashMap<Integer, Integer> map1 = new HashMap<>();
    static HashMap<Integer, Integer> map2 = new HashMap<>();
    public int solution(int[] topping) {
        int len = topping.length;
        int answer = 0;
        for(int i=0;i<len;i++) {
            map2.put(topping[i], map2.getOrDefault(topping[i], 0) + 1);
        }
        int pointer = 0;
        while(pointer < len - 1) {
            map1.put(topping[pointer], map1.getOrDefault(topping[pointer], 0) + 1);
            // map2가 비어버리면 더 이상 돌 필요 없이 멈춤
            if(map2.isEmpty()) break;
            // 여기서 현재 포인터 값은 삭제
            
            // System.out.println(map2.size());
        
            for(int i=0;i<map2.size();i++) {
                if(map2.get(topping[pointer]) != 0) { // 값이 있다면 -1
                    map2.put(topping[pointer], map2.get(topping[pointer]) - 1);
                    if(map2.get(topping[pointer]) == 0) 
                        map2.remove(topping[pointer]);
                    break;
                }
            }
            
            // 이제, map1과 map2의 size 비교
            if(map1.size() == map2.size())
                answer++;
            // 다 끝나고 다음 포인터 추적
            pointer++;
        }
        return answer;
    }
}