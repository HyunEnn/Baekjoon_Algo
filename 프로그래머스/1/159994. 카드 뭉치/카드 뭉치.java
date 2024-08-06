class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // 순서도 생각해야 함, 순서가 안맞으면 No 따라서 각자의 idx가 필요할듯?
        int card1_idx = 0;
        int card2_idx = 0;
        for(int i=0;i<goal.length;i++) {
            // 여기서, card1 과 card2 의 greedy 탐색
            if(goal[i].equals(cards1[card1_idx])) {
                if(cards1.length-1 > card1_idx)
                    card1_idx++;
            }
            else if(goal[i].equals(cards2[card2_idx])) {
                if(cards2.length-1 > card2_idx)
                    card2_idx++;
            }
            else
                return "No";
        }
        return "Yes";
    }
}