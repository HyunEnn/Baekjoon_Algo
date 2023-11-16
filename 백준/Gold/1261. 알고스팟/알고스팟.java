import java.io.*;
import java.util.*;

public class Main {

	static int N,M; // 가로, 세로
	static class Point implements Comparable<Point>{
		int r, c, w;
		Point(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.w - o.w;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static StringTokenizer st;
	static Queue<Point> Q; // bfs Q
	static int[][] map; // 입력받을 미로
	static boolean[][] v;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = new PriorityQueue<Point>();
		v = new boolean[M][N];
		map = new int[M][N];
		max = Integer.MAX_VALUE;
		for(int i=0;i<M;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
//		printMap();
		bfs();
		System.out.println(max);
	}
	
	private static void bfs() {
		Q.offer(new Point(0, 0, 0));
		v[0][0] = true;
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			
			if(p.r == M-1 && p.c == N-1) {
				max= Math.min(max, p.w);
				return;
			}
			for(int i=0;i<4;i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 0 && nr < M && nc >= 0 && nc < N && !v[nr][nc]) {
					v[nr][nc] = true;
					if(map[nr][nc] == 0) {
						Q.offer(new Point(nr, nc, p.w));
					} else {
						Q.offer(new Point(nr, nc, p.w+1));
					}
				}
			}
		}
		
	}
	private static void printMap() {
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
