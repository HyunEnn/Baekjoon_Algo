

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static PriorityQueue Q;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Q = new PriorityQueue<Integer>();
		res = 0;
		for(int i=0;i<N;i++) {
			Q.offer(Integer.parseInt(br.readLine()));
		}
		while(!Q.isEmpty()) {
			if(Q.size() < 2) break;
			
			int a = (int)Q.poll();
			int b = (int)Q.poll();
//			System.out.println(a + " " + b);
			res += a+b;
			Q.offer(a+b);
		}
		System.out.println(res);
		
		
	}

}
