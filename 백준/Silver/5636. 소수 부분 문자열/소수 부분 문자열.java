
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S;
    static boolean[] prime;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        prime = new boolean[100001];
        for(int i=2;i<=Math.sqrt(100001);i++) {
            if(prime[i]) // true 면 소수가 아님
                continue;
            for(int j=i*i;j<100001;j+=i) {
                prime[j] = true;
            }
        }

        while(true) {

            S = br.readLine();
            if(S.equals("0"))
                break;

            max = 0;
            // 11245
            for(int i=0;i<S.length();i++) {
                for(int j=i;j<i+6 && j<S.length();j++) {
                    int num = Integer.parseInt(S.substring(i,j + 1));
                    if(num > 100000) continue;
                    if(!prime[num])
                        max = Math.max(max, num);
                }
            }

            System.out.println(max);
        }
    }
}
