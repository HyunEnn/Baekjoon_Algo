
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] card;
    static int[] answer;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        card = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(card);
        M = Integer.parseInt(br.readLine());
        answer = new int[M];
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++) {
            answer[i] = Integer.parseInt(st.nextToken());
            sb.append(BinarySearch(answer[i])).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static int BinarySearch(int target) {
        return UpperBound(target) - LowerBound(target);
    }

    public static int UpperBound(int target) {
        int left = 0;
        int right = card.length - 1;
        int minVal = card.length;
        while(left <= right) {
            int mid = ( left + right ) / 2;
            if(card[mid] > target) {
                right = mid - 1;
                minVal = Math.min(minVal, mid);
            } else
                left = mid + 1;
        }
        return minVal;
    }

    public static int LowerBound(int target) {
        int left = 0;
        int right = card.length - 1;
        int minVal = card.length;
        while(left <= right) {
            int mid = ( left + right ) / 2;
            if(card[mid] >= target) {
                right = mid - 1;
                minVal = Math.min(minVal, mid);
            } else
                left = mid + 1;
        }
        return minVal;
    }
}
