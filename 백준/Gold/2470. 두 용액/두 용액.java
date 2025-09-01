
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int ansLeft, ansRight;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        twoPointer();
        System.out.println(ansLeft + " " + ansRight);
    }

    public static void twoPointer() {
        int left = 0, right = N - 1;
        ansLeft = 0; ansRight = 0;
        int ans = Integer.MAX_VALUE;
        while(left < right) {
            int sum = arr[left] + arr[right];
            int ch = Math.abs(sum);
            if(ans > ch) {
                ans = ch;
                ansLeft = arr[left];
                ansRight = arr[right];
                if(ch == 0) break;
            }

            if(sum < 0) left++;
            else right--;
        }
    }
}
