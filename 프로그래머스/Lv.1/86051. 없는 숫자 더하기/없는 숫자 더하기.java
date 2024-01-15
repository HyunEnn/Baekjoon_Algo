import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        boolean[] check = new boolean[10];
        int answer = 0;
        Arrays.sort(numbers); // 오름차순 정렬
        for(int i=0;i<numbers.length;i++) {
            check[numbers[i]] = true;
        }
        for(int i=0;i<check.length;i++) {
            if(!check[i]) {
                answer += i;
            }
        }
        return answer;
    }
}