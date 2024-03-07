import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0, right = people.length-1;
        // int check = people[left] + people[right];
        while(left <= right) {
            if(people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            answer++;
        }
        
        return answer;
    }
}
// 50 50 70 80
// 10 20 30 40 50 , 100
