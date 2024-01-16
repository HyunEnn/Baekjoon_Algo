
import java.io.*;
import java.util.*;

public class Main {

	static int N,M,K,X;
	static StringTokenizer st;
	static int[] v;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1]; // list의 ArrayList를 N만큼 생성
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		v = new int[N+1];
		BFS(X);
		int cnt = 0;
		for(int i=1;i<N+1;i++) {
			if(v[i] == K+1) {
				cnt++;
				System.out.println(i);
			}
		}
		if(cnt == 0) System.out.println("-1");
		
	}
	
	private static void BFS(int idx) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(idx);
		v[idx]++;
		while(!Q.isEmpty()) {
			int p = Q.poll();
			for(int i=0;i<list[p].size();i++) {
				// 1 -> 2, 3
				int x = list[p].get(i);
				if(v[x] == 0) {
					v[x] = v[p] + 1;
					Q.offer(x);
				}
			}
		}
	}

}
