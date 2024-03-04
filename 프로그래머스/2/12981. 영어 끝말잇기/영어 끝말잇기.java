import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        List<String> checkWord = new ArrayList<>();
        String startWord = words[0];
        // System.out.println(words[0].charAt(words[0].length()-1));
        int start = 2;
        char startKey = words[0].charAt(words[0].length()-1);
        checkWord.add(words[0]);
        // System.out.println(checkWord.get(0));
        int cnt = 1;
        
        for(int i=1;i<words.length;i++) {
            if(start > n) {
                cnt++;
                start = 1;
            } // 차례 증가, 번호는 1로 초기화
            
            for(String s : checkWord) {
                if(s.equals(words[i])) {
                    answer[0] = start;
                    answer[1] = cnt;
                    return answer;
                }
            }
            
            if(words[i].charAt(0)==startKey) // words의 첫번째 인자가 끝말잇기랑 같으면 값을 바꾼다.
                startKey = words[i].charAt(words[i].length()-1);
            else {
                // if(i != words.length - 1) {
                //     if(start + 1 > n) {
                //         start = 1;
                //         cnt++;
                //     } else {
                //         start++;
                //     }
                // }
                answer[0] = start;
                answer[1] = cnt;
                return answer;    
            }
            
            start++;
            checkWord.add(words[i]);
        }

        return answer;
    }
}