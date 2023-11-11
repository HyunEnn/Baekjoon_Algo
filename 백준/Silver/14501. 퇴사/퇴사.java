import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r, w;

		Point(int r, int w) {
			this.r = r;
			this.w = w;
		}
	}

	static int N;
	static List<Point> list;
	static StringTokenizer st;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Point(a, b));
		}
//		System.out.println(list.size());

		recursive(0, 0);
		System.out.println(max);
	}

	private static void recursive(int idx, int sum) {
		// basis
		if (idx >= N) {
			max = Math.max(max, sum);
			return;
		}

		// inductive
		for (int i = idx; i < list.size(); i++) {
			Point p = list.get(i);
			if (i + p.r <= N) {
				recursive(i + p.r, sum + p.w);
			} else 
				recursive(i + p.r, sum);
			recursive(i + 1, sum);
		}

	}

}
