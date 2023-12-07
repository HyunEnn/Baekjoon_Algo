

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int[] op;
	static int min;
	static int max;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		op = new int[4];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
		
	}

	private static void dfs(int idx, int sum) {
		// basis
		if(idx == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		// inductive
		for(int i=0;i<4;i++) {
			if(op[i] > 0) {
				op[i]--;
				switch(i) {
				case 0: dfs(idx + 1, sum + arr[idx]);
					break;
				case 1: dfs(idx + 1, sum - arr[idx]);
					break;
				case 2: dfs(idx + 1, sum * arr[idx]);
					break;
				case 3: dfs(idx + 1, sum / arr[idx]);
					break;
				}
				op[i]++;
			}
		}
	}

}
