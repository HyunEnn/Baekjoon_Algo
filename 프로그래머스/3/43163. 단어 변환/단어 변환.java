class Solution {
    static boolean[] v;
    static int answer;
    // 1. compareWord에서 begin, words[i]를 비교한다.
    // 2. 비교한 값이 1 이하면 교체를 하는데,
    // 3. 방문 배열 처리 후 dfs 접근
    // 4. dfs에서 
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        v = new boolean[words.length];
        for(int i=0;i<words.length;i++) {
            if(compareWord(begin, words[i]) <= 1) {
                v[i] = true;
                dfs(i, 1, target, words);
            }
        }
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
        
    }
    
    public static void dfs(int idx, int cnt, String target, String[] words) {
        // basis
        if(target.equals(words[idx])) {
            answer = Math.min(answer, cnt);
            return;
        }
        // inductive  i=5 
        for(int i=0;i<words.length;i++) {
            if(!v[i] && compareWord(words[idx], words[i]) == 1) {
                v[i] = true;
                dfs(i, cnt + 1, target, words);
                v[i] = false;    
            }
        }
    }
    
    public static int compareWord(String begin, String comp) {
        int cnt = 0;
        for(int i=0;i<begin.length();i++) {
            if(begin.charAt(i) != comp.charAt(i)) 
                cnt++;
        }
        return cnt;
    }
}
// 