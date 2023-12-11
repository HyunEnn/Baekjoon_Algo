
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;
	static int[] choose;
	static int mid;
	static int cnt;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = 0;
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 기본 정렬
		
		M = Integer.parseInt(br.readLine());
		choose = new int[M]; 
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			choose[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<M;i++) {
			boolean find = false;
			int start = 0;
			int end = arr.length-1;
			while(start <= end) {
				int midI = (start + end) / 2;
				if(choose[i] > arr[midI]) {
					start = midI + 1;
				} else if(choose[i] < arr[midI]) {
					end = midI - 1; 
				} else {
					find = true;
					break;
				}
			}
			
			if(find == true) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
		
	}

}
