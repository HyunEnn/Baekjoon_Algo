
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point>{
        String s;
        Point(String s) {
            this.s = s;
        }

        @Override
        public int compareTo(Point p) {
            if(this.s.length() == p.s.length()) {
                int c1 = 0, c2 = 0;
                for(int i=0;i<this.s.length();i++) {
                    if (this.s.charAt(i) >= '0' && this.s.charAt(i) <= '9') {
                        c1 += this.s.charAt(i) - '0';
                    }
                    if (p.s.charAt(i) >= '0' && p.s.charAt(i) <= '9') {
                        c2 += p.s.charAt(i) - '0';
                    }
                }
                if(c1 == c2) {
                    return this.s.compareTo(p.s);
                }
                return c1 - c2;
            }
            return this.s.length() - p.s.length();
        }

    }
    static int N;
    static List<Point> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            list.add(new Point(br.readLine()));
        }

        Collections.sort(list);

        for(Point p : list) {
            System.out.println(p.s);
        }
    }
}
