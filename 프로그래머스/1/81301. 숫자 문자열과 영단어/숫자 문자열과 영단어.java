import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] c = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        // int a = Integer.valueOf(c);
        StringBuilder sb = new StringBuilder();
        for(char ch : c) {
            if(ch >= 'a' && ch <= 'z') {
                sb.append(ch);
                String res = sb.toString();
                // System.out.println("res : " + res);
                
                if(res.equals("zero")) {
                    list.add(0);
                    sb = new StringBuilder();
                }
                else if(res.equals("one")) {
                    list.add(1);
                    sb = new StringBuilder();
                }
                else if(res.equals("two")) {
                    list.add(2);
                    sb = new StringBuilder();
                }
                else if(res.equals("three")) {
                    list.add(3);
                    sb = new StringBuilder();
                }
                else if(res.equals("four")) {
                    list.add(4);
                    sb = new StringBuilder();
                }
                else if(res.equals("five")) {
                    list.add(5);
                    sb = new StringBuilder();
                }
                else if(res.equals("six")) {
                    list.add(6);
                    sb = new StringBuilder();
                }
                else if(res.equals("seven")) {
                    list.add(7);
                    sb = new StringBuilder();
                }
                else if(res.equals("eight")) {
                    list.add(8);
                    sb = new StringBuilder();
                }
                else if(res.equals("nine")) {
                    list.add(9);
                    sb = new StringBuilder();
                }
            }
            else {
                // System.out.println(ch);
                // System.out.println("char 변환 : " + Integer.valueOf(ch-48));
                list.add(Integer.valueOf(ch-48));
            }
        }
        int size = list.size() - 1;
        for(int i : list) {
            System.out.println(i);
            int m = (int)Math.pow(10, size--);
            answer += m * i;
        }
        // System.out.println(a);
        return answer;
    }
}