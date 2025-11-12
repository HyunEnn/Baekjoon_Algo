
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[] rains;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        rains = new int[W];
        st = new StringTokenizer(br.readLine());
        int ans = 0, curr = 0, sum = 0, lastW = 0;
        for(int i=0;i<W;i++) {
            rains[i] = Integer.parseInt(st.nextToken());
            if(rains[i] >= curr) {
                curr = rains[i];
                ans += sum;
                sum = 0;
                lastW = i;
//                System.out.println(i + " " + ans);
            } else {
                sum += curr - rains[i];
            }
        }
        // sum 이 0이 아니라면, lastW + 1 부터 탐색
        while(lastW + 1 < W - 1) {
            int idx = lastW + 1;
            int max = 0;
            // 여기서, 그 중 가장 큰 벽의 위치를 찾고 위치 저장
            for(int i=idx;i<W;i++) {
                if(rains[i] > max) {
                    max = rains[i];
                    lastW = i;
                }
            }
            // 찾은 위치부터 한칸씩 탐색하는데, curr 을 만나기 전까지 max - rains[i]
            int f = lastW - 1;
            while(rains[f] < max) {
//                System.out.println("현재 자리 추가 : " + (max - rains[f]));
                ans += max - rains[f];
                f--;
            }
//            System.out.println(lastW + " " +ans);
        }

        System.out.println(ans);

        /**
         * 현재 자리가 curr 보다 크다면, 기존의 sum 값을 ans 에 추가해준다
         * 그리고 재탐색할때, curr 보다 작은 경우면 (curr - 현재 자리)를 통해 sum 에 추가 저장해둔다.
         * 만약에 rains[i] 가 curr 보다 크거나 같으면, 지금까지 저장해둔 sum 을 ans 에 추가해주고, sum 초기화 및 curr 을 rains[i]
         */


    }
}
