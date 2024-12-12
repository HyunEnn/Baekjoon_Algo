
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String f = br.readLine();
        boolean flag = false;
        // f랑 s를 한 1000번 반복해서 생성?
        char[] newS = new char[s.length()*1000];
        char[] newF = new char[s.length()*1000];

        for(int i=0;i<newS.length;i++) {
            newS[i] = s.charAt(i % s.length());
        }
        for(int i=0;i<newF.length;i++) {
            newF[i] = f.charAt(i % f.length());
        }

        for(int i=0;i<newS.length;i++) {
            if(newS[i] != newF[i]) {
                flag = true;
                break;
            }
        }

        if(flag)
            System.out.println(0);
        else
            System.out.println(1);
    }
}
