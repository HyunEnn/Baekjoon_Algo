
import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int a = Integer.parseInt(st.nextToken());
                    hashMap.put(a, hashMap.getOrDefault(a, 0) + 1);
                }
            }

            List<Map.Entry<Integer, Integer>> list = new LinkedList<>(hashMap.entrySet());
            list.sort((o1, o2) -> o2.getValue() - o1.getValue());
            int cnt = 0;
            int currV = 0;
            boolean flag = false;
            List<Integer> answer = new ArrayList<>();
            for(Map.Entry<Integer, Integer> entry : list) {

                if(flag) break;
                if(cnt == 0) {
                    currV = entry.getValue();
                    cnt++;
                }
                else if(cnt == 1) {
                    if(currV == entry.getValue()) continue;
                    else {
                        currV = entry.getValue();
                        answer.add(entry.getKey());
                        cnt++;
                    }
                } else {
                    if(currV == entry.getValue())
                        answer.add(entry.getKey());
                    else {
                        flag = true;
                        break;
                    }
                }
            }

            Collections.sort(answer);
            for(int i = 0; i < answer.size(); i++) {
                System.out.print(answer.get(i) + " ");
            }
            System.out.println();
        }
    }
}
