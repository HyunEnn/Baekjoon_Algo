import java.util.*;
import java.io.*;

class Solution {
    static List<Integer> list = new ArrayList<>();
    static int num;
    public int solution(String dartResult) {
        int answer = 0;
        // 문자열을 자를 수는 없고, 숫자가 나오면 저장해둔 값을 배열에 넣고 
        // 끝나면 끝난 값을 마지막에 추가 , 1,0이 주어지는 경우 처리?
        num = dartResult.charAt(0) - '0';
        for(int i=1;i<dartResult.length();i++) {
            char c = dartResult.charAt(i);
            if(c >= '0' && c <= '9') {
                if(c == '0' && dartResult.charAt(i-1) == '1') { // 10인 경우
                    num = 10;
                } else {
                    list.add(num);
                    num = c - '0';   
                }
            } else if(c == 'S' || c == 'D' || c == 'T') {
                powScore(c);
            } else if(c == '*' || c == '#') {
                changeScore(c);
            }
        }
        list.add(num);
        for(int i : list) {
            answer += i;
        }
        return answer;
    }
    
    public static void powScore(char c) {
        if(c == 'D')
            num = (int)Math.pow(num, 2);
        else if(c == 'T')
            num = (int)Math.pow(num, 3);
    }
    
    public static void changeScore(char c) {
        // list의 size가 0이라면 현재 num 만 2배, size가 1이상이면 size() - 1의 값과 현재 
        // num 을 2배
        if(c == '*') {
            if(list.size() == 0) 
                num *= 2;
            else if(list.size() >= 1) {
                int idx = list.size() - 1;
                list.set(idx, list.get(idx) * 2);
                num *= 2;
            }
        }
        // #은 해당 점수 마이너스
        else if(c == '#') {
            num *= -1;
        }
    }
}