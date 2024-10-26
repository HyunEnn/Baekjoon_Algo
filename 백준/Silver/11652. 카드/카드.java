
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static HashMap<Long, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            Long a = Long.parseLong(br.readLine());
            hashMap.put(a, hashMap.getOrDefault(a, 0) + 1);
        }

        List<Long> list = new ArrayList<>(hashMap.keySet());
        list.sort((b1, b2) -> {
            int getA = hashMap.get(b1);
            int getB = hashMap.get(b2);
            // 가장 많이 가지고 있는 갯수가 같다면
            if(getA == getB) {
                return b1.compareTo(b2);
            }
            return getB - getA;
        });

        System.out.println(list.get(0));
    }
}
