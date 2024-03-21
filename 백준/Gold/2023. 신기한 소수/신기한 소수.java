import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> list;
    static boolean[][] v;
    static int[] firstPrime = {2, 3, 5, 7};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<4;i++) {
            v = new boolean[10][9];
            dfs(1, firstPrime[i]);
        }
//        for(int i : list) {
//            System.out.println("list.get(i) = " + list.get(i));
//        }

    }

    public static void dfs(int idx, int prime) {
        if(idx == N) {
            if(isPrime(prime)) {
                // 소수면 list 추가
                list.add(prime);
                System.out.println(prime);
            }

            return;
        }

        for(int i=1;i<=9;i++) {
            if(i % 2 == 0)
                continue;
            if(isPrime(prime * 10 + i)) {
                dfs(idx+1, prime * 10 + i);
            }

        }
    }

    public static boolean isPrime(int x) {
        for(int i=2;i<=Math.sqrt(x);i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
