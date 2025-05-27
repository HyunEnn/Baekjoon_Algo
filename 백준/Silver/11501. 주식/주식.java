
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            int n = Integer.parseInt(br.readLine());
            long[] vals = new long[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++) {
                vals[i] = Long.parseLong(st.nextToken());
            }
            // 거꾸로, 접근한다
            long max = 0;
            long profit = 0;
            for(int i=n-1;i>=0;i--) {
                if(vals[i] > max) max = vals[i];
                else profit += max - vals[i];
            }

            System.out.println(profit);
        }
    }
}