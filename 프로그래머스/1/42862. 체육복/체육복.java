import java.util.*;

class Solution {
    static int[] students;
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        students = new int[n+1];
        // reserve 기준으로 앞, 뒤로만 빌려줄 수 있음
        for(int i=0;i<lost.length;i++) {
            students[lost[i]] = -1;
        }
        
        Arrays.sort(reserve);
        
        for(int i=0;i<reserve.length;i++) {
            // 본인이 도난당해서, 여벌을 사용할 경우 존재
            students[reserve[i]]++;
        }
        
        for(int i=0;i<reserve.length;i++) {
            int curr = reserve[i];
            if(students[curr] == 0) continue;
            int before = reserve[i] - 1;
            int after = reserve[i] + 1;
            if(before > 0 && students[before] == -1) {
                students[curr]--;
                students[before]++;
            } else if(after <= n && students[after] == -1) {
                students[curr]--;
                students[after]++;
            }
        }
        
        for(int i=1;i<=n;i++) {
            if(students[i] >= 0) 
                answer++;
        }
        return answer;
    }
}