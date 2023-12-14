
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] v;
	static int ans;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 = 열
 		M = Integer.parseInt(st.nextToken()); // 가로 = 행
		map = new int[N][M];
		v = new boolean[N][M];
		ans = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		printMap();
		
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				v[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				v[i][j] = false;
				exOther(i, j);
			}
		}
		System.out.println(ans);

	}
	private static void printMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void exOther(int r, int c) {
		// ㅏ 모양
		if(r < N - 2 && c < M- 1) {
			ans = Math.max(ans, map[r][c] + map[r+1][c] + 
					map[r+2][c] + map[r+1][c+1]);
		}
		// ㅓ 모양
		if(r < N - 2 && c > 0) {
			ans = Math.max(ans, map[r][c] + map[r+1][c] +
					map[r+2][c] + map[r+1][c-1]);
		}
		// ㅗ 모양
		if(r > 0 && c < M - 2) {
			ans = Math.max(ans, map[r][c] + map[r][c+1] +
					map[r][c+2] + map[r-1][c+1]);
		}
		// ㅜ 모양
		if(r < N - 1 && c < M - 2) {
			ans = Math.max(ans, map[r][c] + map[r][c+1] +
					map[r][c+2] + map[r+1][c+1]);
		}
	}
	private static void dfs(int r, int c, int cnt, int sum) {
		// basis
		if(cnt == 4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// inductive 
		for(int k=0;k<4;k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || v[nr][nc])
				continue;
			
			v[nr][nc] = true;
			dfs(nr, nc, cnt+1, sum+map[nr][nc]);
			v[nr][nc] = false;
		}
	}

}
