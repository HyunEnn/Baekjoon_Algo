
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int zero;
    static int one;
    static List<Integer> negative, positive;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        negative = new ArrayList<>();
        positive = new ArrayList<>();

        for(int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            if(num < 0) negative.add(num);
            else if(num == 0) zero++;
            else if(num == 1) one++;
            else positive.add(num);
        }

        negative.sort((a, b) -> b - a);
        positive.sort((a, b) -> a - b);

        // 예외는, 음수 1개일때 zero 가 있다면? + 1이 있다면? 1은 스스로 처리하는게 제일 큰 값
        long ans = 0;
        // 기존 0번 인덱스만 제거해서 따로 추가해주고, 나머지 짝수개는 기존과 동일하게 진행한다.
        if(negative.size() % 2 != 0) {
            int a = negative.remove(0);
            if(zero > 0) {
                zero--;
            }
            else ans += a;
        }

        for(int i=0;i<negative.size();i+=2) {
            int a = negative.get(i);
            int b = negative.get(i+1);
            ans += (long) a * b;
        }

        if(positive.size() % 2 != 0) {
            int a = positive.remove(0);
            ans += a;
        }

        for(int i=0;i<positive.size();i+=2) {
            int a = positive.get(i);
            int b = positive.get(i+1);
            ans += (long) a * b;
        }

        System.out.println(ans + one);
        /**
         * 음수가 짝수개라면, 그대로 다 곱한다.
         * 음수가 홀수개라면, 제일 큰 한 수를 더한다.
         *
         * 양수가 짝수개라면, 그대로 다 곱한다.
         * 양수가 홀수개라면, 제일 작은 수를 더한다.
          */
    }
}
