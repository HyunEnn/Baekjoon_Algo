

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static String[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new String[N];
		for(int i=0;i<N;i++) {
			arr[i] = br.readLine();
		}
		
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o1.length() - o2.length();
			}
		});
		
		System.out.println(arr[0]);
//		System.out.println(arr[0].equals(arr[1]));
		for(int i=1;i<N;i++) {
//			if(arr[i] != arr[i-1]) -> 주소값을 비교함 ( 값이 같더라도 가르키는 주소값이 다름 ) 
			if(!arr[i].equals(arr[i-1])) // 문자의 내용을 비교함
				System.out.println(arr[i]);
		}
	}

}
