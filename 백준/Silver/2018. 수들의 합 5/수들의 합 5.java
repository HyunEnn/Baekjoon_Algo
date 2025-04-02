
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int start = 1, end = 1, sum = 0;
        int answer = 0;
        while(true) {
            if(sum >= N) {
                sum -= start;
                start++;
            } else {
                if(end == N+1) break;
                sum += end;
                end++;
            }

            if(sum == N) answer++;
        }
        System.out.println(answer);
    }
}
