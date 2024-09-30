
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] arr;
    static Set<Character> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int ans = 0;
        arr = new String[N];
        for(int i=0;i<N;i++) {
            arr[i] = br.readLine();
        }

        for(int i=0;i<N;i++) {
            char[] c = arr[i].toCharArray();
            set.clear();
            set.add(c[0]);
            boolean flag = false;
            for(int j=1;j<c.length;j++) {
                // 현재 값이 기존에 존재하지 않았으면 그냥 추가
                if(!set.contains(c[j])) {
                    set.add(c[j]);
                }
                // 이전 값과 똑같았어도 상관없이 진행
                else if(c[j] == c[j-1]) continue;
                // 기존에 존재했던 값이 다시 나왔고, 앞 전의 값과 다르면 불가
                else if(set.contains(c[j]) && (c[j] != c[j-1])) {
                    flag = true;
                    break;
                }
            }
            if(!flag) ans++;
        }

        System.out.println(ans);
    }
}
