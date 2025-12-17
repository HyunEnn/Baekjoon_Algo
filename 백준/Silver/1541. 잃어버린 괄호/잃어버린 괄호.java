
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        boolean minus = false;
        int ans = 0;
        for(int i=0;i<line.length();i++){
            char c = line.charAt(i);

            if(c == '-') {
                // 마이너스가 왔는데, 첫 마이너스라면 기존 값을 ans 에 더함
                if(!minus) {
                    minus = true;
                    ans += Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
                // 첫 마이너스가 아니라면, 기존 값에 현재 저장된 값을 뺀다
                else {
                    ans -= Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            }

            else if(c == '+') {
                if(minus) {
                    ans -= Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                } else {
                    ans += Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            }

            else sb.append(c);
        }

        if(sb.length() > 0) {
            if(minus) ans -= Integer.parseInt(sb.toString());
            else ans += Integer.parseInt(sb.toString());
        }

        System.out.println(ans);
    }
}
