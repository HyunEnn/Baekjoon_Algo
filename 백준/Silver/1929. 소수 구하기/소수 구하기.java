import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static StringTokenizer st;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[10000001];
		for(int i=2;i<arr.length;i++) {
			arr[i] = i;
		}
		
		for(int i=2;i<Math.sqrt(arr.length);i++) {
			if(arr[i] == 0) continue;
			for(int j=i*i;j<arr.length;j+=i) {
				arr[j] = 0;
			}
		}
		
		for(int i=N;i<=M;i++) {
			if(arr[i] != 0 ) 
				System.out.println(arr[i]);
		}

	}

}
