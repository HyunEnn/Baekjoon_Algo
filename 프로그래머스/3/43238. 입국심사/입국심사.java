import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        Arrays.sort(times);
        long end = (long) n * times[times.length - 1];
        long start = 1;
        
        while(start <= end) {
            long mid = (start + end) / 2;
            long check = solve(mid, times);
            if(check >= n) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            } 
        }
        return answer;
    }
    
    private static long solve(long mid, int[] times) {
        long cnt = 0;
        for(int i=0;i<times.length;i++) {
            cnt += mid / times[i];
        }
        return cnt;
    }
}