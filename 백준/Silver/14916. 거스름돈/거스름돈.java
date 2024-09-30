
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int coins = 0;
        while(n > 0) {
            if(n < 5 && n % 2 != 0) {
                coins = -1;
                break;
            }
            if(n % 5 != 0) {
                n -= 2;
                coins++;
//                System.out.println("2 빼기 " + " , n = " + n + " , " + coins);
            } else {
                coins += n / 5;
                n = 0;
            }
        }
        System.out.println(coins);
    }
}
