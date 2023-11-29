import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		dfs(2, 1);
		dfs(3, 1);
		dfs(5, 1);
		dfs(7, 1);
		
		System.out.println(sb.toString());
	}
	private static void dfs(int idx, int k) {
		// basis
		if(k == N) {
			if(isPrime(idx)) {
				sb.append(idx + "\n");
				return;				
			}
		}
		
		// inductive
		for(int i=1;i<=9;i++) {
			if(i%2 == 0) {
				continue;
			} // 들어오는 자리의 숫자가 소수인지 체크
			
			if(isPrime(idx*10+i)) {
				dfs(idx * 10 +i, k+1);
			} // 들어온 자리 숫자를 합한 전체 숫자가 소수인지 체크하고 dfs
		}
	}
	
	private static boolean isPrime(int num) {
		for(int i=2;i<=num/2;i++) {
			if(num % i == 0) 
				return false;
		}
		return true;
	}

}
