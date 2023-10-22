
import java.io.*;
import java.util.*;

public class Main {

	static int count;
	static class Person implements Comparable<Person>{
		int age; String name; int cnt;
		private Person(int age, String name, int cnt ) {
			this.age = age;
			this.name = name;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Person o) {
			if(this.age == o.age) {
				return this.cnt - o.cnt;
			}
			return this.age - o.age;
		}
		
	}
	static int N;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Person> PL = new ArrayList<>();
		count = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			PL.add(new Person(a, name, ++count));
		}
		
		Collections.sort(PL);
		for(Person p : PL) {
			System.out.println(p.age + " " + p.name);
		}
		
	}

}
