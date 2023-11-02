

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Integer> list[];
	static boolean[] v; // 방문체크
	static int[] parent; // 노드의 부모
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		v = new boolean[N+1];
		list = new ArrayList[N+1];
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		} // 각각의 원소 노드의 트리를 연결해줘야 함

		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b); // 무방향 그래프니까
			list[b].add(a); // a->b, b->a
		}
		
		dfs(1); // 최상위 부모가 됨.
		
		for(int i=2;i<=N;i++) {
			System.out.println(parent[i]);
		}
	}
	
	private static void dfs(int idx) {
		v[idx] = true;
		for(int i=0;i<list[idx].size();i++) {
			int child = list[idx].get(i);
			if(!v[child]) {
				parent[child] = idx;
				dfs(child);
			}
		}
	}

}
