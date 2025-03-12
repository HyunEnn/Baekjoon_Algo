
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int maxB = 0;

        for(int i=0;i<line.length();i++) {
            if(line.charAt(i)=='b') maxB++;
        }

        int currentB = 0;
        for(int i=0;i<maxB;i++) {
            if(line.charAt(i)=='b') currentB++;
        }

        int st = 0;
        int ans = currentB;
        for(int i=maxB;i<line.length() + maxB;i++) {
            if(line.charAt(i % line.length()) == 'b') currentB++;
            if(line.charAt(st % line.length()) == 'b') currentB--;

            ans = Math.max(ans, currentB);
            st++;
        }

        System.out.println(maxB - ans);
    }
    // aabbaaabaaba -> 2, 3, 7, 10 (정렬)?
    // abababababababa -> 1, 3, 5, 7, 9, 11, 13

    // 투 포인터 알고리즘으로 b가 최대로 등장하는 인덱스 갯수를 세야함
    // 7개니까 ab가 7개일 때, b가 최대로 등장하는 인덱스를 찾는다?
    // 투 포인터로 지정한 인덱스 번호를 제외한 나머지에서 교체 가능한 갯수를 찾으면 정답
}
