import java.io.*;
import java.util.*;

public class Main {

	static long N;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		long res = N;
		// N = 10 
		// 오일러 피 => 
		for(int i=2;i<=Math.sqrt(N);i++) {
			if(N%i==0) res = res - res/i;
			while(N%i==0) N /= i;
		}
		if(N>1) res = res - res/N;
		System.out.println(res);

	}

}
