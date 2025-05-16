class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int ans_l = 0, ans_r = 0;
        int left = 0, right = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        while(right < sequence.length) {
            
            // sum 이 k 보다 작으면 right를 증가시킨다.
            if(sum < k) {
                sum += sequence[right];
                right++;
            }
            // 여기가 sum 이 k보다 크면, 하나씩 줄인다
            while(sum > k && right > left) {
                sum -= sequence[left];
                left++;
            }
            
            if(sum == k) {
                if(right - left < len) {
                    len = right - left;
                    ans_l = left;
                    ans_r = right - 1;
                }
                sum -= sequence[left];
                left++;
            }
        }
        answer[0] = ans_l;
        answer[1] = ans_r;
        return answer;
    }
}