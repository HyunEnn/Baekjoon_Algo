import java.io.*;
import java.util.*;

class Solution {
    static int change;
    static int cnt;
    public int solution(int n) {
        int answer = 0;
        int cnt = 0;

        while (n > 0) {
            int remainder = n % 3;
            n = n / 3;

            if (cnt >= 1) {
                answer *= 3;
            }

            answer += remainder;
            cnt++;
        }

        return answer;
    }
}