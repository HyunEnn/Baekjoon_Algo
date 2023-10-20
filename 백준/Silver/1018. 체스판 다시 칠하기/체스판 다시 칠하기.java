
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[][] map;
	static StringTokenizer st;
	static int min = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		
		createMap();
//		printMap();

		int N_row = N-7;
		int M_col = M-7;
		for(int i=0;i<N_row;i++) {
			for(int j=0;j<M_col;j++) {
				find(i, j);
			}
		}
		System.out.println(min);
	}
	
	private static void find(int r, int c) {
		int end_r = r+8;
		int end_c = c+8;
		int cnt = 0;
		
		boolean check = map[r][c]; // (0, 0) 값이 W, B인지 체크 
		
		for(int i = r ; i < end_r ; i++) {
			for(int j = c ; j < end_c ; j++) {
				if(map[i][j] != check) {
					cnt++;
				}
				check = !check;
			}
			check = !check;
		}
		
		cnt = Math.min(cnt, 64 - cnt);
		min = Math.min(min, cnt);
	}

	private static void printMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void createMap() throws IOException {
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				if(line.charAt(j) == 'W') {
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}
			}
		}
	}

}
