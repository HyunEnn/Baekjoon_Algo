
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] duplicateMap;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int cnt;
	static class Point {
		private int r, c;
		private Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0;
		map = new int[N][M]; // N * M 맵 
		duplicateMap = new int[N][M]; // N * M 복사맵
		if(N < 3 || M > 8) {
			return;
		}
		
		createMap(); // 지도 생성
		dfs(0);
		System.out.println(cnt);
	}
	private static void dfs(int idx) {
		// basis
		if(idx==3) {
			bfs();
			cnt = Math.max(cnt, calculateZero());
			return;
		}
		// inductive
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(idx+1); // idx 값을 1씩 증가시켜서 개수 체크
					map[i][j] = 0;
				}
			}
		}
		
	}
	private static int calculateZero() {
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(duplicateMap[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
	private static void bfs() {
		/*
		 * bfs를 돌기 전에, 매번 막대를 세워서 map을 돌아야 하니까
		 * map을 매번 새로 복사해서 사용하자
		 */
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				duplicateMap[i][j] = map[i][j];
			}
		}
		
		Queue<Point> Q = new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(duplicateMap[i][j] == 2) {
					Q.offer(new Point(i, j));
				}
			}
		}
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			for(int i=0;i<4;i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if(duplicateMap[nr][nc] == 0) {
						duplicateMap[nr][nc] = 2;
						Q.offer(new Point(nr, nc));
					}
				}
			}
		}
		
		
	}
	private static void createMap() throws IOException {
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} 
	}

}
