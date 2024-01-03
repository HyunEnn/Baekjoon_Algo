
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[10000001];
		check = 0;
		for(int i=2;i<10000001;i++) {
			arr[i] = i;
		}
		// 소수 체크 2~10,000,000 , N <= 1,000,000
		// 
		for(int i=2;i<Math.sqrt(arr.length);i++) {
			if(arr[i] == 0) continue; // 2 부터 시작 -> 2 4 
			for(int j=i+i;j<arr.length;j+=i) {
				arr[j] = 0;
			}
		}
		
		int idx = N;
		while(true) {
			if(arr[idx] != 0) {				
				isPalenDrom(idx);
			}
			
			if(check == 1) {
				System.out.println(idx);
				break;
			}
			idx++;
		}
		
	}
	private static void isPalenDrom(int idx) {
		char[] diff = String.valueOf(idx).toCharArray();
		int ch = 0;
		int lx = 0;
		int rx = diff.length-1;
		while(lx<rx) {
			if(diff[lx] == diff[rx]) {
				lx++;
				rx--;
			} else { // 앞 뒤의 값이 다르면, 
				ch = 1;
				break;
			}
		}
		if(ch == 0) 
			check = 1;
	}

}
