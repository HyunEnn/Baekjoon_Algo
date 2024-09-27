
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N;
    static char[] sel;
    static char[] c;
    static int[] arr;
    static Set<String> set = new HashSet<>();
    static boolean[] v;
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            c = br.readLine().toCharArray();
            Arrays.sort(c);
            arr = new int[26];
            v = new boolean[c.length];
            for(int j=0;j<c.length;j++) {
                arr[c[j]-'a']++;
            }
            sel = new char[c.length];
            dfs(0);
        }

        System.out.println(sb.toString());
    }

    // 에너그램에 들어간 단어마다 dfs 탐색을 진행
    static void dfs(int idx) {
        // 종료 조건은 idx 가 에너그램[wi]의 길이만큼 도달하면 종료
        if(idx == c.length) {
            // Set 을 토대로 기존에 있는 값인지 아닌지 체크
            StringBuilder tempSb = new StringBuilder();
            for(int i=0;i<sel.length;i++) {
                tempSb.append(sel[i]);
            }
            String result = tempSb.toString();
            if(!set.contains(result)) {
                set.add(result);
                sb.append(result).append("\n");
            }
            return;
        }
        // 반복은 조합 짜는 형태로 진행
        for(int i=0;i<arr.length;i++) {
            if(arr[i] > 0) {
                sel[idx] = (char) (i + 'a');
                arr[i]--;
                dfs(idx + 1);
                arr[i]++;
            }
        }
    }
}
