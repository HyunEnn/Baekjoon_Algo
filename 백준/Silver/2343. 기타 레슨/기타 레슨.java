import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] blueLay;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = 0;
		blueLay = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			blueLay[i] = Integer.parseInt(st.nextToken());
			if(start <= blueLay[i]) start = blueLay[i]; // 최소
			end += blueLay[i]; // 최대
		}
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			int cnt = 0;
			for(int i=0;i<N;i++) {
				if(sum + blueLay[i] > mid) {
					cnt++;
//					System.out.println("cnt : " + cnt);
					sum = 0;
				}
					sum += blueLay[i];
					/* System.out.println(i + " : " + sum); */
				
			}
			if(sum != 0) cnt++;
			if(cnt > M) 
				start = mid + 1;
			else 
				end = mid - 1;
		}
		
		System.out.println(start);

	}

}