import java.util.*;

class Solution {
    static class Point {
      int r, c;
      Point(int r, int c) {
        this.r = r;
        this.c = c;
      }
    }
    static List<List<Point>> list = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int max = 0;
        int t = 1;
        for(int i=0;i<routes.length;i++) {
          List<Point> path = new ArrayList<>();
          int start = routes[i][0] - 1;

          int r = points[start][0];
          int c = points[start][1];

          path.add(new Point(r, c)); // 시작 위치 저장
          for(int j=1;j<routes[i].length;j++) {
          if(j >= 2) {
            start = routes[i][j-1] - 1;
            r = points[start][0];
            c = points[start][1];
          }
          int end = routes[i][j] - 1;
          // 가로, 세로 거리 차이 계산 -> 마이너스면 위로 이동해야함, 양수면 아래로 이동
          int r_diff = points[end][0] - r;
          int c_diff = points[end][1] - c;
          // start -> end 이동 기록을 저장
          while(true) {
            // 종료 조건
            if(r_diff == 0 && c_diff == 0) {
              max = Math.max(max, t);
              break; 
            }
            // r 이 우선 순위 높음
            if(r_diff != 0) {
              if(r_diff > 0) { r_diff--; r++; }
              else { r_diff++; r--; }
            } else {
              if(c_diff > 0) { c_diff--; c++; }
              else { c_diff++; c--; }
            }
            path.add(new Point(r, c));
            t++;
          }
        }
        list.add(path);
      }
      for(int time=0;time<max;time++) {
          int[][] map = new int[101][101];
        for(int i=0;i<list.size();i++) {
            if(time >= list.get(i).size()) continue;
            Point p = list.get(i).get(time);
            map[p.r][p.c]++;
            if(map[p.r][p.c] == 2) answer++;
        }
      }

      return answer;
    }
}