class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left;i<=right;i++) {
            int idx = i;
            int cnt = 1;
            for(int j=1;j<=idx/2;j++) {
                if(idx % j == 0) cnt++;
            }
            if(cnt % 2 == 0) answer += idx;
            else if(cnt % 2 != 0)answer -= idx;
        }
        return answer;
    }
}