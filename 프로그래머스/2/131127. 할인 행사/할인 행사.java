import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int totalLen = 0;
        for(int i=0;i<number.length;i++) {
            totalLen += number[i];
        }
        // 중복된 값이 올 일은 없으니, getOrDefault에 대한 고려는 필요 x
        System.out.println(totalLen);
        for(int i=0;i<=discount.length - totalLen;i++) {
            // System.out.println(i + "회차");
            for(int a=0;a<want.length;a++)
                map.put(want[a], number[a]);
            for(int j=i;j<i + totalLen;j++) {
                for(int k=0;k<want.length;k++) {
                    if(discount[j].equals(want[k])) {
                        map.put(want[k], map.get(want[k]) - 1);
                    }
                }
            }
            // 여기서 map의 값들이 전부 0 이하면 answer에 값 추가
            boolean flag = true;
            for(String key : map.keySet()) {
                // System.out.println(key + " " + map.get(key));
                if(map.get(key) > 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}