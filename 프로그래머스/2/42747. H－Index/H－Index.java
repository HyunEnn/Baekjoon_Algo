import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int len = citations.length;
        for(int i=0;i<len;i++) {
            if(citations[i] > len - 1 - i) {
                answer = len-i;
                break;
            }
        }
        
        return answer;
    }
    // 정렬하고, 가운데 값을 기준으로 탐색, 그리고 citations[idx] 값을 기준으로 인용된 값이 idx보다 많으면 
}