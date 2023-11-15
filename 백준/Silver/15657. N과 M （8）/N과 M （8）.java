

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static StringTokenizer st;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sel = new int[M];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		recursive(0, 0);
		System.out.println(sb.toString());
		
	}
	
	private static void recursive(int idx, int k) {
		if(k == M) {
			for(int i=0;i<sel.length;i++) {
				sb.append(sel[i] + " ");
			} sb.append("\n");
			return;
		}
		
		for(int i=idx;i<N;i++) {
			sel[k] = arr[i];
			recursive(i, k+1);
		}
	}

}
