
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static String S, T;
    static List<Character> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        char[] s = S.toCharArray();
        list = new ArrayList<>();
        for(char c : T.toCharArray()) {
            list.add(c);
        }

        while(s.length != list.size()) {
            if(list.get(list.size() - 1) == 'A') list.remove(list.size() - 1);
            else if(list.get(list.size() - 1) == 'B') {
                list.remove(list.size() - 1);
                Collections.reverse(list);
            }
        }

        boolean flag = true;
        for(int i=0;i<list.size();i++) {
            if(list.get(i) != s[i]) flag = false;
        }

        if(flag) System.out.println(1);
        else System.out.println(0);

    }


}
