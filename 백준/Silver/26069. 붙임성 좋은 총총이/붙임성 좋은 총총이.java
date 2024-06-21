import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
         HashSet<String> set = new HashSet<>();
         set.add("ChongChong");
         for(int i=0;i<N;i++) {
             st = new StringTokenizer(br.readLine());
             String first = st.nextToken();
             String second = st.nextToken();

             if(set.contains(first) || set.contains(second)) {
                 set.add(first);
                 set.add(second);
             }
         }

        System.out.println(set.size());
    }
}
