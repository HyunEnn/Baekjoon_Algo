import java.io.*;
import java.util.*;

class Solution {
    static int N, M; // N : 가로, M : 세로
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
         for (int N = 1; N <= yellow; N++) {
            if (yellow % N == 0) {
                int M = yellow / N;
                int brownCount = 2 * (M + N + 2);

                if (brownCount == brown) {
                    answer[0] = M + 2;
                    answer[1] = N + 2;
                    return answer;
                }
            }
        }

        return answer;
    }
}

/*
M = 1;
        // System.out.println((int)Math.pow(2, 2));
        if(yellow > 0) {
            while(yellow >= (int)Math.pow(M, 2)) {
                M += 1;
            } // 가능한 정사각형 세로 크기
            M -= 1;
        }
        
        else {
            answer[0] = 0;
            answer[1] = 0;
            return answer;
        }
        
        N = yellow / M;
        M += 2; N += 2;
        answer[0] = N;
        answer[1] = M;
        return answer;
        
*/