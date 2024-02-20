
import java.io.*;
import java.util.*;

public class Main {

	static int[][] arr;
	static int[] res;
	static int[] parent;
	static int N, M;
	static StringTokenizer st;
	static String answer = "YES";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 수 
		M = Integer.parseInt(br.readLine()); // 간선 개수
		arr = new int[N+1][N+1];
		parent = new int[N+1]; // 부모 노드
		res = new int[M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 도시의 정점간 간선 연결
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) {
			res[i] = Integer.parseInt(st.nextToken());
		} // 정점들 접근 순서
		
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		} // 부모 노드 초기화
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(arr[i][j] == 1) {
					union(i, j);
				}
			}
		}
		
		int x = find(res[1]);
		for(int i=2;i<=M;i++) {
			if(x != find(res[i])) 
				answer = "NO";
		}
		System.out.println(answer);
	}
	/**
	 * union - find : 부모 노드 초기화 
	 * 1. 행 순서대로 탐색하면서, 연결되어 있는 정점을 보고, union 한다.
	 * 2. union에서 각각의 부모노드를 조회하고, 같으면 패스, 다르면, 제일 작은 부모
	 * 노드로 통일한다. 
	 * 3. res의 들어온 배열의 개수만큼 맞으면 YES 아니면 NO 
	 */
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			if(a > b) parent[a] = b;
			else parent[b] = a;
		}
	}
	
	public static int find(int idx) {
		if(idx == parent[idx])
			return idx;
		else
			return find(parent[idx]);
	}

}
