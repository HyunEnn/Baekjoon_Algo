
import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	static int[] arr;
	static Set<Integer> list;
	static int ans = 1;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new HashSet<>();
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		v[0] = true;
		dfs(0, 0);
		while (true) {
			if (list.contains(ans))
				ans++;
			else
				break;
		}
		System.out.println(ans);
	}

	private static void dfs(int idx, int sum) {
		//
		if (idx == N) {
			if (sum > 0)
				list.add(sum);
		}

		else {
			dfs(idx + 1, sum + arr[idx]);
			dfs(idx + 1, sum);
		}

	}

}
