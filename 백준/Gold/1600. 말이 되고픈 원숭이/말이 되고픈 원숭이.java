
import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H; // w: 가로, h: 세로, k: 이동 가능 횟수
	static int[][] map;
	static boolean v[][][]; // 방문 배열
	static int[] dr = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	static int res;
	static class Point {
		private int r, c, cnt, k;
		public Point(int r, int c, int cnt, int k) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine()); // 말처럼 이동 가능 수
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 열
		H = Integer.parseInt(st.nextToken()); // 행
		res = Integer.MAX_VALUE; // 결과값은 최소값을 찾는다.
		map = new int[H][W];  // 지도
		v = new boolean[H][W][K+1]; // 방문 배열
		createMap();
		
		bfs();
		if(res == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(res);
		}
	}
	private static void bfs() {
		Queue<Point> Q = new ArrayDeque<>();
		Q.offer(new Point(0, 0, 0, K));
		v[0][0][K] = true;
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			if(p.r == H-1 && p.c == W-1) {
				res = Math.min(res, p.cnt);
				return;
			}
			
			for(int i=0;i<( p.k > 0 ? 12 : 4);i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				int nk = i < 4 ? p.k : p.k -1;
				if(nr >= 0 && nr < H && nc >= 0 && nc < W ) {
					if(!v[nr][nc][nk] && map[nr][nc] == 0) {
						v[nr][nc][nk] = true;
						Q.offer(new Point(nr, nc, p.cnt+1, nk));
					}
				}
			}
 		}
		
	}
	private static void createMap() throws IOException {
		// TODO Auto-generated method stub
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
