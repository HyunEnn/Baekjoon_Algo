
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] arr;
    static Stack<Character> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String s = br.readLine();
        arr = s.toCharArray();
        int m = arr.length;
        stack = new Stack<>();
        for(int i=0;i<line.length();i++) {
            char c = line.charAt(i);
            stack.push(c);

            if(stack.size() >= m && c == arr[m - 1]) {
                boolean v = true;
                for(int j=0;j<m;j++) {
                    if(stack.get(stack.size() - m + j) != arr[j]) {
                        v = false;
                        break;
                    }
                }

                if(v) {
                    for(int j=0;j<m;j++) {
                        stack.pop();
                    }
                }
            }

        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            for(char c : stack) sb.append(c);
            System.out.println(sb.toString());
        }
    }
}
