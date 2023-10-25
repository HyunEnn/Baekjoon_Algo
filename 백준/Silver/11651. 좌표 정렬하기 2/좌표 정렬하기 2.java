

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static class Point implements Comparable<Point>{
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.y  == o.y) {
				return this.x- o.x;
			}
			return this.y - o.y; // this -> o 순서로 하면 오름차순
		}
		
	}
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Point> list = new ArrayList<>();		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());			
			list.add(new Point(x, y));
		}
		
		Collections.sort(list);
		
		for(Point p : list) {
			System.out.println(p.x + " " + p.y);
		}

	}
}
