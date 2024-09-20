
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine()) != null) {
            if(input.isEmpty()) break;
            String answer = "Yes";
            st = new StringTokenizer(input);
            String s = st.nextToken();
            String t = st.nextToken();
            int curr = 0;
            for(int i=0;i<s.length();i++) {
                boolean flag = false;
                for(int j=curr;j<t.length();j++) {
                    if(s.charAt(i) == t.charAt(j)) {
                        curr = j + 1;
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    answer = "No";
                    break;
                }
            }
            System.out.println(answer);
        }
    }
}
