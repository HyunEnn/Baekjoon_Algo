
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int x1, x2, y1, y2, r1, r2;
        double d;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            // 1번 점에 대한 r
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            // 2번 점에 대한 r
            d = Math.sqrt(((x1-x2) * (x1-x2)) + ((y1-y2) * (y1-y2)));
            if(x1==x2 && y1==y2) {
                if(r1 == r2) sb.append(-1 + "\n");
                else sb.append(0 + "\n");
            }
            else {
                if(r1 + r2 == d || (r1-r2)*(r1-r2) == d*d) sb.append(1 + "\n");
                else if(r1 + r2 < d || (r1-r2) * (r1-r2) > d*d) sb.append(0 + "\n");
                else if((r1-r2)*(r1-r2)<d*d && d*d<(r1+r2)*(r1+r2)) sb.append(2 + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}
