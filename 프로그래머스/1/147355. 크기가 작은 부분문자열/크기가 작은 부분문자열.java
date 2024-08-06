import java.util.*;
import java.io.*;

class Solution {
    static List<Long> list = new ArrayList<>();
    public int solution(String t, String p) {
        for(int i=0;i<=t.length()-p.length();i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=i;j<i+p.length();j++) {
                sb.append(t.charAt(j));
            }
            long a = Long.parseLong(sb.toString());
            long b = Long.parseLong(p);
            if(b >= a) {
                list.add(a);
            }
        }
        
        return list.size();
    }
}