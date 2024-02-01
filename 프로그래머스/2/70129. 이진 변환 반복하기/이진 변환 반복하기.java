import java.io.*;
import java.util.*;

class Solution {
    static int rmZero;
    public int[] solution(String s) {
       int[] answer = new int[2];
        rmZero = 0;
        while(s.length() > 1) {
            int cntOne = 0;
            for(int i=0;i<s.length();i++) {
                if(s.charAt(i) == '0') rmZero++;
                else cntOne++;
            }
            s = Integer.toBinaryString(cntOne);
            answer[0]++;
            answer[1] = rmZero;
        }
        return answer;
    }
}