
import java.io.*;
import java.util.*;

public class Main {

	static int n,m;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] sel = new int[m];
		
		recursive(1, 0, sel);
		System.out.println(sb);
	}
	private static void recursive(int idx, int k, int[] sel) {
		// basis
		if(k == m) {
			for(int x : sel) {
				sb.append(x + " ");
			}
			sb.append("\n");
			return;
		}
		// inductive
		else {
			for(int i=idx;i<=n;i++) {
				sel[k] = i;
				recursive(i, k+1, sel);
			}
		}
	}

}
