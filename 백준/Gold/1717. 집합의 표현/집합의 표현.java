import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] unionFind;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		unionFind = new int[N+1][2];
		for(int i=1;i<=N;i++) {
			unionFind[i][0] = i; // index
			unionFind[i][1] = i; // value
		}
//		print();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); // u or f
			int a = Integer.parseInt(st.nextToken()); // 앞
			int b = Integer.parseInt(st.nextToken()); // 뒤
			if(c == 0) { // 0 이면 union, 1 이면 find
				union(a, b);
			} 
			else if(c == 1) {
				if(sameRoot(a, b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
//		print();
	}
	
	public static void union(int x, int y) {
		x = find(x); // 7
		y = find(y); // 6
		if(x != y) {
			if(x > y) unionFind[x][1] = y;
			else unionFind[y][1] = x;
		}
	}
	
	public static int find(int idx) {
		if(unionFind[idx][1] == idx) 
			return idx;
		else { // index - value 가 다르면
			int res = find(unionFind[idx][1]); // value의 값을 탐색
			return unionFind[idx][1] = res; // 탐색한 값으로 변경
		}
			
	}
	
	public static boolean sameRoot(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) 
			return true;
		else 
			return false;
	}

	public static void print() {
		for(int i=1;i<=N;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(unionFind[i][j] + " ");
			}
			System.out.println();
		}
	}
}
