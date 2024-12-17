
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        boolean[] v = new boolean[line.length()];
        if(line.charAt(0) != 'q' || line.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }

        char[] ch = {'q','u','a','c','k'};
        int duckCount = 0;

        while(true) {
            boolean foundDuck = false;
            int sound = 0;

            for(int i=0;i<line.length();i++) {
                if(line.charAt(i) == ch[sound] && !v[i]) {
                    v[i] = true;
                    sound++;
                }
                if(sound == 5) {
                    foundDuck = true;
                    sound = 0;
                }
            }
            if(!foundDuck) break;
            duckCount++;
        }

        for(boolean b : v) {
            if(!b) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(duckCount);
    }
}
