import java.util.*;
import java.io.*;

class Solution {
    static int res;
    static List<Integer> answer = new ArrayList<>();
    public int solution(int[][] board, int[] moves) {
        
        res = 0;
        // 우선 배열에 담아야 함
        for(int i=0;i<moves.length;i++) {
            addAnswer(moves[i] - 1, board);
        }
        
        for(int i=0;i<answer.size();i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();
        
        // 정답 배열에서 터트리기
        int count = 0;
        int idx = 0;
        while(idx < answer.size()) {
            int startIdx = idx;
            // 사이즈 안에 있고, 중복값이라면 count 증가하고 다음 값 탐색
            while (idx + 1 < answer.size() 
                   && answer.get(idx).equals(answer.get(idx + 1))) {
                count++;
                idx++;
            }
            
            if(count != 0) {
                for(int i=0;i<=count;i++) {
                    answer.remove(idx - count);
                    res++;
                }
                count = 0;
                idx = 0;
            } else {
                idx++;
            }
        }
        
        // for(int i=0;i<answer.size();i++) {
        //     System.out.print(answer.get(i) + " ");
        // }
        // System.out.println();
        
        return res;
        // ex) 1 1 1 0 2 가 주어지면 -1 -1 -1 0 2 가 되도록 해보자
        
    }
    
    public static void addAnswer(int idx, int[][] board) {

        // idx열을 기준으로 입력받음 
        for(int i=0;i<board.length;i++) {
            if(board[i][idx] != 0) {
                answer.add(board[i][idx]); 
                board[i][idx] = 0; // 인형을 뽑았으니 자리는 비워줌
                break; // 뽑았으니까 stop
            }
        }
    }
}