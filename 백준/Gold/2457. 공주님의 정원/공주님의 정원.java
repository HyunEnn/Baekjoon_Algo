
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int st, end;

        Point(int st, int end) {
            this.st = st;
            this.end = end;
        }
    }

    static int[] daysInMonth = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    // 날짜 변환 계산기
    static int toDay(int m, int d) {
        int sum = 0;
        for (int i = 1; i < m; i++) sum += daysInMonth[i];
        return sum + d;
    }

    static int N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());
            int startD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            int s = toDay(startM, startD);
            int e = toDay(endM, endD);
            list.add(new Point(s, e));
        }

        list.sort((a, b) -> {
            if (a.st == b.st) {
                return b.end - a.end;
            }
            return a.st - b.st;
        });
        // 시작일 오름차순, 끝나는 날 내림차순

        int currDay = toDay(3, 1);
        int finish = toDay(12, 1);
        int ans = 0;

        int idx = 0;
        while (currDay < finish) {

            int bestEnd = currDay;
            // 현재 선택된 날짜가, currDay 보다 작고, 종료일자가 길면 현재 위치 저장
            while(idx < list.size() && list.get(idx).st <= currDay) {
                if(list.get(idx).end > bestEnd) bestEnd = list.get(idx).end;
                idx++;
            }

            if(bestEnd == currDay) {
                System.out.println(0);
                return;
            }

            // 선택된 종료일이 요구하는 실제 종료일보다 적으면 계속 진행해야함
            currDay = bestEnd;
            ans++;

        }
        System.out.println(ans);
    }

}
/**
 * 4
 * 1 1 5 31
 * 1 1 6 30
 * 5 15 8 31
 * 6 10 12 10
 * <p>
 * 정렬을 하고나서, 우선 가장 먼저 3월 1일에 근접한 날짜를 선택한다.
 * 고르면, 그 꽃의 지는 날을 curM, curD 값으로 갱신한다.
 * 그리고 curr 의 위치를 현재 꽃으로 지정한다.
 */
