
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] belt;
    static int[] ch;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[N*2];
        ch = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N*2;i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int step = 1;
        while(true) {
            // 1. 컨베이어 벨트 회전 
            int tmp = belt[2*N-1];
            for(int i=2*N-1;i>0;i--) {
                belt[i] = belt[i-1];
            } belt[0] = tmp;
            // 1-1. 로봇도 같이 회전 + 이동한 칸이 로봇이 내릴 자리면 즉시 하차
            int robotTmp = ch[N-1];
            for(int i=N-1;i>0;i--) {
                ch[i] = ch[i-1];
            } ch[0] = robotTmp;
            if(ch[N-1] == 1)
                ch[N-1] = 0;
            // 2. 로봇이 있으면 한 칸 이동, 없으면 스킵, 거꾸로 탐색하면 또 탐색할일 없음
            for(int i=N-2;i>=0;i--) {
                // 로봇이 존재
                if(ch[i] == 1) {
                    // 이동하려는 칸에 로봇이 없고, 내구도가 1이상 인지
                    // 이동 가능하면 현재 값은 0으로, 이동한 값은 1로 처리
                    // 이동한 위치의 belt value 는 1 감소
                    if(ch[i+1] == 0 && belt[i+1] >= 1) {
                        ch[i] = 0;
                        ch[i+1] = 1;
                        belt[i+1]--;
                    }
                }
            }

            // 2-1. 로봇이 내릴 수 있는 위치에 있으면 즉시 하차
            if(ch[N-1] == 1)
                ch[N-1] = 0;

            // 3. 올리는 위치에 있는 칸의 내구도가 0보다 크면 로봇을 올리고, value 감소
            if(belt[0] > 0 && ch[0] == 0) {
                ch[0] = 1;
                belt[0]--;
            }
            // 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료
            int cnt = 0;
            for(int i=0;i<2*N;i++) {
                if(belt[i] == 0) cnt++;
            }
            if(cnt >= K)
                break;
            // 그게 아니면 단계 증가 후, 1번부터 다시 시작
            step++;
        }

        System.out.println(step);
    }
}
