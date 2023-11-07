

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;
	static int[] sel;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sel = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		recursive(0, 0);
		System.out.println(sb.toString());
	}
	private static void recursive(int idx, int k) {
		// basis
		if(k == M) {
			for(int i=0;i<sel.length;i++)
				sb.append(sel[i] + " ");
			sb.append("\n");
			return;
		}
		// inductive
		for(int i=idx;i<arr.length;i++) {
			sel[k] = arr[i];
			recursive(i+1, k+1);
		}
	}

}
