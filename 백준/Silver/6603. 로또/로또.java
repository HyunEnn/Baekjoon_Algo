

import java.io.*;
import java.util.*;

public class Main {

	static int K;
	static int[] lotto;
	static boolean[] v;
	static int[] res;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			if(K == 0) System.exit(0);
			lotto = new int[K];
			v = new boolean[50];
			res = new int[7];
			
			for (int i = 0; i < K; i++) {
				lotto[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			System.out.println();
	}

	}

	private static void dfs(int idx, int c) {
		// basis
		if(c == 6) {
			for(int i=0;i<6;i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
			return;
		}
		// inductive
		for(int i=idx;i<K;i++) {
			if(!v[lotto[i]]) {
				v[lotto[i]] = true;
				res[c] = lotto[i];
				dfs(i+1, c+1);
				v[lotto[i]] = false;
//				dfs(i+1, c);
			}
		}
		
		}
		
	}
