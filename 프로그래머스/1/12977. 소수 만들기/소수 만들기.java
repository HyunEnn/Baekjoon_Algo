import java.util.*;
import java.io.*;

class Solution {
    static boolean[] v;
    static int answer;
    static List<Integer> list = new ArrayList<>();
    public int solution(int[] nums) {
        answer = 0;
        // 재귀를 통한 소수 판별
        for(int i=0;i<nums.length - 2;i++) {
            v = new boolean[nums.length];
            v[i] = true;
            recursive(1, i, i, nums);   
        }
        return answer;
    }
    
    public static void recursive(int idx, int start, int curr, int[] nums) {
        
        if(idx == 3) {
            int sum = nums[start];
            for(int i : list) {
                sum += i;
            }
            for(int i=2;i<=sum/2;i++) { // ex) 8 -> 2, 3, 4
                if(sum % i == 0) 
                    return;
            }
            answer++;
            return;
        }
        
        for(int i=curr+1;i<nums.length;i++) {
            if(!v[i]) {
                v[i] = true;
                list.add(nums[i]);
                recursive(idx + 1, start, i, nums);
                list.remove(list.size()-1);
                v[i] = false;
            }
        }
    }
}