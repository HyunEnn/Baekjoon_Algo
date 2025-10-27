
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static boolean[] init, end;
    static boolean[] first, second;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String line2 = br.readLine();
        init = new boolean[N];
        end = new boolean[N];
        first = new boolean[N];
        second = new boolean[N];

        for(int i=0;i<N;i++) {
            if(line.charAt(i) == '1') init[i] = true;
            else if(line.charAt(i) == '0') init[i] = false;

            if(line2.charAt(i) == '1') end[i] = true;
            else if(line2.charAt(i) == '0') end[i] = false;
        }
        // 초기화
        first = init.clone(); second = init.clone();

        // 0번 스위치를 누른다
        first[0] = !first[0]; first[1] = !first[1];
        // 0번 스위치를 안누른다
        int ans = 1;
        int ans2 = 0;
        for(int i=1;i<N-1;i++) {
            if(first[i-1] != end[i-1]) {
                ans++;
                first[i-1] = !first[i-1];
                first[i] = !first[i];
                first[i+1] = !first[i+1];
            }

            if(second[i-1] != end[i-1]) {
                ans2++;
                second[i-1] = !second[i-1];
                second[i] = !second[i];
                second[i+1] = !second[i+1];
            }
        }

        if(N > 2) {
            if(first[N-2] != end[N-2]) {
                ans++;
                first[N-2] = !first[N-2];
                first[N-1] = !first[N-1];
            }

            if(second[N-2] != end[N-2]) {
                ans2++;
                second[N-2] = !second[N-2];
                second[N-1] = !second[N-1];
            }
        }

//        for(int i=0;i<N;i++) {
//            System.out.print(first[i] + " ");
//        }
//        System.out.println();
//        for(int i=0;i<N;i++) {
//            System.out.print(second[i] + " ");
//        }
//        System.out.println();

        boolean flag = true;
        boolean flag2 = true;
        for(int i=0;i<N;i++) {
            if(end[i] != first[i]) {
                flag = false;
                break;
            }
        }
        for(int i=0;i<N;i++) {
            if(end[i] != second[i]) {
                flag2 = false;
                break;
            }
        }
        if(flag && flag2) {
            System.out.println(Math.min(ans, ans2));
        } else if(flag && !flag2) {
            System.out.println(ans);
        } else if(!flag && flag2) {
            System.out.println(ans2);
        } else {
            System.out.println(-1);
        }
    }
    /**
     * 000 -> 110 000
     * 001 000
     * 010 011
     * 0번 인덱스를 누르고 진행하는 것과 누르지 않는것 2개의 경우를 가지고 탐색
     * 010
     * 011
     */
}
