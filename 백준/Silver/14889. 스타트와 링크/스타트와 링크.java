import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] map;
	static StringTokenizer st;
	static boolean[] v;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		printMap();
		dfs(0, 0);
		System.out.println(min);
		
	}
	private static void dfs(int idx, int k) {
		if(k == N/2) {
			diff();
			return;
		}
		
		for(int i=idx;i<N;i++) {
			if(v[i] != true) {
				v[i] = true;
				dfs(i+1, k+1);
				v[i] = false;
			} // 조합을 통해, 1번의 팀이 정해지면 1번의 팀을 제외한 나머지 idx값들을 통해서 남은 인원의 팀의 점수를 비교한다.
		}
		
	}
	private static void diff() {
		int teamA = 0;
		int teamB = 0;
		
		for(int i=0;i<N;i++) { 
			for(int j=i+1;j<N;j++) { // 0, 1번이 팀일 경우 map[0][1] , map[1][0] 제외
				if(v[i] == true && v[j] == true) {
					teamA += map[i][j];
					teamA += map[j][i];
				} else if(v[i] != true && v[j] != true){ // 둘 다 아닐 경우에, 상대편으로 처리, 2, 3번 일경우에 map[2][3] , map[3][2] 제외
					teamB += map[i][j];
					teamB += map[j][i];
				}
			}
		}
		
		min = Math.min(min, Math.abs(teamA - teamB));
		
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
