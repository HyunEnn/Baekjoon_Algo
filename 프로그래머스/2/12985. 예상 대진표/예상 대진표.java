import java.util.*;
import java.io.*;

class Solution
{
    public int solution(int n, int a, int b) {
        int answer = 0;
        while (a != b) {
            a = (a + 1) / 2; // 다음 라운드로 진출 2
            b = (b + 1) / 2; // 3
            answer++;
        }
        return answer;
    }
}
// 4 5 , 6 7 -> 1, 2 -> 1
