class Solution {
    static int[] memo;
    public int[] solution(String s) {
        
        memo = new int[26]; // a~z 는 26개
        char[] c = s.toCharArray();
        int[] answer = new int[c.length];
        for(int i=0;i<memo.length;i++) {
            memo[i] = -1;
        } // 전부 -1로 초기화
        
        for(int i=0;i<c.length;i++) {
            int ch = c[i] - 97; // a의 아스키 코드 97
            if(memo[ch] == -1) { // -1이면, 아직 값이 없는 상태니까, 0으로 변환하고 answer는 -1 삽입
                answer[i] = -1;
                memo[ch] = 0;
            }
            else {
                answer[i] = memo[ch];
                memo[ch] = 0; 
            }
            for(int j=0;j<memo.length;j++) {
                if(memo[j] != -1) 
                    memo[j]++;
            }
        }
        return answer;
    }
}