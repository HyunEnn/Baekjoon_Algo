import java.io.*;
import java.util.*;

class Solution {
    static List<String> list;
    static int[] check;
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        check = new int[26];
        list = new ArrayList<>();
        for(int i=0;i<strings.length;i++) {
            list.add(strings[i].charAt(n) + strings[i]);
        }
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).substring(1);
        }
        return answer;
    }
}