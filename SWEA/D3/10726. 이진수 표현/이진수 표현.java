
import java.io.*;
import java.util.*;

public class Solution {

	static int T, N, M;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int lastNBit = (1 <<(N))-1;
			if(lastNBit == (M & lastNBit)) {
				System.out.println("#" + i + " " + "ON");
			} else {
				System.out.println("#" + i + " " + "OFF");
			}
		}

	}

}
