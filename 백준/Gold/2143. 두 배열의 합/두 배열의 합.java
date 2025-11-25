
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static int caseA, caseB;
    static int[] A, B;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T  = Integer.parseInt(br.readLine());
        caseA = Integer.parseInt(br.readLine());
        A = new int[caseA];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<caseA;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        caseB = Integer.parseInt(br.readLine());
        B = new int[caseB];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<caseB;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 1. 연속 부분합으로 가능한 모든 합을 구한다.
         * 2. sumA + sumB = T 이므로, sumA == T - sumB 인 경우를 구한다.
         */

        HashMap<Long, Integer> mapA = new HashMap<>();
        HashMap<Long, Integer> mapB = new HashMap<>();
        for(int i=0;i<caseA;i++) {
            long sum = 0;
            for(int j=i;j<caseA;j++) {
                sum += A[j];
                mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            }
        }

        long ans = 0;
        for(int i=0;i<caseB;i++) {
            long sum = 0;
            for(int j=i;j<caseB;j++) {
                sum += B[j];
                long need = T - sum;
                ans += mapA.getOrDefault(need, 0);
            }
        }

        System.out.println(ans);
    }
}
