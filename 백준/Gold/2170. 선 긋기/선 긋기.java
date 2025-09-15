
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int a, b;
        Point(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Point(a, b));
        }

        list.sort((a, b) -> {
            if(a.a == b.a)
                return a.b - b.b;
            return a.a - b.a;
        });

        int line = list.get(0).b - list.get(0).a;
        int curr = list.get(0).b;
        // 선이 연결이 가능하면, 이어붙이고 아니라면 끊어붙인다?
        for(int i=1;i<list.size();i++) {
            Point p = list.get(i);
            // 여기서, 현재 들어온 선이 curr 보다 작거나 같으면 이어붙이기 가능
            if(curr >= p.a) {
                // 끝값이 현재 선의 끝값보다 길다면, line 에 더해준다.
                if(p.b > curr) {
                    line += p.b - curr;
                    curr = p.b;
                } // 현재 선의 끝값보다 짧다면, 무시한다.
                else continue;
            }
            // p.b 가 현재 curr 보다 크다면, 값을 재갱신하고 curr 도 새로운 b로 초기화?
            else {
                line += p.b - p.a;
                curr = p.b;
            }
        }
        // 0 10, 3 5

        System.out.println(line);
    }
}
