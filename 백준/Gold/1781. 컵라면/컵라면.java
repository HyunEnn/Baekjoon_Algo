
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int deadline, ramen;
        Point(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Point p) {
            // 데드라인을 우선적으로 오름차순 정렬하고, 같으면 컵라면을 내림차순 정렬
            if(this.deadline == p.deadline)
                return p.ramen - this.ramen;
            return this.deadline - p.deadline;
        }

    }
    static int N;
    static PriorityQueue<Point> PQ = new PriorityQueue<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            PQ.offer(new Point(deadline, ramen));
        }

        PriorityQueue<Integer> PQ2 = new PriorityQueue<>();
        /**
         */
        int ans = 0;
        while(!PQ.isEmpty()) {
            Point p = PQ.poll();
            PQ2.offer(p.ramen);
            if(PQ2.size() > p.deadline)
                PQ2.poll();
        }

        while(!PQ2.isEmpty()) ans += PQ2.poll();
        System.out.println(ans);
    }
    // 문제 마다, 데드라인이 정해져있고
    // 데드라인 으로 정렬을 진행하고, 현재 뽑은 데드라인 기준으로 가장 작은 값을 뽑고
    // 그 데드라인 날짜를 전부 제거하고, 다음 데드라인을 진행한다.
}
