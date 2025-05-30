import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] h = new int[N][2];
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			h[i][0] = Integer.parseInt(st.nextToken());
			h[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(h, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) 
					return o1[0] - o2[0];
				else 
					return o1[1]-o2[1];
			}
		});
		int time = h[0][1];
		int cnt = 1;
		for(int i=1;i<N;i++) {
			if(h[i][0] >= time) {
				cnt++;
				time = h[i][1];
			}
		}
		System.out.println(cnt);
	}

}
