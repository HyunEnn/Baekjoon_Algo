
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int ans = 666;
        int cnt = 1;
        while(cnt != N) {
            ans++;

            if(String.valueOf(ans).contains("666"))
                cnt++;
        }

        System.out.println(ans);
    }
}
