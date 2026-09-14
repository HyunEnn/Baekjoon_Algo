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
        int maxTime = 0;
        for(int i=0;i<routes.length;i++) {
            List<Point> path = new ArrayList<>();

            int start = routes[i][0] - 1;

            int r = points[start][0];
            int c = points[start][1];
            path.add(new Point(r,c));

            for(int j=1;j<routes[i].length;j++) {
                if(j >= 2) {
                    start = routes[i][j-1] - 1;
                }
                int end = routes[i][j] - 1;
                int r_diff = points[end][0] - points[start][0];
                int c_diff = points[end][1] - points[start][1];

                while(true) {
                    if(r_diff == 0 && c_diff == 0) break;

                    if(r_diff != 0) {
                        if(r_diff > 0) { r_diff--; r++; }
                        else { r_diff++; r--; }
                        path.add(new Point(r, c));
                        continue;
                    }
                    if(c_diff > 0) { c_diff--; c++; }
                    else { c_diff++; c--; }
                    path.add(new Point(r, c));
                }
            }
            maxTime = Math.max(maxTime, path.size());
            list.add(path);
        }
        int answer = 0;
        for(int t=0;t<maxTime;t++) {
            int[][] map = new int[101][101];
            for(int i=0;i<list.size();i++) {
                // 값이 있을 경우에만 탐색
                if(list.get(i).size() > t) {
                    Point p = list.get(i).get(t);
                    map[p.r][p.c]++;
                    if(map[p.r][p.c] == 2) answer++;
                }
            }
        }
        return answer;

    }
}