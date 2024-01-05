
import java.io.*;
import java.util.*;

public class Main {

	static int N, M, R;
	static ArrayList<Integer>[] list;
	static boolean[] v;
	static int[] seq;
	static int cnt;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		seq = new int[N+1];
		v = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i=1;i<=N;i++) {
			Collections.sort(list[i]);
//			for(int j=0;j<list[i].size();j++) {
//				System.out.print(list[i].get(j) + " ");
//			}
//			System.out.println();
		}
		cnt = 1;
		seq[R] = cnt;
		v[R] = true;
		dfs(R);
		for(int i=1;i<=N;i++) {
			System.out.println(seq[i]);
		}
	}
	
	private static void dfs(int idx) {
		
		// basis
		
		// inductive
			for(int i=0;i<list[idx].size();i++) {
				int next = list[idx].get(i);
				if(!v[next]) {
					v[next] = true;
					seq[next] = ++cnt;
					dfs(next);
			}
		}
	}

}
