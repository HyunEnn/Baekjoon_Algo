import java.util.*;

class Solution {
    static int[] rate = {40, 30, 20, 10};
    static List<Integer> list = new ArrayList<>();
    static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        // 할인은, 10,20,30,40 프로로 조합을 고려해야함.
        recursive(0, emoticons.length, emoticons, users);
        return answer;
    }
    
    public static void recursive(int idx, int n, int[] emoticons, int[][] users) {
        // basis
        if(list.size() == n) {
            // 가능한 조합들 기준에서, 구매할 수 있는 최대값 저장?
            // 예시로, 1번 유저는 40% 이상, 2번은 25% 이상 할인하는걸 모두 구매한다.
            
            int[] tmp = solve(users, emoticons);
            // 여기서, 계산된 구독자 수가 더 많으면 전체 변경
            if(tmp[0] > answer[0]) {
                answer = tmp;
            }
            // 구독자 수가 동일하다면, 합산값 비교
            else if(tmp[0] == answer[0]) {
                answer[1] = Math.max(answer[1], tmp[1]);
            }
            
            return;
        }
        // inductive , 처음부터 필요한 조합만 한다?
        for(int i=0;i<4;i++) {
            list.add(rate[i]);
            recursive(idx + 1, n, emoticons, users);
            list.remove(list.size() - 1);
        }
    }
    
    public static int[] solve(int[][] users, int[] emoticons) {
        int[] res = new int[2];
        // 유저별 순서대로 최대값을 구해본다. 어차피, 이모티콘 구독이 최우선임.
        int[] ans = new int[users.length];
        for(int i=0;i<users.length;i++) {
            int sum = 0;
            // 모든 품목을 다 탐색하면서 최대값 구하기
            for(int j=0;j<list.size();j++) {
                int curr = list.get(j); // 현재 적용 할인율
                if(curr >= users[i][0]) { // 할인율이 유저가 설정한 할인율 이상이면 다 구매
                    sum += emoticons[j] * (100 - curr) / 100; // 할인된 가격 합산    
                } 
            }
            ans[i] = sum;
        }
        
        // 여기서, 이모티콘 구독 가능한 사람과 그 외에 판매액을 합산하자.
        for(int i=0;i<ans.length;i++) {
            if(ans[i] >= users[i][1]) res[0]++;
            else res[1] += ans[i];
        }
        
        // System.out.println(res[0] + " " + res[1]);
        
        return res;
    }
}

/*
 1. 각 유저는 [할인율, 한도] 으로 총 구매한 가격이 한도를 넘어서면 다 취소하고
 이모티콘 플러스 구독으로 전환
 2. 만일, 한도 내에 구매했다면 result에서 [이모티콘 구독자 수, 합계]로 리턴한다.
*/