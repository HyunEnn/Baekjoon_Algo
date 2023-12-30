import java.io.*;
import java.util.*;

public class Main {

	static class soo implements Comparable<soo>{
		int num;
		soo(int num) {
			this.num = num;
		}
		
		@Override
		public int compareTo(soo o) {
			return o.num - this.num;
		}
	}
	static int N;
	static PriorityQueue<soo> FPQ; // 양수 -> 역정렬
	static PriorityQueue<Integer> LPQ; // 음수 -> 순정렬
	static int one; // 1일 때
	static int zero; // 0일 때
	static int res; // 결과값
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		FPQ = new PriorityQueue<>();
		LPQ = new PriorityQueue<>();
		res = 0;
		one = 0;
		zero = 0;
		for(int i=0;i<N;i++) {
			int a = Integer.parseInt(br.readLine());
			if(a > 1) FPQ.offer(new soo(a));
			else if(a == 1) one++;
			else if(a == 0) zero++;
			else LPQ.offer(a);
		}
		
		while(FPQ.size() > 1) { // 양수 2개 이상인 PQ
			soo x = FPQ.poll();
			soo y = FPQ.poll();
			res += x.num * y.num;
		}
		
		while(!FPQ.isEmpty()) { // 남은 값은 더해줌
			soo x = FPQ.poll();
			res += x.num;
		}
		
		while(LPQ.size() > 1) { // 음수 * 음수 = 양수
			int x = LPQ.poll();
			int y = LPQ.poll();
			res += x * y;
		}
		
		if(!LPQ.isEmpty()) {
			if(zero == 0) res += LPQ.poll();
		}
		res += one;
		System.out.println(res);
		
	}

}
