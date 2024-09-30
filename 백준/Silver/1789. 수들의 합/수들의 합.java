import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        // 200의 합을 가지는 N개의 자연수?
        // 1~18 , 29
        Long curr = N;
        int cnt = 0;
        int idx = 1;
        while(true) {
            // idx 를 뺏을 때, 값을 넘지 않으면서 idx + 1을 했을 때 가능하면 진행한다.
            if(curr - idx >= 0 && (curr - idx) - (idx + 1) >= 0) {
                curr -= idx;
                idx++;
                cnt++;
            } else {
//                System.out.println(curr);
                cnt++;
                break;
            }
        }
        System.out.println(cnt);
    }
}
