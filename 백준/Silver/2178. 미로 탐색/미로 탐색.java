import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1}; // 왼쪽 아래 오른쪽 위
	static int[] dc = {1, 0, -1, 0}; //
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Point {
		private int r,c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		createMap(); // 지도 생성
		bfs();
		System.out.println(map[N-1][M-1]);
	}
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Point> Q = new ArrayDeque<>();
		Q.offer(new Point(0,0));
		
		int level = 1;
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 
						&& nc < M && map[nr][nc] == 1) {
					map[nr][nc] = map[p.r][p.c]+1;
					Q.offer(new Point(nr, nc));
					
				}
			}
			
			
		}
	}
	private static void createMap() throws IOException {
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
//		for(int i=0;i<N;i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

}
