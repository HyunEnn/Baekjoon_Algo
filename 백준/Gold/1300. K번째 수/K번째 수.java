

import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[][] map;
	static int[] B;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		long start = 1, end = K;
		long mid;
		long ans = 0;
		while(start <= end) {
			mid = (start + end) / 2;
			long cnt = 0;
			for(int i=1;i<=N;i++) {
				cnt += Math.min(mid / i, N);
			}
			if(cnt < K) 
				start = mid + 1;
			else {
				ans = mid;
				end = mid - 1;
			}
		}
		System.out.println(ans);

	}
	private static void printMap() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
