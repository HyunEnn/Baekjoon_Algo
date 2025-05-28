
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, H, M;
    static int answer;
    static int[][] ladders;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선
        M = Integer.parseInt(st.nextToken()); // 가로선
        H = Integer.parseInt(st.nextToken()); // 가로선 높이
        ladders = new int[H][N-1];
        answer = 0;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 현재 0,0 부터 시작하기에 -1
            ladders[a-1][b-1] = 1;
        }

        // 아무것도 안해도 가능한 경우?
        if(check()) {
            System.out.println(0);
            System.exit(0);
        }
        // 가능한 모든 조합 ? 1개, 2개, 3개일 경우 다 따지면 시간 복잡도는?
        for(int i=1;i<=3;i++){
            dfs(0, i);
        }

        System.out.println(-1);
    }
    
    public static void dfs(int idx, int max) {
        // basis
        if(idx == max) {
            if(check()) {
                // 답이 나왔으므로 더 이상 탐색필요없이 그냥 바로 종료?
                answer = max;
                System.out.println(max);
                System.exit(0);
            }
            return;
        }
        
        // inductive
        for(int i=0;i<H;i++) {
            for(int j=0;j<N-1;j++) {
                // 무조건 왼쪽에서 사다리를 놓는다고 가정
                // 조건) 가로선이 연속하거나 서로 접하면 안된다.
                if (ladders[i][j] == 0
                        && (j == 0 || ladders[i][j - 1] == 0)
                        && (j == N - 2 || ladders[i][j + 1] == 0)) {
                    ladders[i][j] = 1;
                    dfs(idx + 1, max);
                    ladders[i][j] = 0;
                }
            }
        }
    }

    public static boolean check() {
        // i 번이 i 번으로 끝나면 true
        for(int i=0;i<N;i++) {
            int curr = i;
            for(int j=0;j<H;j++) {
                // 1이면, 현재 자리만 보면 되지만, 2 이상이면 내 자리와 한 칸 앞을 보고 이동해야함
                // N이면, 현재 자리 -1만 탐색
                // 사다리가 있다면 이동
                if (curr < N - 1 && ladders[j][curr] == 1) curr++;
                else if (curr > 0 && ladders[j][curr - 1] == 1) curr--;
            }

            if(curr != i) return false;
        }
        return true;
    }
    // 선을 긋고, 탐색을 진행
    // 만약에, i가 i번째로 끝나지 않는다면, 종료하고 선을 하나 더 추가해서 탐색 시작
    // 가로선이 3개가 넘어가면, -1을 출력하고 종료
    // 사다리를 놓으면 [1,1,0,0] 으로 놓아야 어느쪽으로 가야할지 알 수 있음
    // 따라서, 조합으로 가능한 3개의 선을 그어줄 수 있는 경우를 탐색하는 문제
}
