

import java.io.*;
import java.util.*;

public class Main {

	static int V, E;
	static class Vertex {
		int r, c;
		Vertex(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static ArrayList<Integer>[] list;
	static int cnt;
	static boolean[] v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			list[i] = new ArrayList<>(); // 리스트 생성
		}
		cnt = 0; // 연결 개수
		v = new boolean[V+1]; // 체크 배열 
		
		for(int i=1;i<=E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작 노드 
			int b = Integer.parseInt(st.nextToken()); // 연결 노드
			list[a].add(b);
			list[b].add(a);
		}
		
//		System.out.println(list[1].get(1));
		
//		for(int i=1;i<list.length;i++) {
//			System.out.println(list[i]);
//		}
		
		for(int i=1;i<list.length;i++) {
			if(!v[i]) {
				BFS(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	private static void BFS(int idx) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(idx);
		v[idx] = true;
		
		while(!Q.isEmpty()) {
			int p = Q.poll();
			for(int i=0;i<list[p].size();i++) {
				int check = list[p].get(i);
				if(!v[check]) {
					Q.offer(check);
					v[check] = true;
				}
			}
		}
	}

}
