import java.io.*;
import java.util.*;

class Solution {
    public String solution(String s) {
        String[] array = s.split(" ");
        int[] a = new int[array.length];
        for(int i=0;i<array.length;i++) {
            a[i] = Integer.parseInt(array[i]);
        }
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        // -4 -3 -2 -1  
        StringBuilder sb = new StringBuilder();
    
        sb.append(a[0] + " ");
        sb.append(a[a.length-1]);       
        
        return sb.toString();
    }
}