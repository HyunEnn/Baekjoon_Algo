import java.util.*;

class Solution {
    static boolean[][][] v;
    static class Point implements Comparable<Point> {
        int r, c, ch;
        Point(int r, int c, int ch) {
            this.r = r;
            this.c = c;
            this.ch = ch;
        }
        
        @Override
        public int compareTo(Point p) {
            if(this.r == p.r) {
                if(this.c == p.c) 
                    return this.ch - p.ch;
                return this.c - p.c;
            }
            return this.r - p.r;
        }
    }
    static List<Point> list = new ArrayList<>();
    public int[][] solution(int n, int[][] build_frame) {
        v = new boolean[n + 1][n + 1][2];
        for(int i=0;i<build_frame.length;i++) {
            int r = build_frame[i][0];
            int c = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            
            if(b == 1) {
                if(canBuild(r, c, a)) {
                    v[r][c][a] = true;
                    list.add(new Point(r, c, a));
                }
            } else {
                remove(r, c, a);
            }
            // 삭제할 때는 모든 list의 조건들을 고려해서 삭제해야함
            
        }
        Collections.sort(list);
        int[][] answer = new int[list.size()][3];
        for(int i=0;i<answer.length;i++) {
            Point p = list.get(i);
            answer[i][0] = p.r;
            answer[i][1] = p.c;
            answer[i][2] = p.ch;
        }
        return answer;
    }
    
    public static boolean canBuild(int x, int y, int a) {
        int n = v.length; // 배열 크기 기반으로 범위 체크

        if (a == 0) {
            // 기둥 조건 1. 높이가 0일때
            if (y == 0) return true;
            // 2. 아래에 기둥이 있는 경우
            if (v[x][y-1][0]) return true;
            // 3. 보 왼쪽, 오른쪽 끝
            if ((x > 0 && v[x-1][y][1]) || v[x][y][1]) return true;
            return false;
        } else {
            // 보 조건
            // 1. 양쪽 중 한쪽에 기둥이 있어야 함
            if ((y > 0 && v[x][y-1][0]) ||
                (y > 0 && x+1 < n && v[x+1][y-1][0]))
                return true;
            // 2. 왼쪽, 오른쪽 보가 있으면 연결
            if (x > 0 && x+1 < n && 
                v[x-1][y][1] && v[x+1][y][1])
                return true;
            return false;
        }
    }
    
    public static void remove(int r, int c, int a) {
        v[r][c][a] = false;
        // 1. 삭제
        for(int i=0;i<list.size();i++) {
            Point p = list.get(i);
            if(p.r == r && p.c == c && p.ch == a) {
                list.remove(i);
                break;
            }
        }
        
        // 2. 삭제 후 검증
        for(int i=0;i<list.size();i++) {
            Point p = list.get(i);
            if(!canBuild(p.r, p.c, p.ch)) {
                v[r][c][a] = true;
                list.add(new Point(r, c, a));
                return;
            }
        }
    }
    // x, y는 가로 세로 좌표 , a -> 0:기둥 1:보, b -> 0:삭제, 1:설치
    // 설치 및 삭제는 오른쪽, 기둥은 위쪽방향
}