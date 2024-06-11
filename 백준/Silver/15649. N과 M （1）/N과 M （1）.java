import java.io.*;
import java.util.*;

public class Main {

	static int n, s;
	static boolean[] ch;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // 전체 배열 갯수
		s = sc.nextInt(); // 뽑을 갯수
		arr = new int[s];
		ch = new boolean[n];
		recursive(0);
	}
	private static void recursive(int idx) {
		// basis
		if(idx == s) {
			for(int x : arr) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		} // 3 , 1 -> 1 2 3
		// inductive
		for (int i = 0; i < n; i++) {
			if(ch[i] == false) {
				ch[i] = true;
				arr[idx] = i+1;
				recursive(idx+1);
				ch[i] = false;
			}
		}
		
		
	}

}