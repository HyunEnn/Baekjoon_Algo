
import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	static int[] arr;
	static boolean[] v;
	static int cnt;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		v = new boolean[N];
		cnt = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		if (S == 0)
			System.out.println(cnt - 1);
        else
		    System.out.println(cnt);

	}

	private static void dfs(int idx, int num) {
		// basis -> 끝까지 돌앗으면 return
		if (idx == N) {
			if (num == S)
				cnt++;
			return;
		}

		if (!v[idx]) {
			v[idx] = true;
			dfs(idx + 1, num + arr[idx]);
			v[idx] = false;
		}
		dfs(idx+1, num);
	}

}
