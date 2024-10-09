import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static HashMap<String, Integer> map = new HashMap<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            // 여기서 .이 나오면 그 이후 문자를 뽑아서 HashMap 으로 저장
            boolean flag = false;
            String s = "";
            for(int j=0;j<line.length();j++) {
                if(flag) {
                    s += line.charAt(j);
                }
                if(line.charAt(j) == '.')
                    flag = true;
            }
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        for(String key : list) {
            int val = map.get(key);
            System.out.println(key + " " + val);
        }
    }
}
