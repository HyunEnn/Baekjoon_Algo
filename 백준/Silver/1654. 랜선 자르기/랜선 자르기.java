
import java.io.*;
import java.util.*;

public class Main {

	static int K, N;
	static int[] arr;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 막대
		N = Integer.parseInt(st.nextToken()); // 자를 갯수
		arr = new int[K];
		long max = 0;
		for(int i=0;i<K;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		max += 1;
		long mid = 0;
		long min = 0;
		
		long res = 0;
		/*
		 * 이분 탐색으로, 가장 큰 값을 구해서 그것을 반으로 나누면서, 
		 * 될 수 있는 최대값을 찾는다
		 *  
		 */
		while(max > min) {
			mid = ( max + min ) / 2; // 값을 반으로 나눔
			int cnt = 0;
			
			for(int i=0;i<K;i++) {
				cnt += arr[i] / mid; // cnt에 증가 
			}
			
			if(cnt < N) {
				max = mid;
			} else {
				res = Math.max(res, mid);
				min = mid + 1;
			}
		}
		// 4 11
		// 802 743 457 539 -> max = 803 , mid = 401 
		// 0~401 , mid = 200 ( 4, 3, 2, 2 )
		// 
		System.out.println(res);
	}

}
