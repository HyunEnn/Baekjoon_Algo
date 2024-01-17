
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static StringTokenizer st;
	static ArrayList<Integer>[] list;
	static boolean[] v;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		list = new ArrayList[N+1];
		ans = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>(); // i마다의 Arraylist 생성
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		for(int i=1;i<=N;i++) {
			v = new boolean[N+1];
			BFS(i); // 1, 2, 3, 4, 5
		}
		int max_value = 0;
		for(int i=1;i<=N;i++) {
			max_value = Math.max(max_value, ans[i]);
		}
		
		for(int i=1;i<=N;i++) {
			if(ans[i] == max_value) {
				System.out.print(i + " ");
			}
		}

	}
	
	private static void BFS(int idx) {
		// 3 -> 1, 2
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(idx);
		while(!Q.isEmpty()) {
			int p = Q.poll();
			v[p] = true;
			for(int i=0;i<list[p].size();i++) {
				int x = list[p].get(i);
				if(!v[x]) {
					v[x] = true;
					ans[x]++;
					Q.offer(x);
				}
			}
		}
	}

}
