import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            List<Character> list = new LinkedList<>();
            ListIterator<Character> iter = list.listIterator();

            for(int j=0;j<line.length();j++) {
                if(line.charAt(j)=='<') {
                    if(iter.hasPrevious()) iter.previous();
                } else if(line.charAt(j)=='>') {
                    if(iter.hasNext()) iter.next();
                } else if(line.charAt(j)=='-') {
                    if(iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                } else {
                    iter.add(line.charAt(j));
                }
            }

            StringBuilder sb = new StringBuilder();
            for(char c : list) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
