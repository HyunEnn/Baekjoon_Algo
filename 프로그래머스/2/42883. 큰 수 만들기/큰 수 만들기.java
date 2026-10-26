import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int ch = 0;
        for(int i=0;i<number.length() - k;i++) {
            int max = 0;
            for(int j=ch;j<=k + i;j++) {
                int num = number.charAt(j) - '0';
                if(num > max) {
                    max = num; ch = j + 1;
                }
            }
            answer += max;
        }
        return answer;
    }
}