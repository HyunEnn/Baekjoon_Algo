import java.util.*;

class Solution {
    static 
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0, right = 0;
        for(int i=0;i<sequence.length;i++) {
            sum += sequence[i];
            if(sum > k) {
                while(sum > k) {
                    sum -= sequence[left];
                    left++;
                }
            } 
            if(sum == k) {
                
                // 같을 때에는, 길이가 짧은 수열을 찾는다.
                if(res > i - left) {
                    res = i - left;
                    answer[0] = left;
                    answer[1] = i;
                }
            }
        }
        return answer;
    }
}

// 시작, 끝 인덱스를 기반으로 슬라이딩 윈도우로 이동 처리?