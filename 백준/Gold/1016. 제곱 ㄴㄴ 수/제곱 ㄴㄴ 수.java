import java.io.*;
import java.util.*;

public class Main {

	static long min, max;
	static boolean[] v;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		// min ~ max 사이 제곱수 판별 
		// min = 1, max = 10
		v = new boolean[(int) (max-min+1)];
		for(long i=2;i*i<=max;i++) {
			long pow = i*i;
			long start_index = min / pow; // 1 / 4 = 0
			if(min % pow != 0)	start_index++; // start_index = 1
			for(long j = start_index;pow * j <= max;j++) {
				v[(int)((j * pow)-min)] = true;
			} // 
		}
		
		int cnt = 0;
		for(int i=0;i<=max-min;i++) {
			if(!v[i]) cnt++;
		}
		System.out.println(cnt);
	}

}
