import java.util.*;
import java.io.*;

class Solution {
    static List<Integer> answer = new ArrayList<>(); // 발표 점수
    static List<Integer> honor = new ArrayList<>(); // 명예의 전당
    public List<Integer> solution(int k, int[] score) {
        int idx = 0;
        for(int i=0;i<score.length;i++) { // score 개수만큼 진행
            if(honor.size() == k) { // 명예의 전당이 가득 차면 
                // 제일 점수 낮은 애랑 비교하고, 그거보다 크면 0번째 인덱스 날리고 새로운 값 넣기
                // 아니면 무시하고 다음 점수 진행
                int currScore = honor.get(0);
                if(currScore < score[i]) {
                    honor.remove(0);
                    honor.add(score[i]); // 새로운 점수 추가
                }
            }
            else { // 가득 안 찼으니까, 점수 추가
                honor.add(score[i]);
            }
            // 여기서, 정렬을 통해 가장 낮은 점수를 발표 점수에 추가
            Collections.sort(honor);
            answer.add(honor.get(0));
        }
        return answer;
    }
}