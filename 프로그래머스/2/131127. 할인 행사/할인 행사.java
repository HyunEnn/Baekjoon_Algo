
import java.util.*;

class Solution {
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<want.length;i++) {
          map.put(want[i], map.getOrDefault(want[i], 0) + number[i]);
        }

        for(int i=0;i<=discount.length-10;i++) {
          HashMap<String, Integer> temp = new HashMap<>();
          for(int j=i;j<i+10;j++) {
            temp.put(discount[j], temp.getOrDefault(discount[j], 0) + 1);
          }
          if(map.equals(temp)) {
            answer++;
          }
        }
        
      return answer;
    }
}