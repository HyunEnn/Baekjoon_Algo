import java.util.*;

class Solution {
    static String[] ch1;
    static String[] ch2;
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n]; // 각 answer[i]는 null
        for(int i=0;i<n;i++) {
            String check1 = Integer.toBinaryString(arr1[i]);
            String check2 = Integer.toBinaryString(arr2[i]);
            
            ch1 = addZero(check1, n);
            ch2 = addZero(check2, n);
            answer[i] = ""; // 초기화
            for(int j=0;j<ch1.length;j++) {
                if(ch1[j].equals("1") || ch2[j].equals("1")) answer[i] += "#";
                else answer[i] += " ";
            }
            
        }
        return answer;
    }
    
    public static String[] addZero(String ch, int n) {
        if(ch.length() != n) {
            String zero = "";
            for(int i=0;i<n-ch.length();i++) {
                zero += "0";
            }
            ch = zero + ch;
        }
        return ch.split("");
    }
}