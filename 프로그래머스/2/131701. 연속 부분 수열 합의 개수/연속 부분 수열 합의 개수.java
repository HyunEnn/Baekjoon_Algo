import java.util.*;
import java.io.*;

class Solution {
    static HashSet<Integer> set = new HashSet<>();
    public int solution(int[] elements) {
        for(int k=0;k<elements.length;k++) {
            for(int i=0;i<elements.length;i++) {
                int ch = elements[i];
                for(int j=1;j<=k;j++) {
                    ch += elements[(i + j) % elements.length];
                }
                set.add(ch);
            }   
        }
        return set.size();
    }
}