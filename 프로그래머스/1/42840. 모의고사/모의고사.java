import java.util.*;
import java.io.*;

class Solution {
    static int[] q1 = {1, 2, 3, 4, 5};
    static int[] q2 = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] q3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    static int[] answer;
    public int[] solution(int[] answers) {
        answer = new int[3];
        for(int i=0;i<answers.length;i++) {
            checkAnswer(i,answers[i], q1, 0);
            checkAnswer(i,answers[i], q2, 1);
            checkAnswer(i,answers[i], q3, 2);
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int max = answer[0];
        for(int i=1;i<answer.length;i++) {
            if(answer[i] > max) {
                max = answer[i];
                list.remove(0);
                list.add(i+1);
            } else if(answer[i] == max) {
                list.add(i+1);
            }
        }
        int[] result = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            result[i] = list.get(i);
        }
        return result; 
    }
    
    public static void checkAnswer(int idx, int ans, int[] person, int locate) {
        idx = idx % person.length; // 1번 기준 6이면 1부터 시작
        if(ans == person[idx]) {
            answer[locate]++;
        }
    } 
}