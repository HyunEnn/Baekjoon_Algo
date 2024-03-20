import java.util.*;

class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(0, 0, target, numbers);
        return answer;
    }
    
    public static void dfs(int idx, int sum, int target, int[] numbers) {
        if(idx == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(idx+1, sum+numbers[idx], target, numbers);
        dfs(idx+1, sum-numbers[idx], target, numbers);
        
        // if(!v[idx][0]) {
        //     v[idx][0] = true;
        //     sum += numbers[idx];
        //     dfs(idx+1, target, move+1, numbers, sum);
        //     v[idx][0] = false;
        // }
        // if(!v[idx][1]) {
        //     v[idx][1] = true;
        //     sum -= numbers[idx];
        //     dfs(idx+1, target, move+1, numbers, sum);
        //     v[idx][1] = false;
        // }
    }
}