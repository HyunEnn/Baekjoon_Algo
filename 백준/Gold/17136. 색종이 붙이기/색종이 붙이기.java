
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] papers = new int[] {0,5,5,5,5,5};
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        for(int i=0;i<10;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 0, 0);
//        for(int i=0;i<papers.length;i++) {
//            System.out.print(papers[i] + " ");
//        }
//        System.out.println();
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 끝까지 탐색을 완료하면 바로 재귀를 종료?

    public static void find(int r, int c, int cnt) {
        // basis
        if(r >= 10) {
            if(isCovered()) {
                answer = Math.min(answer, cnt);
            }
            return;
        }
        // 다음 열 탐색
        if(c >= 10) {
            find(r + 1, 0, cnt);
            return;
        }

        // inductive : 현재 위치에서 분기처리
        // 색종이 붙이기 로직 , 5x5 부터 탐색
        if(map[r][c] == 1) {
            for(int size=5;size>=1;size--) {
                if(papers[size] > 0 && canAttach(r, c, size)) {
                    // 색종이 붙이기(붙일 수 있는 조건 탐색)
                    papers[size]--;
                    attach(r, c, size, 0);
                    // 다음 분기 탐색
                    find(r, c + 1, cnt + 1);
                    // 색종이 떼기
                    papers[size]++;
                    attach(r, c, size, 1);
                }
            }
        } else {
            find(r, c + 1, cnt);
        }
    }

    public static boolean isCovered() {

        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                if(map[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 10 && c >= 0 && c < 10;
    }

    public static boolean canAttach(int r, int c, int size) {

        // 범위를 벗어나는지 체크
        if(!inRange(r + size - 1, c + size -1)) return false;

        // 범위 안의 모든 칸이 1인지 체크
        for(int i=r;i<r+size;i++) {
            for(int j=c;j<c+size;j++) {
                if(map[i][j] != 1)
                    return false;
            }
        }
        return true;
    }

    public static void attach(int r, int c, int size, int color) {

        for(int i=r;i<r+size;i++) {
            for(int j=c;j<c+size;j++) {
                map[i][j] = color;
            }
        }
    }
}
