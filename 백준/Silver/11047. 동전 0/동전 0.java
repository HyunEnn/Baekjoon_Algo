
import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] money;
	static int res;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		money = new int[N];
		res = 0;
		
		for(int i=0;i<N;i++) {
			money[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(money); // 4200 % 1000 = 4
//		System.out.println(Arrays.toString(money));
		for(int i=N-1;i>=0;i--) {
			if(money[i] > K) continue;
			else {
				res += K / money[i]; // 4
				K = K % money[i]; // 200
			}
		}
		System.out.println(res);

	}

}
