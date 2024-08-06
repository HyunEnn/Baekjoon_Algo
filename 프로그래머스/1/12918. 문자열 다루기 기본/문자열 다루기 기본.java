class Solution {
    public boolean solution(String s) {
        // 초기 조건, s의 길이가 4나 6인지 확인
        System.out.println(s.length());
        if(s.length() == 4 || s.length() == 6) {
            for(int i=0;i<s.length();i++) {
            if((s.charAt(i) >= 97 && s.charAt(i) < 123) 
               || s.charAt(i) >= 65 && s.charAt(i) < 91)
                return false;
            }
            return true;
        }
        else 
            return false;
    }
}
// 97 + 26 = 123
// 65 + 26 = 91