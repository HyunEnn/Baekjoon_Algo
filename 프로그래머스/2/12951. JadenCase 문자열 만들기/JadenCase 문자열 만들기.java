import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        String[] a = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<a.length;i++) {
            String now = a[i];
            
            if(a[i].length() == 0) {
                sb.append(" ");
            }
            else {
                sb.append(now.substring(0, 1).toUpperCase());
                sb.append(now.substring(1, now.length()).toLowerCase());
                sb.append(" ");    
            }
            
        }
        if(s.substring(s.length()-1, s.length()).equals(" ")){
    		return sb.toString();
    	}
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}