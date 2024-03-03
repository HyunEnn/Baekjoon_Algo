import java.util.*;

class Solution {
    static List<Integer> list;
    public int[] solution(int[] numbers) {
        list = new ArrayList<>();
        Arrays.sort(numbers);
        for(int i=0;i<numbers.length-1;i++) {
            for(int j=i+1;j<numbers.length;j++) {
                int sum = numbers[i] + numbers[j];
                if(!list.contains(sum)) {
                    list.add(sum);
                }
            }
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0;i<answer.length;i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}