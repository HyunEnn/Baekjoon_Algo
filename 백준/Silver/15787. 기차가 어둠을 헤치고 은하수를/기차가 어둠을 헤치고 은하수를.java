
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] trains;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new int[N+1][21];
        // 좌석은 1~20번까지
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            switch(order) {
                case 1:
                    int ch1 = Integer.parseInt(st.nextToken());
                    trains[num][ch1] = 1;
                    break;
                case 2:
                    int ch2 = Integer.parseInt(st.nextToken());
                    trains[num][ch2] = 0;
                    break;
                case 3:
                    for(int j=20;j>=1;j--) {
                        trains[num][j] = trains[num][j-1];
                    } trains[num][0] = 0;
                    break;
                case 4:
                    for(int j=1;j<20;j++) {
                        trains[num][j] = trains[num][j+1];
                    } trains[num][20] = 0;
                    break;
            }
        }

        Set<String> uniqueTrains = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= 20; j++) {
                sb.append(trains[i][j]); // 각 열차의 상태를 문자열로 변환
            }
            uniqueTrains.add(sb.toString()); // Set에 추가하여 중복 여부 체크
        }
        System.out.println(uniqueTrains.size());

    }
}
