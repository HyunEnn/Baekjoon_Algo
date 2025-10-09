import java.util.*;

class Solution {
    static int[] rates = {10, 20, 30, 40};
    static List<Integer> list = new ArrayList<>();
    static int bestCnt, bestSell;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] arr = new int[emoticons.length];
        comb(arr, users, emoticons, 0);
        answer[0] = bestCnt;
        answer[1] = bestSell;
        return answer;
    }
    
    public static void comb(int[] arr, int[][] users, int[] emoticons, int idx) {
        // basis
        if(idx == emoticons.length) {
            calculate(arr, users, emoticons);
            return;
        }
        // inductive
        for(int i=0;i<rates.length;i++) {
            arr[idx] = rates[i];
            comb(arr, users, emoticons, idx + 1);
        }
    }
    // 모든 가능한 조합의 이모티콘 할인율을 구해놓고, 현재 할인율에 대해서, 처리가 불가능한 경우면 스킵하고, 처리한다?
    public static void calculate(int[] arr, int[][] users, int[] emoticons) {
        int cnt = 0;
        int sell = 0;
        for(int i=0;i<users.length;i++) {
            int curr = 0;
            int rate = users[i][0]; // 설정된 할인 비율
            // 배열이 현재 이모티콘 할인율, 따라서 현재 설정한 할인율보다 높으면 구매한다.
            for(int j=0;j<arr.length;j++) {
                if(arr[j] >= rate) {
                    curr += (int) Math.round(emoticons[j] * (1.0 - (arr[j] * 0.01)));
                }
            }
            // curr 의 값이 user의 설정 값보다 높으면, cnt++
            if(curr >= users[i][1]) cnt++;
            else sell += curr;
        }
        
        // 
        if (cnt > bestCnt || (cnt == bestCnt && sell > bestSell)) {
            bestCnt = cnt;
            bestSell = sell;
        }
    }
}