
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = i + 1;
        }
        v = new boolean[N];
        int[] sel = new int[N];

        recursive(0, sel);
    }

    private static void recursive(int idx, int[] sel) {
        if(idx == N) {
            for(int i=0;i<N;i++) {
                System.out.print(sel[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                sel[idx] = arr[i];
                recursive(idx + 1, sel);
                v[i] = false;
            }
        }
    }
}
