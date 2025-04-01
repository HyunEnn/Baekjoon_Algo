
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        // 소수 구하기
        for(int i=2;i*i<=N;i++) {
            if(isPrime[i]) {
                for(int j=i*i;j<=N;j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i=2;i<=N;i++) {
            if(isPrime[i]) list.add(i);
        }
        // 투 포인터로 앞 뒤에서 탐색하면서 N이 되는지 체크
        int start = 0, end = 0, sum = 0;
        int answer = 0;
        while(true) {
            if(sum >= N) {
                sum -= list.get(start);
                start++;
            } else {
                if(end == list.size()) break;
                sum += list.get(end);
                end++;
            }
            // 가능한 소수 조합이면 +1 , 투 포인터라서 중복 생각 필요 x
            if(sum == N) answer++;
        }

        System.out.println(answer);
    }
}
