
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static String[] str;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }

        int totalCnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str[0].length();i++) {
            int cnt = 0;
            int[] ch = new int[26];
            int max = 0;
            char curr = 0;

            // 알파벳 배열을 통해서 값을 넣는다.
            for(int j=0;j<str.length;j++) {
                ch[str[j].charAt(i)-'A']++;
            }

//            for(int j=0;j<ch.length;j++) {
//                System.out.print(j+"번째: " + ch[j] + " ");
//            }
//            System.out.println();

            // 저장된 배열 중 큰 값을 찾아서 뽑음
            for(int k=0;k<ch.length;k++) {
                if(ch[k] > max) {
                    max = ch[k];
                    curr = (char)('A' + k);
//                    System.out.println(curr);
                }
            }

            // total 값에 max 를 더해주고, 현재 위치에 대한 값을 저장
            totalCnt += str.length - max;
            sb.append(curr);
        }

        System.out.println(sb.toString());
        System.out.println(totalCnt);
    }
}
