
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static boolean[] v;
	static int min = Integer.MAX_VALUE;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 0);
		System.out.println(min);
	}
	private static void DFS(int idx, int k) {
		// basis
		if(k == N) {
			diff();
			return;
		}
		
		// inductive
//		for(int i=idx;i<N;i++) {
//			if(v[i]) continue;
//			v[i] = true;
//			DFS(i, k + 1);
//			v[i] = false;
//			DFS(i , k+1);
//		}
		v[k] = true;
		DFS(idx, k+1);
		v[k] = false;
		DFS(idx, k+1);
	}
	
	private static void diff() {
		int start_team = 0;
		int link_team = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				if(v[i] && v[j]) {
					start_team += map[i][j];
					start_team += map[j][i];
				}
				else if(!v[i] && !v[j]) {
					link_team += map[i][j];
					link_team += map[j][i];
				}
			}
		}
		
		min = Math.min(min, Math.abs(start_team - link_team));
	}

}
