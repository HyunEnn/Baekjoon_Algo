
import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r, c, cnt;
		Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int R, C;
	static Queue<Point> Q; // 지훈이
	static Queue<Point> fire; // 불
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static StringTokenizer st;
	static int fireMap[][];
	static int jihoonMap[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Q = new ArrayDeque<>();
		fire = new ArrayDeque<>();
		map = new char[R][C]; 
		fireMap = new int[R][C]; // 불이 퍼질 곳
		jihoonMap = new int[R][C]; // 지훈이가 이동할 곳
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'J') {
					Q.offer(new Point(i, j, 0));
					jihoonMap[i][j] = 1;
				}
				if(map[i][j] == 'F') {
					fire.offer(new Point(i, j, 0));
					fireMap[i][j] = 1;
				}
			}
		}
		
		while(!fire.isEmpty()) {
			Point f = fire.poll();
			for(int i=0;i<4;i++) {
				int nr = f.r + dr[i];
				int nc = f.c + dc[i];
				if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
					if(fireMap[nr][nc] == 0 && map[nr][nc] != '#') {
						fireMap[nr][nc] = fireMap[f.r][f.c] + 1;
						fire.offer(new Point(nr, nc, f.cnt + 1));
					}
				}
			}
		}
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			for(int i=0;i<4;i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 0 && nr < R && nc >=0 && nc < C) { // 범위 안에 들어가 있으며
					if(jihoonMap[nr][nc] == 0 && map[nr][nc] != '#') { // 아직 접근하지 않은 구역이면서, 벽이 아니여야 한다
						if(jihoonMap[p.r][p.c] + 1 < fireMap[nr][nc]) { // 불이 퍼진 순서보다, 지훈이가 먼저 갈수 있으면 이동한다.
							jihoonMap[nr][nc] = jihoonMap[p.r][p.c] + 1;
							Q.offer(new Point(nr, nc, p.cnt + 1));							
						} else if(fireMap[nr][nc] == 0) { // 만약에, 불이 없는 곳이면 그냥 접근하면 된다. 
							jihoonMap[nr][nc] = jihoonMap[p.r][p.c]+1;
							Q.offer(new Point(nr, nc, p.cnt + 1));
						}
					}
				} else {
					System.out.println(p.cnt + 1);
					System.exit(0);
				}
			}
		}
		System.out.println("IMPOSSIBLE");
		
	}
}
