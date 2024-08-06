import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        String[] sl = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(int i=0;i<s.length();i++) {
            // 단어를 읽으면서, 공백을 만나면 초기화
            if(s.charAt(i) == ' ') {
                idx = 0;
                sb.append(" "); // 공백을 만났으니 공백 추가
            }
            else {
                // 짝수면
                if(idx % 2 == 0) {
                    // 소문자일 경우 -32, 대문자면 그냥 그대로
                    if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') 
                        sb.append((char)(s.charAt(i) - 32)); 
                    else
                        sb.append((char)s.charAt(i));
                }
                // 홀수일 경우, 소문자
                else {
                    // 대문자일 경우 +32, 소문자일 경우 그대로
                    if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') 
                        sb.append((char)(s.charAt(i) + 32));
                    else
                        sb.append((char)s.charAt(i));
                }
                idx++;
            }
        }
        return sb.toString();
    }
}
// 대문자 97, 소문자 65, 차이 : 32