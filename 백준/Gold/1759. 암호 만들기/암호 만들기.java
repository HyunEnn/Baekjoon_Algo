import java.io.*;
import java.util.*;

public class Main {

	static int L, C;
	static StringTokenizer st;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static char[] alpha;
	static char[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 자리 수
		C = Integer.parseInt(st.nextToken()); // 입력받는 char
		visited = new boolean[C]; // 방문 배열
		alpha = new char[C];
		answer= new char[L];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpha); // 사전식으로 정렬
		
		recursive(0, 0);
		// acis iacs 
	}
	private static void recursive(int idx, int a) {
		// basis
		if(idx == L) {
			if(isPossible()) {
				System.out.println(answer);
			}
			return;
		}
		
		// inductive
		for(int i=a;i<C;i++) {
			answer[idx] = alpha[i];
			recursive(idx+1, i+1);
		}
	}
	private static boolean isPossible() {
		int v = 0; // 모음
		int c = 0; // 자음
		for(int i=0;i<L;i++) {
			if(answer[i] == 'a' ||
					answer[i] == 'e' ||
					answer[i] == 'i' ||
					answer[i] == 'o' ||
					answer[i] == 'u') {
				v++;
			}
			else
				c++;
		}
		if(v >= 1 && c >= 2) 
			return true;
		return false;
	}

}
