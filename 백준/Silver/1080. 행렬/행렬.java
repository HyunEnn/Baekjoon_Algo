
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr1, arr2;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        arr2 = new int[N][M];
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0;j<M;j++) {
                arr1[i][j] = line.charAt(j) - '0';
            }
        }

        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0;j<M;j++) {
                arr2[i][j] = line.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for(int i=0;i<N-2;i++) {
            for(int j=0;j<M-2;j++) {
                if(arr1[i][j] != arr2[i][j]) {
                    change(i, j);
                    cnt++;
                }
            }
        }

        if(isSame(arr1, arr2)) System.out.println(cnt);
        else System.out.println(-1);
    }

    private static void change(int r, int c) {
        for(int i=r;i<r+3;i++) {
            for(int j=c;j<c+3;j++) {
                arr1[i][j] = 1 - arr1[i][j];
            }
        }
    }

    private static boolean isSame(int[][] arr1, int[][] arr2) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(arr1[i][j] != arr2[i][j]) return false;
            }
        }

        return true;
    }
}
