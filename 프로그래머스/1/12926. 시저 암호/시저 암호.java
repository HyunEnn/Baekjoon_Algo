class Solution {
    public String solution(String s, int n) {
        String answer = "";
        char[] c = s.toCharArray();
        for(char i : c) {
            if(i == ' ')
                answer += " ";
            else {
                if(i >= 'a' && i <= 'z') {
                    i += n;
                    System.out.println("i : " + i);
                    if(i > 122) i -= 26;
                    System.out.println("소문자 : "+ i);
                
                } else if(i >= 'A' && i <= 'Z') {
                    i += n;
                    System.out.println("i : "+ (int)i);
                    if(i > 90) i -= 26;
                    System.out.println("대문자 : " + i);
                }
                answer += i;
            }
            
        }
        return answer;
    }
}