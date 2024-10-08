
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static Set<String> listen = new HashSet<>();
    static List<String> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            listen.add(line);
        }

        for(int i=0;i<M;i++) {
            String line = br.readLine();
            if(listen.contains(line)) {
                answer.add(line);
            }
        }

        System.out.println(answer.size());
        Collections.sort(answer);
        for(int i=0;i<answer.size();i++) {
            System.out.println(answer.get(i));
        }
    }
}
