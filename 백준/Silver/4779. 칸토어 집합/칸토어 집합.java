import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            len = new StringBuilder();
            int l = (int)Math.pow(3, n);
            fillLen(l);
            recursive(0, l);
            System.out.println(len);
        }
    }

    public static void fillLen(int size) {

        for(int i=0;i<size;i++) {
            len.append('-');
        }
    }

    public static void recursive(int start, int idx) {

        if(idx == 1) {
            return;
        }

        int newIdx = idx/3;

        // 여기가 가운데 정렬 기준
        for(int i=start + newIdx;i<start + newIdx * 2;i++) {
            len.setCharAt(i, ' ');
        }
        // 1번 정렬
        recursive(start, newIdx);
        // 3번 정렬
        recursive(start + newIdx * 2, newIdx);

    }
}
