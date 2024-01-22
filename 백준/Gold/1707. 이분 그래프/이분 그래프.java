
import java.io.*;
import java.util.*;

public class Main {

	static int K, V, E;
	static StringTokenizer st;
	static ArrayList<Integer>[] list;
	static boolean[] v;
	static int[] ch;
	static boolean isEven;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수
			list = new ArrayList[V+1];
			v = new boolean[V+1];
			ch = new int[V+1];
			isEven = true;
			for(int j=0;j<=V;j++) {
				list[j] = new ArrayList<Integer>();
			}
			
			for(int j=0;j<E;j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			
			for(int j=1;j<=V;j++) {
				if(isEven) 
					DFS(j);
				else 
					break;
			}
			if(isEven) {
				System.out.println("YES");
			} else  {
				System.out.println("NO");
			}
		}
	}

	private static void DFS(int idx) {
		v[idx] = true;
		for(int i : list[idx]) {
			if(!v[i]) {
				ch[i] = (ch[idx] + 1) % 2;
				DFS(i);
			}
			else if(ch[idx] == ch[i]) {
				isEven = false;
				break;
			}
		}
		
	}

}
