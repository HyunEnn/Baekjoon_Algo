
import java.io.*;
import java.util.*;

public class Main {

//	static class Node {
//		int v, e;
//		public Node(int v, int e) {
//			this.v = v;
//			this.e = e;
//		}
//	}
	static boolean[] visited;
	static int N, M;
	static int flag;
	static int answer;
	static List<Integer>[] list;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계 간선
		list = new ArrayList[N];
		visited = new boolean[N];
		flag = 0;
		answer = 0;
		
		for(int i=0;i<N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[v].add(e);
			list[e].add(v);
		}
		
//		BFS(0);
		for(int i=0;i<N;i++) {
			DFS(i, 1);
			if(flag == 1)  {
				answer=1;
				break;
			}
		}
		System.out.println(answer);
	}
	private static void DFS(int node, int depth) {
		
		if(depth == 5) {
			flag = 1;
			return;
		}

		visited[node] = true;
		
		for(int i=0;i<list[node].size();i++) {
			if(!visited[list[node].get(i)])
				DFS(list[node].get(i), depth+1);
		}
		visited[node] = false;
	}
	
//	private static void BFS(int node) {
//		Queue<Integer> Q = new ArrayDeque<>();
//		Q.offer(node);
//		visited[node] = true; // 뽑은 값을 방문처리
//		
//		while(!Q.isEmpty()) {
//			int newNode = Q.poll(); // Node 값을 저장
//			for(int i=0;i<list[newNode].size();i++) {
//				if(!visited[list[newNode].get(i)]) {
//					Q.offer(list[newNode].get(i));
//				}
//			}
//			
//		}
//	}

}
