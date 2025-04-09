
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, Q;
    static int[][] arr;
    static int[][] sumArr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        sumArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        prefix();

        // R-1, C-1
        for(int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken()) - 1;
            int startC = Integer.parseInt(st.nextToken()) - 1;
            int endR = Integer.parseInt(st.nextToken()) - 1;
            int endC = Integer.parseInt(st.nextToken()) - 1;

            // 현재 누적합에서 포함되지 않는 열 구간의 합과 행 구간의 합을 빼주고, 겹쳐서 뺀 구간은 더해준다.
            int total = sumArr[endR][endC];
            if(startR > 0) total -= sumArr[startR-1][endC];
            if(startC > 0) total -= sumArr[endR][startC-1];
            if(startR > 0 && startC > 0) total += sumArr[startR-1][startC-1];

            int avg = (endR-startR+1) * (endC-startC+1);
            System.out.println(total / avg);
        }
    }

    public static void prefix() {
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(i == 0 && j == 0) sumArr[i][j] = arr[i][j];
                else if(i == 0) sumArr[i][j] = sumArr[i][j-1] + arr[i][j];
                else if(j == 0) sumArr[i][j] = sumArr[i-1][j] + arr[i][j];

                if(i > 0 && j > 0) sumArr[i][j] = arr[i][j] + sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];

//                System.out.print(sumArr[i][j] + " ");
            }
//            System.out.println();
        }
    }
}
