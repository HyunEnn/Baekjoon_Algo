import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N;
        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        dp.add(1);
        dp.add(5);

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            if (N < dp.size()) {
                System.out.println(dp.get(N));
            } else {
                for (int j = dp.size(); j <= N; j++) {
                    int result = (Integer) dp.get(j - 1) + (Integer) dp.get(j - 2) * 4;

                    for (int k = 3; j >= k ; k++) {
                        if (k % 2 == 0){
                            result += 3 * (Integer) dp.get(j - k);
                        } else {
                            result += 2 * (Integer) dp.get(j - k);
                        }
                    }
                    dp.add(result);
                }
                System.out.println(dp.get(N));
            }

        }
    }
}