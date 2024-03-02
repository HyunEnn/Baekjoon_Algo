import java.io.*;
import java.util.*;

class Solution {
    static List<Integer> resultList;
    static List<Integer> copyArr;
    public int[] solution(int[] array, int[][] commands) {
        resultList = new ArrayList<>();
        for(int i=0;i<commands.length;i++) {
            copyArr = new ArrayList<>();
            int x = commands[i][0]; // 2, 4, 1
            int y = commands[i][1]; // 5, 4, 7
            int z = commands[i][2]; // 3, 1, 3
            copyArray(array, x, y);
            // System.out.println("size : " + copyArr.size());
            Collections.sort(copyArr); // 정렬
            // System.out.println("리스트 출력 : ");
            // for(int j : copyArr) {
            //     System.out.print(j + " ");
            // }
            // System.out.println();
            resultList.add(copyArr.get(z-1));
            // System.out.println(Arrays.toString(checkArr));
        }
        
        int[] answer = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++) {
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
    
    public static void copyArray(int[] array, int x, int y) {
        for(int i=x-1;i<y;i++) {
            copyArr.add(array[i]);
        }
    }
}