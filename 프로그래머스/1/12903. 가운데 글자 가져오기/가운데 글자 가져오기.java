// 4 / 2 = 2 (1, 2) , 5 / 2 = 2 
// 6 / 2 = 3 (2, 3)
class Solution {
    public String solution(String s) {
        String answer = "";
        char[] c = s.toCharArray();
        if(c.length % 2 == 0) {
            answer += c[c.length/2-1];
            answer += c[c.length/2];
        } else {
            answer += c[c.length / 2];
            System.out.println(answer);
        }
        return answer;
    }
}