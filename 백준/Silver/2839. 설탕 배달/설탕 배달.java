import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static int a, b;
	static int cnt;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Weight = Integer.parseInt(br.readLine());
		int cnt = 0;
		int answer = -1;

		while(true) {
			if(Weight % 5 == 0) {
				Weight /= 5;
				cnt+=Weight;
				break;
			}
			if(Weight <= 0) {
				cnt=-1;
				break;
			}
			Weight-=3;
			cnt++;

		}
		System.out.println(cnt);
//		System.out.println(cnt);
	}

}
