import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;

        while(n > 1) {
            if(n % 2 != 0) {
                ans++;
            }
            n /= 2;
        }

        return ans;
    }
}
// 5/2에서 나머지 1 -> 2 , 2/2 
// 6/2 = 3/2 = 1 --> 2
// 5000/2 = 2500/2 = 1250/2 = 625/2 에서 나머지가 1이 존재하니까 ++ -> 2
// 312/2 = 156/2 = 78/2 = 39/2 에서 나머지 1 -> 3
// 19/2 = 9 에서 나머지 1 -> 4
// 9/2 = 4 에서 나머지 1 -> 5
// 4/2 = 2/2 = 1