

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static StringTokenizer st;
	static List<Integer> arr;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
//		System.out.println(arr.size());
		
		dfs(0, 0);
		System.out.println(max);
	}
	private static void dfs(int idx, int sum) {
		// basis
		if(idx == N - 2) {
			max = Math.max(max, sum);
			return;
		}
		// inductive
		for(int i=1;i<arr.size()-1;i++) {
			int tmp = arr.get(i);
			int res = arr.get(i-1) * arr.get(i+1);
			arr.remove(i);
			dfs(idx + 1, sum + res);
			arr.add(i, tmp);
		}
		
	}

}
