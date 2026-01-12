
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static class Point {
        int st, ed;
        Point(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }
    }
    static List<Point> list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        int ans = 0;
        list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Point(start, end));
        }

        list.sort((a, b) -> {
            if(a.st == b.st) {
                return a.ed - b.ed;
            }
            return a.st - b.st;
        });

        for(int i=0;i<list.size();i++) {

            Point p = list.get(i);
            int start = p.st;
            int end = p.ed;

            // 빈 값이면, 현재의 회의 끝나는 시간을 저장
            if(PQ.isEmpty()) {
                PQ.offer(end);
            }
            else {
                // 빈값 아니라면, PQ의 맨 앞자리와 비교해서 방을 쓸 수 있는지 추가해야하는지 판단
                if (PQ.peek() <= start) {
                    PQ.poll();
                }
                PQ.offer(end);
            }
            ans = Math.max(ans, PQ.size());
        }

        System.out.println(ans);
    }
}
