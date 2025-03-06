import java.util.*;

class Solution {
    static class Point implements Comparable<Point> {
        int st, time;
        // 번호, 소요 시간
        Point(int st, int time) {
            this.st = st;
            this.time = time;
        }
        
        @Override
        public int compareTo(Point p) {
            return this.time - p.time;
        }
    }
    static PriorityQueue<Point> PQ = new PriorityQueue<>();
    public int solution(int[][] jobs) {
        int answer = 0;
        int currentTime = 0;
        int cnt = 0;
        int idx = 0;
        int time = 0;
// 큐에서 소요 시간이 가장 짧은 작업을 선택하여 수행한다.
// 작업이 완료되면 현재 시각을 업데이트하고, 다음 실행 가능한 작업들을 큐에 추가한다.
        // jobs 배열을 요청 시간 기준으로 정렬한다.
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        // PQ를 처리하는 과정 : 모든 작업이 처리될 때까지 위 과정을 반복한다.
        // 반례로, 0ms 시작이 아닐 수 있다. 그래서 !PQ.isEmpty() 가 불가능
        while(cnt < jobs.length) {
            // PQ에 넣을 자리
            while(idx < jobs.length && currentTime >= jobs[idx][0]) {
                PQ.offer(new Point(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            // 현재 시간 0초, 최소 시작 시간 1초 일 경우 -> PQ가 비어있음
            if(!PQ.isEmpty()) {
                Point p = PQ.poll();
                // 0, 3 -> 현재시간 3 
                currentTime += p.time;
                // currentTime - p.st 시작시간 을 더해준다?
                answer += (currentTime - p.st);
                cnt++;
            } else {
                currentTime = jobs[idx][0];
            }
        }
        // 3 + 7 + 17 = 27
        return answer / jobs.length;
    }
}