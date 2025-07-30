
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] buildings;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        // 0번 부터 N 번까지 탐색하면서, 현재 빌딩 <-> 목표 빌딩 간에 선을 그었을 때, 그 선에 끼어드는 빌딩이 없어야함
        // 빌딩 간의 선을 그었을 때, 기울기 값을 구한다. (현재 높이 - 목표 높이) / (인덱스 차이)
        // |2 - 5| / 2 = 1.3
        // 여기서, 목표 빌딩이 현재 빌딩보다 크면, 현재와 목표 빌딩 사이에서 (현재 빌딩 + 기울기) > 사이 빌딩 이 모두 성립되어야 보이는 고층 빌딩
        // 목표 빌딩이 현재 빌딩보다 작다면, 현재와 목표 빌딩 사이에서 (현재 빌딩 - 기울기) > 사이 빌딩 이 성립되어야 함
        // 현재 빌딩과 목표 빌딩이 같다면? ->
        int ans = 0;
        for(int i=0;i<N;i++) {
            // 좌로 볼 때, 현재 위치와 목표 위치와 기울기 계산
            int check = 0;
            for(int j=0;j<i;j++) {
                double slope = (double) (buildings[j] - buildings[i]) / (j-i);
                boolean flag = false;
                for(int k=j+1;k<i;k++) {
                    if(buildings[k] >= buildings[i] + slope * (k-i)) {
                        flag = true;
                        break;
                    }
                }

                if(!flag) check++;
            }
            
            // 우로 볼 때
            for(int j=i+1;j<N;j++) {
                double slope = (double) (buildings[j] - buildings[i]) / (j-i);
                boolean flag = false;
                for(int k=i+1;k<j;k++) {
                    if(buildings[k] >= buildings[i] + slope * (k-i)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) check++;
            }

            ans = Math.max(ans, check);
        }

        System.out.println(ans);
    }
}
