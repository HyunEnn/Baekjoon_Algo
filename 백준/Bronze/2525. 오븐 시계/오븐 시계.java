

import java.io.*;
import java.util.*;

public class Main {

	static int A, B;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(br.readLine());
		if(B + c >= 60) {
			A += (B + c) / 60;
			B = (B + c) % 60;
			if(A >= 24) {
				A -= 24;
			}
		} else {
			B += c;
		}
		System.out.println(A + " " + B);

	}

}
