
import java.io.*;
import java.util.*;

public class Main {

	static int[] arr;
	static int N, M;
	static List<Integer> list;
	static int[] sel;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		list = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[10000];
		sel = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			if (arr[a] == 0) {
				arr[a]++;
				list.add(a);
			}
		}
		Collections.sort(list);
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		recursive(0, 0);

	}

	private static void recursive(int idx, int k) {
		// basis
		if (k == M) {
			for (int i = 0; i < sel.length; i++)
				System.out.print(sel[i] + " ");
			System.out.println();
			return;
		}

		// inductive
		for (int i = idx; i < list.size(); i++) {
			int a = list.get(i);
			sel[k] = a;
			recursive(i, k + 1);
		}
	}

}
