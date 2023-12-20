

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Coin {
		int r, c;

		Coin(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class TwoCoin {
		int r1, c1, r2, c2, cnt;

		public TwoCoin(int r1, int c1, int r2, int c2, int cnt) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int min;
	static boolean[][][][] v;
	static Coin[] coin;
	static Queue<TwoCoin> Q;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		coin = new Coin[2];
		int coinIdx = 0;
		min = Integer.MAX_VALUE;
		Q = new ArrayDeque<>();
//		flag = 2; // 동전의 개수가 1이 되면 , 종료
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'o') {
					coin[coinIdx++] = new Coin(i, j);
				}
			}
		}
		v = new boolean[N][M][N][M];
		bfs();
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
//		dfs(0);

//		printMap();
	}

	private static void bfs() {
		Q.offer(new TwoCoin(coin[0].r, coin[0].c, coin[1].r, coin[1].c, 0));
		v[coin[0].r][coin[0].c][coin[1].r][coin[1].c] = true;

		while (!Q.isEmpty()) {
			TwoCoin tc = Q.poll();
			if(tc.cnt >= 10) {
				min = -1;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr1 = tc.r1 + dr[i];
				int nc1 = tc.c1 + dc[i];
				int nr2 = tc.r2 + dr[i];
				int nc2 = tc.c2 + dc[i];

				if(!canMoveCoin(nr1, nc1)) {
					nr1 = tc.r1;
					nc1 = tc.c1;
				}
				
				if(!canMoveCoin(nr2, nc2)) {
					nr2 = tc.r2;
					nc2 = tc.c2;
				}
				
				int flag = 0;
				if(nr1 >= 0 && nc1 >= 0 && nr1 < N && nc1 < M) flag++;
				if(nr2 >= 0 && nc2 >= 0 && nr2 < N && nc2 < M) flag++;
				
				if(flag == 1) {
					min = tc.cnt + 1;
					return;
				}
				if(flag == 2 && !v[nr1][nc1][nr2][nc2]) {
					v[nr1][nc1][nr2][nc2] = true;
					Q.offer(new TwoCoin(nr1, nc1, nr2, nc2, tc.cnt+1));
				}
			}
		}
	}

	private static boolean canMoveCoin(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == '#') {
			return false;
		}
		return true;
	}

//	private static void dfs(int cnt) {
//		// basis
//		if(Q.size() == 1) {
//			min = Math.min(min, cnt); // cnt 도는 최소 갯수
//			return;
//		}
//		// inductive 4방 탐색 하면서, 확인
//		for(int i=0;i<4;i++) {
//			Point p1 = Q.poll(); // 1번째 동전
//			Point p2 = Q.poll(); // 2번째 동전
//			int nr1 = p1.r + dr[i];
//			int nc1 = p1.c + dc[i];
//			int nr2 = p2.r + dr[i];
//			int nc2 = p2.c + dc[i];
//			
//			if((nr1 >= 0 && nr1 < N && nc1 >=0 && nc1 < M) ||
//					(nr2 >= 0 && nr2 < N && nc2 >=0 && nc2 < M)) {
//				
//				if(map[nr1][nc1] == '.')
//				
//				if(map[nr1][nc1] == '#') {
//					Q.offer(new Point(p1.r, p1.c)); // 벽이면 이동 금지
//					if(map[nr2][nc2] == '.') {
//						Q.offer(new Point(nr2, nc2)); // 만약에 2번째 동전은 이동 공간있으면 이동
//						dfs(cnt + 1);
//						Q.poll();
//						Q.offer(new Point(p2.r, p2.c)); // dfs를 종료하고 돌아오면, 원래대로 복원
//					} else if(map[nr2][nc2] =='o') {
//						Q.offer(new Point(p2.r, p2.c)); // 1번 동전이 있던 자리면 이동 중지
//						dfs(cnt);
//					} else {
//						dfs(cnt + 1);
//						Q.offer(new Point(p2.r, p2.c));
//					}
//					// 떨어지는 자리면 그냥 그대로 떨어지게 냅둠
//				}
//				if(map[nr2][nc2] == '#') {
//					Q.offer(new Point(p2.r, p2.c));
//					if(map[nr1][nc1] == '.') {
//						Q.offer(new Point(nr1, nc1));
//						dfs(cnt + 1);
//						Q.poll();
//						Q.offer(new Point(p1.r, p1.c));
//					} else if(map[nr1][nc1] == 'o') {
//						Q.offer(new Point(p1.r, p1.c));
//						dfs(cnt);
//					} else {
//						dfs(cnt + 1);
//						Q.offer(new Point(p1.r, p1.c));
//					}
//				}
//			}
//			
//			
//		}
//	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
