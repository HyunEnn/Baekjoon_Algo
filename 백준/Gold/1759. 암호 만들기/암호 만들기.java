
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, C;
    static char[] ch = new char[] {'a','e','i','o','u'};
    static boolean[] v;
    static char[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        v = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, new StringBuilder());
    }

    public static void dfs(int idx, StringBuilder sb) {
        // basis
        if(sb.length() == L) {
            int a = 0;
            int b = 0;
            for(int i=0;i<sb.length();i++) {
                boolean flag = false;
                for(int j=0;j<ch.length;j++) {
                    if(sb.charAt(i) == ch[j]) flag = true;
                }
                if(flag) a++;
                else b++;
            }
            if(a>=1 && b>=2) System.out.println(sb.toString());
            return;
        }

        // inductive
        for(int i=idx;i<C;i++) {
            if(!v[i]) {
                v[i] = true;
                sb.append(arr[i]);
                dfs(i+1, sb);
                sb.delete(sb.length()-1, sb.length());
                v[i] = false;
            }
        }
    }
}
